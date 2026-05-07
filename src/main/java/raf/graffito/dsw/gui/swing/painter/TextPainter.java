package raf.graffito.dsw.gui.swing.painter;

import raf.graffito.dsw.core.composite.concrete.TextElement;

import java.awt.*;

public class TextPainter implements IPainter {
    private TextElement element;

    public TextPainter(TextElement element) {
        this.element = element;
    }

    @Override
    public void paint(Graphics2D g) {
        Point loc = element.getLocation();
        Dimension dim = element.getDimension();
        g.drawString(element.getText(), loc.x, loc.y + dim.height / 2);
    }

    public boolean elementAt(Point p) {
        Point pos = element.getLocation();
        Dimension size = element.getDimension();
        return new Rectangle(pos.x, pos.y, size.width, size.height).contains(p);
    }
}
