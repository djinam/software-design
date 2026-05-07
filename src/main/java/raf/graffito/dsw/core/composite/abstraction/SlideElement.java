package raf.graffito.dsw.core.composite.abstraction;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.prototype.IPrototype;
import raf.graffito.dsw.core.command.ElementSnapshot;

import java.awt.*;

@Getter
@Setter
public abstract class SlideElement extends GraffLeaf implements IPrototype {
    protected Point location;
    protected Dimension dimension;
    private double rotationAngle = 0; // Ugao rotacije u radijanima

    public SlideElement(String name, GraffNode parent, Point location, Dimension dimension) {
        super(name, parent);
        this.location = location;
        this.dimension = dimension;
    }

    public void move(int dx, int dy) {
        location.translate(dx, dy);
    }

    public boolean contains(Point p) {
        Rectangle r = new Rectangle(location, dimension);
        return r.contains(p);
    }

    public void rotate90(boolean clockwise) {
        double angle = Math.toRadians(clockwise ? 90 : -90);
        rotationAngle += angle;
    }

    public ElementSnapshot createSnapshot() {
        return new ElementSnapshot(location, dimension, rotationAngle);
    }

    public void restoreSnapshot(ElementSnapshot snapshot) {
        this.location = new Point(snapshot.getLocation());
        this.dimension = new Dimension(snapshot.getDimension());
        this.rotationAngle = snapshot.getRotationAngle();
    }

    public abstract SlideElement clone();

    public Dimension getDimension() {
        return dimension;
    }
}
