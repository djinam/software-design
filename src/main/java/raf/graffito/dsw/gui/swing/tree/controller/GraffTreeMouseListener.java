package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.*;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GraffTreeMouseListener implements TreeSelectionListener, MouseListener {

    private GraffTreeItem dragSourceItem = null;
    private GraffTreeItem dragSourceParent = null;
    private int dragSourceIndex = -1;

    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) { // double-click
            JTree tree = (JTree) e.getSource();
            TreePath path = tree.getPathForLocation(e.getX(), e.getY());
            if (path == null) return;

            GraffTreeItem clicked = (GraffTreeItem) path.getLastPathComponent();
            Object node = clicked.getGraffNode();
            if (node instanceof Project project) { // if user double clicks a project node - open all presentations
                for (GraffNode child : project.getChildren()) {
                    if (child instanceof Presentation p) {
                        openPresentationTab(p, project);

                    }
                }
            } else if (node instanceof Presentation presentation) { // if user double clicks a presentation node
                openPresentationTab(presentation, (Project) presentation.getParent());
            }
        }
    }

    private void openPresentationTab(Presentation presentation, Project project) {
        TabbedPane mainTabbedPane = MainFrame.getInstance().getTabbedPane();

        // check if a tab for this presentation already exists
        for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
            Component tab = mainTabbedPane.getComponentAt(i);
            if (tab instanceof PresentationView pv) {
                if (pv.getPresentation() == presentation) { // same instance
                    mainTabbedPane.setSelectedComponent(tab);
                    return; // if opened select it and exit
                }
            }
        }

        String baseTitle = project.getTitle() + " - " + presentation.getName();
        String tabTitle = baseTitle;

        // tab title is unique
        int counter = 1;
        while (true) {
            boolean exists = false;
            for (int i = 0; i < mainTabbedPane.getTabCount(); i++) {
                if (mainTabbedPane.getTitleAt(i).equals(tabTitle)) {
                    exists = true;
                    break;
                }
            }
            if (!exists) break;
            tabTitle = baseTitle + " (" + counter + ")";
            counter++;
        }

        PresentationView panel = new PresentationView(presentation, project);
        mainTabbedPane.addTab(tabTitle, panel);
        mainTabbedPane.setSelectedComponent(panel);
    }


    @Override
    public void mousePressed(MouseEvent e)
    {
        JTree tree = (JTree) e.getSource();
        TreePath path = tree.getPathForLocation(e.getX(), e.getY());
        clearDragState();

        if (path == null) return;

        GraffTreeItem item = (GraffTreeItem) path.getLastPathComponent();
        if (!(item.getGraffNode() instanceof Slide)) {
            // dozvoljeno je prevlačenje SAMO slajdova
            return;
        }
        GraffTreeItem parent = (GraffTreeItem) item.getParent();
        if (parent == null) return;

        this.dragSourceItem = item;
        this.dragSourceParent = (GraffTreeItem) parent;
        this.dragSourceIndex = parent.getIndex(item);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        if (dragSourceItem == null || dragSourceParent == null) {
            clearDragState();
            return;
        }

        JTree tree = (JTree) e.getSource();
        TreePath dropPath = tree.getPathForLocation(e.getX(), e.getY());
        if (dropPath == null) {
            clearDragState();
            return;
        }

        GraffTreeItem dropNode = (GraffTreeItem) dropPath.getLastPathComponent();
        GraffNode dropGN = dropNode.getGraffNode();


        GraffTreeItem targetParentNode;
        int insertIndex;

        if (dropGN instanceof Slide) {
            targetParentNode = (GraffTreeItem) dropNode.getParent();
            if (!(targetParentNode.getGraffNode() instanceof Presentation)) {
                clearDragState();
                return;
            }
            insertIndex = targetParentNode.getIndex(dropNode);
        } else if (dropGN instanceof Presentation) {
            targetParentNode = dropNode;
            insertIndex = targetParentNode.getChildCount();
        } else {

            clearDragState();
            return;
        }

        if (targetParentNode == dragSourceParent) {
            int adjustedIndex = insertIndex;
            if (insertIndex > dragSourceIndex) {
                adjustedIndex = insertIndex - 1;
            }
            if (dragSourceIndex == adjustedIndex) {
                clearDragState();
                return;
            }
        }

        Slide slideModel = (Slide) dragSourceItem.getGraffNode();
        Presentation sourcePres = (Presentation) dragSourceParent.getGraffNode();
        Presentation targetPres = (Presentation) targetParentNode.getGraffNode();


        sourcePres.removeChild(slideModel);

        targetPres.addChild(slideModel);


        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();

        dragSourceParent.remove(dragSourceItem);
        targetParentNode.insert(dragSourceItem, insertIndex);

        model.nodeStructureChanged(dragSourceParent);
        if (dragSourceParent != targetParentNode) {
            model.nodeStructureChanged(targetParentNode);
        }

        TreePath toSelect = new TreePath(targetParentNode.getPath()).pathByAddingChild(dragSourceItem);
        tree.expandPath(new TreePath(targetParentNode.getPath()));
        tree.setSelectionPath(toSelect);
        tree.scrollPathToVisible(toSelect);

        clearDragState();
    }

    private void clearDragState() {
        dragSourceItem = null;
        dragSourceParent = null;
        dragSourceIndex = -1;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void valueChanged(TreeSelectionEvent e) {

    }
}
