package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Project;

import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.NewChildTypeDialog;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractGraffAction{

    public NewProjectAction() {
       // putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/plus.png"));
        putValue(NAME, "New Project");
        putValue(SHORT_DESCRIPTION, "New Project");
    }

    public void actionPerformed(ActionEvent arg0) { // dodavanje novog cvora
        try {
            GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getGraffTree().getSelectedNode();
            if(selected == null) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NO NODE SELECTED", MessageType.GRESKA);
                return;
            }
            Object node = selected.getGraffNode();
            if (!(node instanceof Project)) {
                MainFrame.getInstance().getGraffTree().addChild(selected);
                return;
            }

            NewChildTypeDialog.Choice choice = MainFrame.getInstance().showNewChildTypeDialog();
            if(choice == NewChildTypeDialog.Choice.PRESENTATION) {
                MainFrame.getInstance().getGraffTree().addChild(selected);
            }
            else if(choice == NewChildTypeDialog.Choice.SLIDE) {
                MainFrame.getInstance().getGraffTree().addChild(selected);
            }
        }
        catch (Exception e) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NO NODE SELECTED", MessageType.GRESKA);
        }
    }
}
