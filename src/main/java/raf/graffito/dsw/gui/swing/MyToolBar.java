package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.*;
import raf.graffito.dsw.controller.proxy.controller.ImageAction;
import raf.graffito.dsw.controller.strategy.OpenAction;
import raf.graffito.dsw.controller.strategy.SaveAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction exitAction = MainFrame.getInstance().getActionManager().getExitAction();
        AboutUsAction aboutUs = MainFrame.getInstance().getActionManager().getAboutUsAction();
        NewProjectAction newProjectAction = MainFrame.getInstance().getActionManager().getNewProjectAction();
        RemoveAction removeAction = MainFrame.getInstance().getActionManager().getRemoveAction();
        TabColorController tabColorController = MainFrame.getInstance().getActionManager().getTabColorController();
        SaveAction saveAction = MainFrame.getInstance().getActionManager().getSaveAction();
        OpenAction openAction = MainFrame.getInstance().getActionManager().getOpenAction();
        ImageAction imageAction = MainFrame.getInstance().getActionManager().getImageAction();
        add(exitAction);
        add(aboutUs);
        add(newProjectAction);
        add(removeAction);
        add(tabColorController);
        add(saveAction);
        add(openAction);
        add(imageAction);
    }
}
