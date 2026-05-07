package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.ElementStateCommand;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.MouseEvent;

public class RotateLeftState extends BaseState {

    public void mouseClicked(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        // 1. if there are selected elements, rotate all of them
        if (!slide.getSelectedElements().isEmpty()) {
            for (SlideElement el : slide.getSelectedElements()) {
                ElementStateCommand snapshot = new ElementStateCommand(el, el.createSnapshot());

                el.rotate90(false);

                snapshot.captureAfter();
                ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(snapshot);
            }
            System.out.println("elements rotated");
            slide.notifySubscribers(this);
            return;
        }

        // 2. otherwise, rotate only the element under the click
        for (var node : slide.getChildren()) {
            if (node instanceof SlideElement element) {
                if (element.contains(e.getPoint())) {
                    ElementStateCommand snapshot = new ElementStateCommand(element, element.createSnapshot());

                    element.rotate90(false);

                    snapshot.captureAfter();
                    ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(snapshot);

                    System.out.println("element rotated");
                    slide.notifySubscribers(this);
                    break;
                }
            }
        }
    }
}
