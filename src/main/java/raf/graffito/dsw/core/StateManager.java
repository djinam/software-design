package raf.graffito.dsw.core;

import raf.graffito.dsw.controller.state.IState;
import raf.graffito.dsw.controller.state.stateConcrete.*;
import raf.graffito.dsw.gui.swing.MainFrame;

public class StateManager {
    private IState currentState;

    private MoveState moveState;
    private SelectState selectState;
    private AddLogoState addLogoState;
    private DeleteState deleteState;
    private ResizeState resizeState;
    private RotateRightState rotateRightState;
    private RotateLeftState rotateLeftState;
    private ZoomState zoomState;
    private AddTextState addTextState;

    public StateManager() {
        initStates();
    }

    private void initStates(){
        moveState = new MoveState();
        addLogoState = new AddLogoState();
        selectState = new SelectState();
        deleteState = new DeleteState();
        resizeState = new ResizeState();
        rotateRightState = new RotateRightState();
        rotateLeftState = new RotateLeftState();
        zoomState = new ZoomState();
        addTextState = new AddTextState();
    }

    public IState getCurrent(){
        return currentState;
    }

    public void setAddLogoState() {
        currentState = addLogoState;
    }

    public void setMoveState() {
        currentState = moveState;
    }

    public void setSelectState() {
        currentState = selectState;
    }

    public void setDeleteState() {
        currentState = deleteState;
    }

    public void setResizeState() {
        currentState = resizeState;
    }

    public void setRotateRightState() {
        currentState = rotateRightState;
    }

    public void setRotateLeftState() {
        currentState = rotateLeftState;
    }

    public void setZoomState() {
        currentState = zoomState;
    }

    public void setAddTextState() {
        currentState = addTextState;
    }

    public void setAddImageState(IState state) {
        currentState = state;
    }
}
