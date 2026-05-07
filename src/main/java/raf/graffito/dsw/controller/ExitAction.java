package raf.graffito.dsw.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractGraffAction{

    public ExitAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, loadIcon("/images/button.png")); // Postavljanje ikonice
        putValue(NAME, "Exit"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Exit"); // Tooltip
    }
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}
