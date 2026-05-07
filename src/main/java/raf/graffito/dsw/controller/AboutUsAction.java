package raf.graffito.dsw.controller;
import raf.graffito.dsw.gui.swing.MainFrame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AboutUsAction extends AbstractGraffAction{

    public AboutUsAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/hacker.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }


    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().showAboutUsWindow();
    }
}
