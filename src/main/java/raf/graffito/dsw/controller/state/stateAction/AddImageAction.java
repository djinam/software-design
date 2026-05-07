package raf.graffito.dsw.controller.state.stateAction;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class AddImageAction extends AbstractGraffAction {
    public void actionPerformed(ActionEvent e) {
        var presentationView = MainFrame.getInstance().getActivePresentationView();
        if (presentationView == null || presentationView.getActiveSlideView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("No slide is opened", MessageType.GRESKA);
            return;
        }
    }
}
