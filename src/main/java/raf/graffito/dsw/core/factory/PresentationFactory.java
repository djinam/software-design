package raf.graffito.dsw.core.factory;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.NewChildTypeDialog;

public class PresentationFactory extends NodeFactory{
    @Override
    public GraffNode createNode(GraffNode parent) {
        NewChildTypeDialog.Choice choice = MainFrame.getInstance().getLastChildChoice();
        if (choice == NewChildTypeDialog.Choice.PRESENTATION) {
            return new Presentation("Presentation", parent);
        } else if (choice == NewChildTypeDialog.Choice.SLIDE) {
            return new Slide("Slide", parent);
        }
        return null;
    }
}
