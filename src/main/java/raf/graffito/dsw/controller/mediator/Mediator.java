package raf.graffito.dsw.controller.mediator;

import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import java.awt.*;

public class Mediator implements IMediator {
    private ButtonColleague selectedButton;

    @Override
    public void notify(ButtonColleague sender, String event) {
        MainFrame.getInstance().updateSlideSize(event);
        selectedButton = sender;
    }

    public Dimension getCurrentSlideDimension() {
        SlideView slideView = MainFrame.getInstance().getActivePresentationView().getActiveSlideView();

        return switch (slideView.getCurrentMode()) {
            case "SMALL" -> slideView.getSmallSize();
            case "NORMAL" -> slideView.getNormalSize();
            case "FULLSCREEN" -> slideView.getParent().getSize();
            default -> slideView.getNormalSize();
        };
    }
}
