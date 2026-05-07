package raf.graffito.dsw.core.composite.concrete;

import lombok.Getter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;

import java.awt.*;
@Getter
public class TextElement extends SlideElement {

    private final String text;

    public TextElement(String name, GraffNode parent, Point location, Dimension dimension, String text) {
        super(name, parent, location, dimension);
        this.text = text;
    }

    @Override
    public GraffNode findByName(String name) {
        return getName().equals(name) ? this : null;
    }

    @Override
    public SlideElement clone() {
        TextElement clone = new TextElement(
                this.getName() + " Copy",
                this.getParent(),
                new Point(this.getLocation()),
                new Dimension(this.getDimension()),
                this.getText()
        );
        return clone;
    }
}
