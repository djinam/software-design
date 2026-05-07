package raf.graffito.dsw.controller.proxy.model;

import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageProxy implements IImage {
    private ImageLoad realImage;
    private File file;
    private ImageIcon thumbnail;
    private BufferedImage realImageBuff;

    public ImageProxy(File file) {
        this.file = file;
        this.thumbnail = createThumbnail(file);
    }

    private ImageIcon createThumbnail(File file) {
        try {
            BufferedImage img = ImageIO.read(file);

            if (img == null) {
                System.err.println("File is not a supported image: " + file.getName());
                return getFallbackThumbnail();
            }

            Image scaled = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);

        } catch (IOException e) {
            System.err.println("Failed to read image: " + file.getName());
            return getFallbackThumbnail();
        }
    }

    private ImageIcon getFallbackThumbnail() {
        return (ImageIcon) UIManager.getIcon("OptionPane.warningIcon");
    }

    public ImageIcon getThumbnail() {
        return thumbnail;
    }


    @Override
    public void display() {
        // KONTROLA PRISTUPA: Lazy Loading
        if (realImage == null) {
            System.out.println("-> Proxy aktivira Učitavanje stvarne slike (Lazy Loading)...");
            realImage = new ImageLoad(file);
        }
        realImage.display();
    }

    public BufferedImage getRealImageBuff() {
        if (realImageBuff == null) {
            try {
                realImageBuff = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return realImageBuff;
    }

    public File getFile() {
        return file;
    }
}
