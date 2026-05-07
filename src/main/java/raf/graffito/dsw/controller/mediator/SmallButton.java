package raf.graffito.dsw.controller.mediator;

public class SmallButton extends ButtonColleague {

    public SmallButton(IMediator mediator) {
        super(mediator);
    }

    @Override
    public void click() {
        mediator.notify(this, "SIZE_SELECTED_SMALL");
    }
}
