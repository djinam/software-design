package raf.graffito.dsw.core.composite;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.core.factory.*;
import raf.graffito.dsw.core.observer.IPublisher;
import raf.graffito.dsw.core.observer.ISubscriber;

import java.util.ArrayList;
import java.util.List;

public class GraffRepositoryImplementation implements IGraffRepository, IPublisher {
    private Workspace workspace;
    private List<ISubscriber> subscribers = new ArrayList<>();

    public GraffRepositoryImplementation(){
        workspace = new Workspace("Workspace");
    }
    @Override
    public Workspace getWorkspace() {
        return workspace;
    }

    @Override
    public void addChild(GraffNodeComposite parent, GraffNode child) {

    }

     @Override
    public NodeFactory getNodeFactory(GraffNode parent) {
        if (parent instanceof Workspace)
            return new ProjectFactory();
        else if (parent instanceof Project)
            return new PresentationFactory();
        else if (parent instanceof Presentation)
            return new SlideFactory();
        //else if (parent instanceof Slide)
            //return new SlideElementFactory();
        return null;
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
