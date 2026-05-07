package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.DeleteElementCommand;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;


import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class DeleteState extends BaseState {

    private void deleteWithCommand(Slide slide, SlideElement element) {
        ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(new DeleteElementCommand(slide, element));
    }

    private void deleteSelected(Slide slide) {
        for (SlideElement el : new ArrayList<>(slide.getSelectedElements())) {
            deleteWithCommand(slide, el);
        }
        slide.clearSelection();
    }

//    public void deleteSelected() {
//        Slide slide = MainFrame.getInstance().getActiveSlide();
//        if (slide == null) return;
//        //System.out.println(slide.getSelectedElements().size());
//        for (SlideElement el : new ArrayList<>(slide.getSelectedElements())) {
//            slide.removeChild(el);
//            System.out.println("removed selected elements");
//        }
//        slide.clearSelection();
//    }

    public void mouseClicked(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        // if there are selected elements, delete all of them first
        if (!slide.getSelectedElements().isEmpty()) {
            deleteSelected(slide);
            slide.notifySubscribers(this);
        }

        // otherwise, find the element under the click and delete it
        for (var node : slide.getChildren()) {
            if (node instanceof SlideElement element) {
                if (element.contains(e.getPoint())) {
                    System.out.println("deleting: " + element.getName());
                    ///slide.removeChild(element);
                    deleteWithCommand(slide, element);
                    slide.clearSelection();
                    slide.notifySubscribers(this);
                    break;
                }
            }
        }
    }

}
