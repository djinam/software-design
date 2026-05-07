package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SlideMouseController extends MouseAdapter {
    private Slide slide;
    private ImageElement selected;
    private Point lastPoint;

    public SlideMouseController(Slide slide) {
        this.slide = slide;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        SlideView slideView = (SlideView) e.getSource();
        Point clickPoint = e.getPoint();

        // don't allow clicks on ImageElements
        for (GraffNode node : slide.getChildren()) {
            if (node instanceof ImageElement img && img.contains(e.getPoint())) {
                ApplicationFramework.getInstance().getMessageGenerator().generateMessage("CANNOT ADD TEXT ON PICTURE", MessageType.UPOZORENJE);
                return;
            }
        }
        slideView.showTextInput(clickPoint, text -> {
            slide.addTextElement(text, clickPoint);
        });
    }
}
