package raf.graffito.dsw.core.composite.abstraction;

public abstract class GraffLeaf extends GraffNode {

    public GraffLeaf(String name, GraffNode parent) {
        super(name, parent);
    }


    public abstract GraffNode findByName(String name);
}
