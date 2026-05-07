package raf.graffito.dsw.controller;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.TabColorModel;
import raf.graffito.dsw.core.messageGenerator.MessageType;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.TabColorView;

import java.awt.*;
import java.awt.event.ActionEvent;

public class TabColorController extends AbstractGraffAction{

    public TabColorController() {
        putValue(SMALL_ICON, loadIcon("/images/bee.png"));
        putValue(NAME, "Tab Color");
        putValue(SHORT_DESCRIPTION, "Tab Color");
    }

    public void actionPerformed(ActionEvent e) {
        MainFrame mainFrame = MainFrame.getInstance();
        TabColorView view = mainFrame.getTabColorView();
        TabColorModel model = ApplicationFramework.getInstance().getTabColorModel();

        view.setVisible(true);

        view.addSaveButtonListener(() -> { // attach save button listener
            String input = view.getColorField().getText().trim().toLowerCase();
            if (input.isEmpty()) return;

            switch (input) {
                case "red", "green", "blue", "yellow", "orange", "pink", "black", "white", "gray", "purple" -> {
                    model.setSelectedColorString(input); // store in model
                    mainFrame.getTabbedPane().repaintTabsWithColor(input); // repaint tabs
                    view.dispose(); // close window
                }
                default -> ApplicationFramework.getInstance().getMessageGenerator().generateMessage("Invalid color", MessageType.UPOZORENJE);
            }
        });
    }


}
