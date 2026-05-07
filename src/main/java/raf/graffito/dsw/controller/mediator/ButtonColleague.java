package raf.graffito.dsw.controller.mediator;

public abstract class ButtonColleague {
    protected IMediator mediator;

    public ButtonColleague(IMediator mediator) {
        this.mediator = mediator;
    }

    public abstract void click();
}
