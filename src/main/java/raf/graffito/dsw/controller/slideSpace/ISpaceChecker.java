package raf.graffito.dsw.controller.slideSpace;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;

public interface ISpaceChecker {
    double MIN_FREE_SPACE = 0.2; // 20% free
    boolean hasEnoughSpace(Slide slide, SlideElement newElement);
}
