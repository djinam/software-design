package raf.graffito.dsw.controller.slideSpace;

import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;


public class PixelSpaceChecker implements ISpaceChecker{

    public static final double MIN_FREE_SPACE = 0.2; // 20% free

    public boolean hasEnoughSpace(Slide slide, SlideElement newElement) {

        Dimension slideDim = MainFrame.getInstance().getMediator().getCurrentSlideDimension();
        int width = slideDim.width;
        int height = slideDim.height;

        // create a binary pixel matrix for the slide
        boolean[][] pixels = new boolean[width][height];

        // mark pixels occupied by existing SlideElements
        for (GraffNode node : slide.getChildren()) {
            if (node instanceof SlideElement e) {
                Point loc = e.getLocation();
                Dimension dim = e.getDimension();

                int xStart = Math.max(0, loc.x);
                int yStart = Math.max(0, loc.y);
                int xEnd = Math.min(xStart + dim.width, width);
                int yEnd = Math.min(yStart + dim.height, height);

                for (int x = xStart; x < xEnd; x++) {
                    for (int y = yStart; y < yEnd; y++) {
                        pixels[x][y] = true;
                    }
                }
            }
        }

        // count free pixels
        int freePixels = 0;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!pixels[x][y]) freePixels++;
            }
        }

        int totalPixels = width * height;
        double freeRatio = (double) freePixels / totalPixels;

        // return true if free pixels >= min_free
        return freeRatio >= MIN_FREE_SPACE;
    }
}
