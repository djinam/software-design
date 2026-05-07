package raf.graffito.dsw.controller.mediator.action;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class FullAction extends AbstractGraffAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        var presentationView = MainFrame.getInstance().getActivePresentationView();
        if (presentationView == null || presentationView.getActiveSlideView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("No slide is opened", MessageType.GRESKA);
            return;
        }
        SlideView activeSlide = MainFrame.getInstance().getActivePresentationView().getActiveSlideView();
        if (activeSlide != null) activeSlide.showFullscreen();
    }

}
