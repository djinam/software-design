package raf.graffito.dsw.core.command;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;

public class AddElementCommand implements ICommand{
    /// add command -> structure-based

    private Slide slide;
    private SlideElement element;

    public AddElementCommand(Slide slide, SlideElement element) {
        this.slide = slide;
        this.element = element;
    }

    @Override
    public void execute() {
        slide.addChild(element);
    }

    @Override
    public void undo() {
        slide.removeChild(element);
    }
}
