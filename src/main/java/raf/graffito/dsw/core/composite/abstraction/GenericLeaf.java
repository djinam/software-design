package raf.graffito.dsw.core.composite.abstraction;

import lombok.Getter;

@Getter
public class GenericLeaf extends GraffLeaf{

    public GenericLeaf(String name, GraffNode parent) {
        super(name, parent);
    }

    @Override
    public GraffNode findByName(String name) {
        return null;
    }
}
