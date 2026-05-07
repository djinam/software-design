package raf.graffito.dsw.controller.state.stateConcrete;

import raf.graffito.dsw.controller.proxy.model.ImageProxy;
import raf.graffito.dsw.controller.slideSpace.PixelSpaceChecker;
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

public class AddImageState extends BaseState {
    private final SpaceManager spaceManager = new SpaceManager(new PixelSpaceChecker());

    private final ImageProxy proxy;

    public AddImageState(ImageProxy proxy) {
        this.proxy = proxy;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Slide slide = MainFrame.getInstance().getActiveSlide();
        if (slide == null || proxy == null) return;

        BufferedImage realImage = proxy.getRealImageBuff();
        if (realImage == null) return;

        Point clickPoint = e.getPoint();
        SlideElement element = new ImageElement(proxy.getFile().getName(), slide, realImage);
        element.setLocation(clickPoint);

        if (!spaceCheck(slide, element)) return;

        addElement(slide, element);
    }

    private void addElement(Slide slide, SlideElement element) {
        ApplicationFramework.getInstance().getUndoRedoManager().executeCommand(new AddElementCommand(slide, element));
        slide.notifySubscribers(this);
    }

    private boolean spaceCheck(Slide slide, SlideElement element) {
        if (spaceManager.canAddElement(slide, element)) return true;

        ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Not enough space on the slide to add this element!", MessageType.GRESKA);
        return false;
    }
}
