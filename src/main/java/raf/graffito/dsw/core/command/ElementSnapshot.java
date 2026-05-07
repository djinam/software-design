package raf.graffito.dsw.core.command;

import lombok.Getter;

import java.awt.*;
@Getter
public class ElementSnapshot {

    private final Point location;
    private final Dimension dimension;
    private final double rotationAngle;

    public ElementSnapshot(Point location, Dimension dimension, double rotationAngle) {
        // create copies to make snapshot immutable
        this.location = new Point(location);
        this.dimension = new Dimension(dimension);
        this.rotationAngle = rotationAngle;
    }
}
