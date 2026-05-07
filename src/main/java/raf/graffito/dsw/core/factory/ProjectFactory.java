package raf.graffito.dsw.core.factory;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Project;

import javax.swing.*;
import java.awt.*;

public class ProjectFactory extends NodeFactory {

    /// TODO:
    @Override
    public GraffNode createNode(GraffNode parent) {
        String title = JOptionPane.showInputDialog(null, "Enter project name:");
        String author  = JOptionPane.showInputDialog(null, "Enter project author:");
        if(title==null || title.isEmpty()){
            title = "default title";
        }
        if(author==null || author.isEmpty()){
            author = "default author";
        }
        return new Project(title, author, 0, title, parent);
    }

}