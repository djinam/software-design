package raf.graffito.dsw.controller.proxy.view;


import raf.graffito.dsw.controller.proxy.model.ImageProxy;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import javax.swing.*;
import java.awt.*;

public class ImageGalleryView extends JPanel {
    private JPanel thumbnailPanel;

    public ImageGalleryView() {
        setLayout(new BorderLayout());

        thumbnailPanel = new JPanel();
        thumbnailPanel.setLayout(new BoxLayout(thumbnailPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(thumbnailPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        add(scrollPane, BorderLayout.CENTER);
    }

    public void addThumbnail(ImageProxy proxy) {
        JLabel label = new JLabel(proxy.getThumbnail());
        label.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        label.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) { // i know:(
                SlideView slideView = MainFrame.getInstance().getActivePresentationView().getActiveSlideView();

                slideView.startAddImageState(proxy);            }
        });

        thumbnailPanel.add(label);
        thumbnailPanel.revalidate();
        thumbnailPanel.repaint();

        this.revalidate();
        this.repaint();

        SwingUtilities.getWindowAncestor(this).revalidate();
        SwingUtilities.getWindowAncestor(this).repaint();
    }
}
