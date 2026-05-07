package raf.graffito.dsw.gui.swing.tree.view;

import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;


public class GraffTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        // Object value je selektovan cvor

        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if (((GraffTreeItem)value).getGraffNode() instanceof Workspace) {
            imageURL = getClass().getResource("/images/hat.png");

        }
        else if (((GraffTreeItem)value).getGraffNode() instanceof Project) {
            imageURL = getClass().getResource("/images/artificial-intelligence.png");
        }
        else if (((GraffTreeItem)value).getGraffNode() instanceof Presentation) {
            imageURL = getClass().getResource("/images/computer.png");
        }
        else if (((GraffTreeItem)value).getGraffNode() instanceof Slide) {
            imageURL = getClass().getResource("/images/document.png");
        }

        Icon icon;
        Image img = new  ImageIcon(imageURL).getImage();
        Image newImg = img.getScaledInstance(25, 30, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImg);
        setIcon(icon);
        return this;
    }
}
