package raf.graffito.dsw.core.composite.concrete;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;

public class Workspace extends GraffNodeComposite {

    Workspace() {}

    public Workspace(String name) {
        super(name, null);
    }

    @Override
    public void addChild(GraffNode node) {
        super.getChildren().add(node);
    }

    @Override
    public void removeChild(GraffNode node) {
        super.getChildren().remove(node);
    }

}
