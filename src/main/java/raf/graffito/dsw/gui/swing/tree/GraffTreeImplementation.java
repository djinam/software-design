package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.core.factory.NodeFactory;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeImplementation implements IGraffTree{
    private GraffTreeView treeView; // view
    private DefaultTreeModel treeModel; // model

    @Override
    public GraffTreeView generateTree(Workspace workspace) { // prosledis root cvor
        GraffTreeItem root = new GraffTreeItem(workspace); // Kreiramo root cvor stabla
        treeModel = new DefaultTreeModel(root); // Kreiramo model stabla sa root cvorom
        treeView = new GraffTreeView(treeModel); // Kreiramo view stabla sa modelom
        return treeView;
    }

    @Override
    public void addChild(GraffTreeItem parent) { // kad klikces po jtree-u to ce biti tipa GraffTreeItem
        if (!(parent.getGraffNode() instanceof GraffNodeComposite)) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT ADD CHILD NODE TO SLIDE", MessageType.UPOZORENJE);
            return;
        }

        GraffNode child = createChild(parent.getGraffNode()); // Kreiramo novi child cvor
        parent.add(new GraffTreeItem(child)); // Dodajemo child cvor u parent cvor u view-u
        ((GraffNodeComposite) parent.getGraffNode()).addChild(child); // Dodajemo child cvor u parent cvor u modelu podataka
        treeView.expandPath(treeView.getSelectionPath()); // Prosirujemo parent cvor u view-u da bi se video novi child
        SwingUtilities.updateComponentTreeUI(treeView); // Osvezavamo view
        ApplicationFramework.getInstance().getGraffRepository().notifySubscribers(child);
    }


    @Override
    public void removeChild(GraffTreeItem parent) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();
        if (selected == null){
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NO NODE SELECTED", MessageType.GRESKA);
        }

        GraffTreeItem parentNode = (GraffTreeItem) selected.getParent();

        GraffNode parentGraffNode = parentNode.getGraffNode();
        if (parentGraffNode instanceof GraffNodeComposite parentComposite) {
            parentComposite.removeChild(selected.getGraffNode());
        }

        parentNode.remove(selected); // remove from view

        if (treeModel != null) {
            treeModel.reload(parentNode); // refresh the tree UI
        }

    }


    @Override
    public GraffTreeItem getSelectedNode() {
        return (GraffTreeItem) treeView.getLastSelectedPathComponent();
    }

    private GraffNode createChild(GraffNode parent) {
        if (!(parent instanceof GraffNodeComposite)) {
             ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Cannot add child to non-composite node", MessageType.UPOZORENJE);
             return null;
        }
        if (parent instanceof Project || parent instanceof Presentation || parent instanceof Workspace) {
            NodeFactory factory = ApplicationFramework.getInstance().getGraffRepository().getNodeFactory(parent);
            return factory.createNode(parent);
        }
        return null;
    }
}
