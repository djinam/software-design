package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.AddElementCommand;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class CloneAction extends AbstractAction {

    @Override
    public void actionPerformed(ActionEvent e) {
        var presentationView = MainFrame.getInstance().getActivePresentationView();
        if (presentationView == null || presentationView.getActiveSlideView() == null) {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("No slide is opened", MessageType.GRESKA);
            return;
        }

        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        for (SlideElement original : slide.getSelectedElements()) {
            SlideElement clone = original.clone();
            clone.move(10, 10);

            ///slide.addChild(clone);
            AddElementCommand addCommand = new AddElementCommand(slide, clone);
            ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(addCommand);
        }
        slide.notifySubscribers(this);
    }
}
