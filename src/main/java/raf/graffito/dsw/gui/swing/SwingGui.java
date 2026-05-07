package raf.graffito.dsw.gui.swing;

public class SwingGui implements IGui {
    private MainFrame instance;

    public SwingGui() {
    }

    @Override
    public void start() {
        instance = MainFrame.getInstance();
        instance.setVisible(true);
    }
}
