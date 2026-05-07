package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.ElementStateCommand;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.composite.concrete.TextElement;
import raf.graffito.dsw.gui.swing.painter.ImagePainter;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.painter.TextPainter;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState extends BaseState {
    private SlideElement selected;
    private Point lastPoint;
    private ElementStateCommand snapshot;

    @Override
    public void mouseDragged(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        int dx = e.getX() - lastPoint.x;
        int dy = e.getY() - lastPoint.y;

        selected.move(dx, dy);

        slide.notifySubscribers(this);
        lastPoint = e.getPoint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        for (GraffNode node : slide.getChildren()) {

            if (node instanceof ImageElement img) {
                ImagePainter painter = new ImagePainter(img);
                if (painter.elementAt(e.getPoint())) {
                    selected = img;
                    lastPoint = e.getPoint();
                    // capture snapshot before moving
                    snapshot = new ElementStateCommand(selected, selected.createSnapshot());
                    return;
                }
            } else if (node instanceof TextElement text) {
                TextPainter painter = new TextPainter(text);
                if (painter.elementAt(e.getPoint())) {
                    selected = text;
                    lastPoint = e.getPoint();
                    // capture snapshot before moving
                    snapshot = new ElementStateCommand(selected, selected.createSnapshot());
                    return;
                }
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (selected != null && snapshot != null) {
            snapshot.captureAfter();
            ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(snapshot);
            System.out.println("element moved");
        }

        snapshot = null;
        selected = null; // stop moving
    }
}
