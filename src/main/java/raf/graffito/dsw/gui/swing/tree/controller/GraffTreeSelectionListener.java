package raf.graffito.dsw.gui.swing.tree.controller;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Slide;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.util.Arrays;

public class GraffTreeSelectionListener implements TreeSelectionListener {
    // osluskuje da je neki node selektovan

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        GraffTreeItem graffTreeItemSelected = (GraffTreeItem) path.getLastPathComponent();
        System.out.println("Selektovan cvor:" + graffTreeItemSelected.getGraffNode().getName());
        System.out.println("Selektovan cvor:" + Arrays.toString(path.getPath()));

        if (graffTreeItemSelected.getGraffNode() instanceof Slide slide)
            ApplicationFramework.getInstance().getSlideSelectionModel().setSelectedSlide(slide);

    }

//    private void slideSelection(Slide slide) {
//        TabbedPane tabbedPane = MainFrame.getInstance().getTabbedPane();
//        Component selectedTab = tabbedPane.getSelectedComponent();
//
//        if (selectedTab instanceof PresentationView pv &&
//                slide.getParent() == pv.getPresentation()) {
//            pv.showSlide(slide); /// mvc controller directly tells view what to do
//        }
//    }
}