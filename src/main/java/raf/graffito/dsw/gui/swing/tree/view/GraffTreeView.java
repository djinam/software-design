package raf.graffito.dsw.gui.swing.tree.view;


import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeCellEditor;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeMouseListener;
import raf.graffito.dsw.gui.swing.tree.controller.GraffTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class GraffTreeView extends JTree {

    public GraffTreeView(DefaultTreeModel defaultTreeModel) {
        setModel(defaultTreeModel);  // Setujemo model
        GraffTreeCellRenderer ruTreeCellRenderer = new GraffTreeCellRenderer(); // Kreiramo renderer
        addTreeSelectionListener(new GraffTreeSelectionListener()); // Dodajemo listener za selekciju
        addMouseListener(new GraffTreeMouseListener()); // mouse listener
        setCellEditor(new GraffTreeCellEditor(this, ruTreeCellRenderer));
        setCellRenderer(ruTreeCellRenderer); // Postavljamo renderer
        setEditable(true);
    }
}
