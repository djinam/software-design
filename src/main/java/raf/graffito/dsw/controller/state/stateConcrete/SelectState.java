package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.event.MouseEvent;

public class SelectState extends BaseState {

    @Override
    public void mouseClicked(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        for (var node : slide.getChildren()) {
            if (node instanceof SlideElement element) {
                if (element.contains(e.getPoint())) {
                    System.out.println("click!");
                    slide.select(element);
                }
            }
        }
    }

}
