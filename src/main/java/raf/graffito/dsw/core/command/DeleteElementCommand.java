package raf.graffito.dsw.core.command;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.Slide;

public class DeleteElementCommand implements ICommand{
    /// remove command -> structure-based

    private Slide slide;
    private SlideElement element;

    public DeleteElementCommand(Slide slide, SlideElement element) {
        this.slide = slide;
        this.element = element;
    }

    @Override
    public void execute() {
        slide.removeChild(element);
    }

    public void undo() {
        slide.addChild(element); // add back at the end
    }
}
