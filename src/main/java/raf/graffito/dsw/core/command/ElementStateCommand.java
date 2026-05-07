package raf.graffito.dsw.core.command;

import raf.graffito.dsw.core.composite.abstraction.SlideElement;

public class ElementStateCommand implements ICommand {
    /// move, resize, rotate command -> snapshot-based (captures state)

    private SlideElement element;
    private ElementSnapshot previousState;
    private ElementSnapshot newState;

    public ElementStateCommand(SlideElement element, ElementSnapshot previousState) {
        this.element = element;
        this.previousState = previousState;
    }

    @Override
    public void execute() {
        if (newState != null) {
            element.restoreSnapshot(newState);
        }
    }

    public void captureAfter() {
        this.newState = element.createSnapshot();
    }

    @Override
    public void undo() {
        element.restoreSnapshot(previousState);
    }
}
