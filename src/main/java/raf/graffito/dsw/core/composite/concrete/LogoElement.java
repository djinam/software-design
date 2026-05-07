package raf.graffito.dsw.core.composite.concrete;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class LogoElement extends SlideElement {

    private BufferedImage image;

    public LogoElement(String name, GraffNode parent, BufferedImage image) {
        super(name, parent, new Point(100, 100), new Dimension(image.getWidth(), image.getHeight()));
        this.image = image;
    }

    @Override
    public GraffNode findByName(String name) {
        return getName().equals(name) ? this : null;
    }

    @Override
    public SlideElement clone() {
        LogoElement clone = new LogoElement(
                this.getName() + " Copy",
                this.getParent(),
                this.getImage()
        );
        clone.setLocation(new Point(this.getLocation()));
        clone.setDimension(new Dimension(this.getDimension()));
        return clone;
    }
}
