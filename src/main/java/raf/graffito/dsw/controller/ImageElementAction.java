//package raf.graffito.dsw.controller;
//
//import raf.graffito.dsw.core.ApplicationFramework;
//import raf.graffito.dsw.core.composite.abstraction.SlideElement;
//import raf.graffito.dsw.core.composite.concrete.ImageElement;
//import raf.graffito.dsw.core.composite.concrete.Slide;
//import raf.graffito.dsw.core.messageGenerator.MessageType;
//import raf.graffito.dsw.gui.swing.MainFrame;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.image.BufferedImage;
//
//
//public class ImageElementAction extends AbstractGraffAction {
//
//    public ImageElementAction() {
//        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
//        putValue(SMALL_ICON, loadIcon("/images/hacker.png"));
//        putValue(NAME, "About us");
//        putValue(SHORT_DESCRIPTION, "About us");
//    }
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        Slide slide = MainFrame.getInstance().getActiveSlide();
//        if(slide == null){
//            ApplicationFramework.getInstance().getMessageGenerator().generateMessage("NO SLIDE OPENED", MessageType.GRESKA);
//            return;
//        }
//        BufferedImage img = createTestImage();
//        SlideElement element = new ImageElement("img", slide, img);
//
//        slide.addChild(element);
//    }
//
//    private BufferedImage createTestImage() {
//        BufferedImage image = new BufferedImage(200, 150, BufferedImage.TYPE_INT_RGB);
//        Graphics2D g = image.createGraphics();
//        g.setColor(Color.BLUE);
//        g.fillRect(0, 0, 200, 150);
//        g.setColor(Color.YELLOW);
//        g.fillOval(60, 60, 80, 40);
//        g.dispose();
//
//        return image;
//    }
//
//}
