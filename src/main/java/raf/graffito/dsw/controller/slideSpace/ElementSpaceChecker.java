package raf.graffito.dsw.controller.slideSpace;

import raf.graffito.dsw.controller.mediator.Mediator;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;


public class ElementSpaceChecker implements ISpaceChecker{
    public static final double MIN_FREE_SPACE = 0.2; // 20% free

    @Override
    public boolean hasEnoughSpace(Slide slide, SlideElement newElement) {
        Mediator mediator = MainFrame.getInstance().getMediator();
        Dimension slideDim = mediator.getCurrentSlideDimension();
        double totalSlideArea = slideDim.width * slideDim.height;

        double usedArea = 0;
        for (GraffNode node : slide.getChildren()) {
            if (node instanceof SlideElement e) {
                Dimension d = e.getDimension();
                usedArea += d.width * d.height;
            }
        }

        // include new element
        Dimension newDim = newElement.getDimension();
        usedArea += newDim.width * newDim.height;
        System.out.println("Used Area: " + usedArea);

        double freeRatio = (totalSlideArea - usedArea) / totalSlideArea;
        return freeRatio >= ElementSpaceChecker.MIN_FREE_SPACE;
    }
}
