package raf.graffito.dsw.core.composite.abstraction;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public abstract class GraffNodeComposite extends GraffNode{

    @JsonProperty
    private List<GraffNode> children =  new ArrayList<>();

    public GraffNodeComposite(String name, GraffNode parent) {
        super(name, parent);
    }

    public GraffNodeComposite() {

    }

    public abstract void addChild(GraffNode node);

    public abstract void removeChild(GraffNode node);

    public List<GraffNode> getChildren() {
        return this.children;
    }


    @Override
    public GraffNode findByName(String name) {
        if (getName().equals(name)) { // first node check
            return this;
        }

        for (GraffNode child : children) { // recursively check children
            GraffNode result = child.findByName(name);
            if (result != null) {
                return result;
            }
        }

        return null;
    }
}
