package raf.graffito.dsw.core.composite.concrete;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
@Setter
public class ImageElement extends SlideElement {

    private BufferedImage image;

    public ImageElement(String name, GraffNode parent, BufferedImage image) {
        super(name, parent, new Point(100, 100), new Dimension(image.getWidth(), image.getHeight()));
        this.image = image;
    }

    public BufferedImage getImage() {
        return image;
    }

    @Override
    public GraffNode findByName(String name) {
        return getName().equals(name) ? this : null;
    }

    @Override
    public SlideElement clone() {
        ImageElement clone = new ImageElement(
                this.getName() + " Copy",
                this.getParent(),
                this.getImage()
        );
        clone.setLocation(new Point(this.getLocation()));
        clone.setDimension(new Dimension(this.getDimension()));
        return clone;
    }
}
