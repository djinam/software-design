package raf.graffito.dsw.controller.proxy.model;

import javax.swing.*;
import java.io.File;

public class ImageLoad implements IImage {
    private File file;
    private ImageIcon imageIcon;

    public ImageLoad(File file) {
        this.file = file;
        loadImage();
    }

    private void loadImage() {
        imageIcon = new ImageIcon(file.getAbsolutePath());
        System.out.println("Loaded full image from disk: " + file.getName());
    }


    @Override
    public void display() { //i know:(
        if (imageIcon != null) {
            JFrame frame = new JFrame(file.getName());
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JLabel label = new JLabel(imageIcon);
            frame.add(new JScrollPane(label));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        } else {
            System.out.println("Cannot display image: " + file.getName());
        }
    }
}
