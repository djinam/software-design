package raf.graffito.dsw.core.factory;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Slide;

import java.util.Random;

public class SlideFactory extends NodeFactory{
    @Override
    public GraffNode createNode(GraffNode parent) {
        Random rand = new Random();
        int randomNumber = rand.nextInt(100) + 1;
        return new Slide("Slide " + randomNumber, parent);
    }
}
