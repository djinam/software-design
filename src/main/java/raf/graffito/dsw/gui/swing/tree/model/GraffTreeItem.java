package raf.graffito.dsw.gui.swing.tree.model;

import lombok.Getter;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;
import java.util.List;

@Getter
public class GraffTreeItem extends DefaultMutableTreeNode {
    // predstavlja jedan node (model noda)
    // wrapper za implementaciju jednog cvora

    private GraffNode graffNode;

    public GraffTreeItem(GraffNode graffNode){
        this.graffNode = graffNode;
    }

    @Override
    public String toString() {
        return graffNode.toString();
    }

    public void setName(String name) {
        this.graffNode.setName(name);
    }

    public GraffNode getGraffNode() {
        return graffNode;
    }

    public List<GraffTreeItem> getChildren() { /// todo
        List<GraffTreeItem> childrenList = new ArrayList<>();
        for (int i = 0; i < getChildCount(); i++) {
            Object child = getChildAt(i);
            if (child instanceof GraffTreeItem item) {
                childrenList.add(item);
            }
        }
        return childrenList;
    }

}
