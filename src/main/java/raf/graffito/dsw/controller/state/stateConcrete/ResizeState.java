package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.ElementStateCommand;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.composite.concrete.TextElement;
import raf.graffito.dsw.gui.swing.painter.ImagePainter;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ResizeState extends BaseState {
    private SlideElement selected;
    private Point dragStartPoint;
    private Dimension startSize;
    private ElementStateCommand snapshot;

    @Override
    public void mousePressed(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        for (GraffNode node : slide.getChildren()) {
            if (node instanceof ImageElement img) {
                ImagePainter painter = new ImagePainter(img);
                if (painter.elementAt(e.getPoint())) {
                    selected = img;
                    dragStartPoint = e.getPoint();
                    startSize = new Dimension(img.getDimension());

                    snapshot = new ElementStateCommand(selected, selected.createSnapshot());
                    return;
                }
            } else if (node instanceof TextElement text) {
                System.out.println("try with other element instead");
//                TextPainter painter = new TextPainter(text);
//                if (painter.elementAt(e.getPoint())) {
//                    selected = text;
//                    dragStartPoint = e.getPoint();             // initialize drag start
//                    startSize = new Dimension(text.getDimension()); // initialize start size
//                    return;
                }
            }
        }


    @Override
    public void mouseDragged(MouseEvent e) {
        if (selected == null || dragStartPoint == null) return;

        int dx = e.getX() - dragStartPoint.x;
        int dy = e.getY() - dragStartPoint.y;

        int newWidth = Math.max(10, startSize.width + dx);
        int newHeight = Math.max(10, startSize.height + dy);

        selected.setDimension(new Dimension(newWidth, newHeight));

        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide != null) slide.notifySubscribers(this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected != null && snapshot != null) {
            snapshot.captureAfter();
            ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(snapshot);
        }
        selected = null;
        dragStartPoint = null;
        startSize = null;
    }
}
