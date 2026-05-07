package raf.graffito.dsw.controller.mediator;

public class NormalButton extends ButtonColleague {
    public NormalButton(IMediator mediator) {
        super(mediator);
    }

    @Override
    public void click() {
        mediator.notify(this, "SIZE_SELECTED_NORMAL");
    }
}