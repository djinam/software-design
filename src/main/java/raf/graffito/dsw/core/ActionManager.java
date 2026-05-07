package raf.graffito.dsw.core;

import lombok.Getter;
import raf.graffito.dsw.controller.*;
import raf.graffito.dsw.controller.proxy.controller.ImageAction;
import raf.graffito.dsw.controller.state.stateAction.*;
import raf.graffito.dsw.controller.strategy.OpenAction;
import raf.graffito.dsw.controller.strategy.SaveAction;
import raf.graffito.dsw.controller.undoRedoAction.RedoAction;
import raf.graffito.dsw.controller.undoRedoAction.UndoAction;
import raf.graffito.dsw.controller.mediator.action.FullAction;
import raf.graffito.dsw.controller.mediator.action.NormalAction;
import raf.graffito.dsw.controller.mediator.action.SmallAction;

@Getter
public class ActionManager {

    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewProjectAction newProjectAction;
    private RemoveAction removeAction;
    private TabColorController tabColorController;
   // private ImageElementAction imageElementAction;
    private SaveAction saveAction;
    private OpenAction openAction;

    private MoveAction moveAction;
    private SelectAction selectAction;
    private DeleteAction deleteAction;
    private ResizeAction resizeAction;
    private RotateRightAction rotateRightAction;
    private RotateLeftAction rotateLeftAction;
    private ZoomAction zoomAction;
    private AddLogoAction addLogoAction;
    private AddTextAction addTextAction;
    private UndoAction undoAction;
    private CloneAction cloneAction;
    private RedoAction redoAction;
    private AddImageAction addImageAction;

    private FullAction fullAction;
    private NormalAction normalAction;
    private SmallAction smallAction;

    private ImageAction imageAction;

    public ActionManager() {
        initActions();
    }

    private void initActions() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newProjectAction = new NewProjectAction();
        removeAction = new RemoveAction();
        tabColorController = new TabColorController();
        //imageElementAction = new ImageElementAction();
        moveAction = new MoveAction();
        selectAction = new SelectAction();
        deleteAction = new DeleteAction();
        resizeAction = new ResizeAction();
        rotateRightAction = new RotateRightAction();
        rotateLeftAction = new RotateLeftAction();
        zoomAction = new ZoomAction();
        addLogoAction = new AddLogoAction();
        addTextAction = new AddTextAction();
        undoAction = new UndoAction();
        cloneAction = new CloneAction();
        redoAction = new RedoAction();
        fullAction = new FullAction();
        normalAction = new NormalAction();
        smallAction = new SmallAction();
        saveAction = new SaveAction();
        openAction = new OpenAction();
        imageAction = new ImageAction();
        addImageAction = new AddImageAction();
    }

}
