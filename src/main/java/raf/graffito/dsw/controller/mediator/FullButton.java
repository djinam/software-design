package raf.graffito.dsw.controller.mediator;

public class FullButton extends ButtonColleague {

    public FullButton(IMediator mediator) {
        super(mediator);
    }

    @Override
    public void click() {
        mediator.notify(this, "SIZE_SELECTED_FULLSCREEN");
    }
}
