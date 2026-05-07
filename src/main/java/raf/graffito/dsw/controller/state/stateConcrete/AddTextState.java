package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import java.awt.*;
import java.awt.event.MouseEvent;

public class AddTextState extends BaseState {
    /// TODO: undo i normalnija implementacija
    @Override
    public void mouseClicked(MouseEvent e) {
        SlideView slideView = (SlideView) e.getSource();
        Slide slide = MainFrame.getInstance().getActiveSlide();
        Point clickPoint = e.getPoint();

        // don't allow clicks on ImageElements
        for (GraffNode node : slide.getChildren()) {
            if (node instanceof ImageElement img && img.contains(e.getPoint())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("cannot add text on picture", MessageType.UPOZORENJE);
                return;
            }
        }
        slideView.showTextInput(clickPoint, text -> {
            slide.addTextElement(text, clickPoint);
        });
    }
}
