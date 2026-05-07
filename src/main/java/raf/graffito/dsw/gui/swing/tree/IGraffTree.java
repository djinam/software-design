package raf.graffito.dsw.gui.swing.tree;

import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.core.factory.NodeFactory;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.tree.view.GraffTreeView;

public interface IGraffTree { // ugovor metoda koje treba da imam
    // 'most' izmedju onog sto je napravljeno i sta treba da dohvatim
    // view-model bridge

    GraffTreeView generateTree(Workspace workspace); // korenski cvor
    void addChild(GraffTreeItem parent);
    void removeChild(GraffTreeItem parent);
    GraffTreeItem getSelectedNode();
}
