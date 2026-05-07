package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class AboutUsWindow extends JFrame {
    public AboutUsWindow() {
        initGUI();
    }
    private void initGUI() {
        setTitle("About Us");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        JPanel imagesPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        imagesPanel.setBackground(Color.WHITE);

        ImageIcon icon = new ImageIcon(getClass().getResource("/images/tulip.png"));
        ImageIcon icon2 = new ImageIcon(getClass().getResource("/images/sunflower.png"));

        Image scaled = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        Image scaled2 = icon2.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);

        ImageIcon scaledIcon = new ImageIcon(scaled);
        ImageIcon scaledIcon2 = new ImageIcon(scaled2);


        JLabel leftImage = new JLabel(scaledIcon, JLabel.CENTER);
        JLabel rightImage = new JLabel(scaledIcon2, JLabel.CENTER);
        imagesPanel.add(leftImage);
        imagesPanel.add(rightImage);

        JPanel namesPanel = new JPanel(new GridLayout(1, 2, 40, 0));
        namesPanel.setBackground(Color.WHITE);
        JLabel djinaLabel = new JLabel("Đina Mandić 90/24 RN", JLabel.CENTER);
        JLabel markoLabel = new JLabel("Marko Zorica 137/24 SI", JLabel.CENTER);

        djinaLabel.setFont(new Font("SansSerif", Font.BOLD, 18)); // yas
        markoLabel.setFont(new Font("SansSerif", Font.BOLD, 18));

        namesPanel.add(djinaLabel);
        namesPanel.add(markoLabel);

        mainPanel.add(imagesPanel, BorderLayout.CENTER);
        mainPanel.add(namesPanel, BorderLayout.SOUTH);

        add(mainPanel);
        setVisible(true);
    }
}
