package raf.graffito.dsw.core.messageGenerator;

import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {

    private List<ISubscriber> subscribers;

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) { // ide kroz listu subs i nad svakim poziva update
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for (ISubscriber listener : subscribers) {
            listener.update(notification);
        }
    }
    public void generateMessage(String message, MessageType messageType) {
        Message msg = new Message(message, messageType, LocalDateTime.now());
        notifySubscribers(msg); // all subs
    }
}
