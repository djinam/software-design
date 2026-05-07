package raf.graffito.dsw.controller.undoRedoAction;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractGraffAction {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (ApplicationFramework.getInstance().getUndoRedoManager().canRedo()) {
            Slide slide = MainFrame.getInstance().getActiveSlide();
            if (slide == null) return;

            ApplicationFramework.getInstance().getUndoRedoManager().redo();

            slide.notifySubscribers(this);
        } else {
            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Cannot redo yet", MessageType.GRESKA);
        }
    }
}
