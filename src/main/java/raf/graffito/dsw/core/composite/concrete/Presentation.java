package raf.graffito.dsw.core.composite.concrete;

import lombok.Getter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Presentation extends GraffNodeComposite implements IPublisher {
    private List<ISubscriber> subscribers = new ArrayList<>();

    public Presentation() {
    }

    public Presentation(String name, GraffNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(GraffNode node) {
        super.getChildren().add(node);
        notifySubscribers(node);
    }

    @Override
    public void removeChild(GraffNode node) {
        super.getChildren().remove(node);
        notifySubscribers(node);
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

}
