package raf.graffito.dsw.core.factory;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;

public abstract class NodeFactory {

    public abstract GraffNode createNode(GraffNode parent);
}
