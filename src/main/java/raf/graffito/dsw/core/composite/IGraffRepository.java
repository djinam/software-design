package raf.graffito.dsw.core.composite;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.core.factory.NodeFactory;

public interface IGraffRepository {
    Workspace getWorkspace();
    void addChild(GraffNodeComposite parent, GraffNode child); // ne treba?
    NodeFactory getNodeFactory(GraffNode parent) ;
}
