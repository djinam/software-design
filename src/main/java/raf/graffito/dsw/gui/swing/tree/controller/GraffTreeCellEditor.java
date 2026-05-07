package raf.graffito.dsw.gui.swing.tree.controller;

import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class GraffTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;
    private JTextField edit = null; // novo ime cvora

    public GraffTreeCellEditor(JTree arg0, DefaultTreeCellRenderer arg1) {
        super(arg0, arg1);
    }

    // arg0 - JTree komponenta (tj stablo)
    // arg1 - objekat koji predstavlja cvor koji se edituje (njega menjas)
    // arg2 - boolean vrednost koja oznacava da li je cvor selektovan
    // arg3 - boolean vrednost koja oznacava da li je cvor prosiren
    // arg4 - boolean vrednost koja oznacava da li je cvor lista
    // arg5 - indeks reda cvora u stablu
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {
        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        clickedOn = arg1; // osluskuje da je kliknuto
        edit = new JTextField(arg1.toString()); //arg1 je node na koji je kliknuto, uzmi mu toString i stavi ovde
        edit.addActionListener(this);
        return edit;
    }


    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent) // da li je event koji se desio poreklom od misa
            if (((MouseEvent) arg0).getClickCount() == 3) { // kastuj i prebroj
                return true;
            }
        return false;
    }



    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof GraffTreeItem)) // da li je node kliknut
            return;

        GraffTreeItem clicked = (GraffTreeItem) clickedOn;
        clicked.setName(e.getActionCommand()); // getActionCommand dogadjaj sa tastature
        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
        model.nodeChanged(clicked);
    }

}
