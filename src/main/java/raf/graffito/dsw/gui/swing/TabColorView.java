package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class TabColorView extends JFrame {
    private JTextField colorField;
    private JButton saveButton;
    private JLabel promptLabel;

    public TabColorView() {
        initGUI();
    }

    private void initGUI() {
        setTitle("Tab Color Window");
        setSize(400, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 1, 5, 5));

        promptLabel = new JLabel("Enter color for tabs ");
        colorField = new JTextField();
        saveButton = new JButton("Save and Close");

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(promptLabel);
        panel.add(colorField);
        panel.add(saveButton);

        add(panel);
    }

    public JTextField getColorField() {
        return colorField;
    }

    public void addSaveButtonListener(Runnable action) {
        saveButton.addActionListener(e -> action.run());
    }

}
