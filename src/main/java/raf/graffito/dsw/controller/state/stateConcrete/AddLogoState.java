package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.slideSpace.ElementSpaceChecker;
import raf.graffito.dsw.controller.slideSpace.SpaceManager;
import raf.graffito.dsw.controller.state.BaseState;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.command.AddElementCommand;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class AddLogoState extends BaseState {

    private final SpaceManager spaceManager = new SpaceManager(new ElementSpaceChecker());

    public void mouseClicked(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null) return;

        // create image
        BufferedImage img = createTestImage();

        // create ImageElement at click position
        Point clickPoint = e.getPoint();
        SlideElement element = new ImageElement("img", slide, img);
        element.setLocation(clickPoint);

        if (!spaceCheck(slide, element)) return;

        addElement(slide, element);

       /// slide.addChild(element); - covered with addElementCommand
    }

    private void addElement(Slide slide, SlideElement element) {
        ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(new AddElementCommand(slide, element));

        System.out.println("logo added");
        slide.notifySubscribers(this);
    }

    private boolean spaceCheck(Slide slide, SlideElement element) {
        if (spaceManager.canAddElement(slide, element)) {
            return true;
        }

        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Not enough space on the slide to add this element!", MessageType.GRESKA);
        return false;
    }

    private BufferedImage createTestImage() {
        int width = 200;
        int height = 150;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();

        // smooth rendering
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // gradient background
        GradientPaint gradient = new GradientPaint(0, 0, Color.CYAN, width, height, Color.MAGENTA);
        g.setPaint(gradient);
        g.fillRect(0, 0, width, height);

        // star shape
        g.setColor(Color.YELLOW);
        int[] xPoints = {100, 120, 180, 130, 150, 100, 50, 70, 20, 80};
        int[] yPoints = {10, 60, 60, 100, 150, 120, 150, 100, 60, 60};
        g.fillPolygon(xPoints, yPoints, xPoints.length);

        g.dispose();
        return image;
    }
}
