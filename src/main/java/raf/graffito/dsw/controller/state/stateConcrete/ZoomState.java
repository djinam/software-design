package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import java.awt.event.MouseWheelEvent;

public class ZoomState extends BaseState {

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        double factor = (e.getPreciseWheelRotation() < 0) ? 1.1 : 0.9;
        SlideView slideView = MainFrame.getInstance().getActivePresentationView().getActiveSlideView();
        slideView.zoom(factor);
    }

}
