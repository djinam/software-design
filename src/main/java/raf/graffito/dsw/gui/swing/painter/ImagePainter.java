package raf.graffito.dsw.gui.swing.painter;

import lombok.Getter;
import raf.graffito.dsw.core.composite.concrete.ImageElement;

import java.awt.*;
@Getter
public class ImagePainter implements IPainter {

    private ImageElement element;

    public ImagePainter(ImageElement element) {
        this.element = element;
    }

    public void paint(Graphics2D g) {
        g.drawImage(
                element.getImage(),
                element.getLocation().x,
                element.getLocation().y,
                element.getDimension().width,
                element.getDimension().height,
                null
        );
    }

    public boolean elementAt(Point p) {
        Point point = element.getLocation();
        Dimension d = element.getDimension();
        return new Rectangle(point.x, point.y, d.width, d.height).contains(p);
    }
}
