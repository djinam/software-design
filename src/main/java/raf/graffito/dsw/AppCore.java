package raf.graffito.dsw;

import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.gui.swing.IGui;
import raf.graffito.dsw.gui.swing.SwingGui;

public class AppCore {
    public static void main(String[] args) {
        ApplicationFramework appCore = ApplicationFramework.getInstance();
        IGui gui = new SwingGui();
        appCore.initialise(gui);
        appCore.initialize();
        appCore.run();
    }
}