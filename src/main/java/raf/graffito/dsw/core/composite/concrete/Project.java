package raf.graffito.dsw.core.composite.concrete;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;
@Getter
public class Project extends GraffNodeComposite implements IPublisher {

    private String title;
    private String author;
    private int number; // ukupan br slajdova projekta
    private List<ISubscriber> subscribers = new ArrayList<>();

    public Project() {
    }

    public Project(String title, String author, int number, String name, GraffNode parent) {
        super(name, parent);
        this.title = title;
        this.author = author;
        this.number = number;
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
    public void setTitle(String title) { /// TODO
        this.title = title;
        notifySubscribers(this);
    }

    public void setAuthor(String author) {
        this.author = author;
        notifySubscribers(this);
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getNumber() {
        return number;
    }
}
