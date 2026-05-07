package raf.graffito.dsw.core.composite.concrete;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Slide extends GraffNodeComposite implements IPublisher {

    private List<ISubscriber> subscribers = new ArrayList<>();
    private final List<SlideElement> selectedElements = new ArrayList<>();

    public Slide() {
    }

    public Slide(String name, GraffNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(GraffNode node) {
        super.getChildren().add(node);
        notifySubscribers(this);
    }

    @Override
    public void removeChild(GraffNode node) {
        super.getChildren().remove(node);
        notifySubscribers(this);
    }


    @Override
    public GraffNode findByName(String name) {
        return getName().equals(name) ? this : null;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (!subscribers.contains(sub))
            subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        for (ISubscriber sub : subscribers) {
            sub.update(notification);
        }
    }

    public void addTextElement(String text, Point p) {
        /// TODO
        SlideElement element = new TextElement(
                "text",
                this,
                p,
                new Dimension(text.length() * 8, 30),
                text
        );
        addChild(element);
    }

    public List<SlideElement> getSelectedElements() {
        return selectedElements;
    }

    public void clearSelection() {
        selectedElements.clear();
    }

    public void select(SlideElement element) {
        if (!selectedElements.contains(element)) {
            selectedElements.add(element);
        }
    }

}

