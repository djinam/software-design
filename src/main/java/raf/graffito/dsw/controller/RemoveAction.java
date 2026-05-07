package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class RemoveAction extends AbstractGraffAction{
    public RemoveAction() {
       // putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/minus.png")); // Postavljanje ikonice
        putValue(NAME, "RemoveAction"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Remove"); // Tooltip
    }
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();

        if (selected == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NO NODE SELECTED", MessageType.GRESKA);
            return;
        }

        if (selected.getGraffNode() instanceof Workspace) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("ROOT NODE CANNOT BE DELETED", MessageType.GRESKA);
            return;
        }

        MainFrame.getInstance().getGraffTree().removeChild(selected);
    }

}
