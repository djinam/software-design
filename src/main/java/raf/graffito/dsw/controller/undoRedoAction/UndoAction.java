package raf.graffito.dsw.controller.undoRedoAction;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class UndoAction extends AbstractGraffAction {

    public void actionPerformed(ActionEvent e) {
        if (ApplicationFramework.getInstance().getUndoRedoManager().canUndo()) {
            Slide slide = MainFrame.getInstance().getActiveSlide();
            if (slide == null) return;

            ApplicationFramework.getInstance().getUndoRedoManager().undo();

            slide.notifySubscribers(this);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Cannot undo yet", MessageType.GRESKA);
        }
    }
}
