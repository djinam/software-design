package raf.graffito.dsw.controller.proxy.controller;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.controller.proxy.model.ImageGallery;
import raf.graffito.dsw.controller.proxy.model.ImageProxy;
import raf.graffito.dsw.controller.proxy.view.ImageGalleryView;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class ImageAction extends AbstractGraffAction {

    public ImageAction() {
        putValue(SMALL_ICON, loadIcon("/images/upload.png"));
        putValue(NAME, "Dodaj Sliku");
        putValue(SHORT_DESCRIPTION, "Učitaj sliku sa diska");
    }

    public void actionPerformed(ActionEvent e) {
        ImageGallery model = MainFrame.getInstance().getImageGallery();
        ImageGalleryView view =  MainFrame.getInstance().getImageGalleryView();
        if (model == null || view == null) {
            System.err.println("ImageAction not initialized correctly!");
            return;
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setMultiSelectionEnabled(true);
        chooser.setAcceptAllFileFilterUsed(false);
        chooser.addChoosableFileFilter(
                new javax.swing.filechooser.FileNameExtensionFilter(
                        "Images", "jpg", "jpeg", "png", "bmp", "gif"
                )
        );

        if (chooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            for (File file : chooser.getSelectedFiles()) {
                ImageProxy proxy = new ImageProxy(file);
                model.addImage(proxy);
                view.addThumbnail(proxy);
            }
        }
    }
}
