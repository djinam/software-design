package raf.graffito.dsw.core;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
@Getter
public class SlideSelectionModel implements IPublisher {
    private Slide selectedSlide;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public void setSelectedSlide(Slide selectedSlide) {
        this.selectedSlide = selectedSlide;
        notifySubscribers(selectedSlide);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
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
