package raf.graffito.dsw.controller.slideSpace;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;

public class SpaceManager {
    private final ISpaceChecker spaceChecker;

    public SpaceManager(ISpaceChecker initialChecker) {
        this.spaceChecker = initialChecker;
    }

    public boolean canAddElement(Slide slide, SlideElement element) {
        return spaceChecker.hasEnoughSpace(slide, element);
    }
}
