//package raf.graffito.dsw.core.factory;
//
//import raf.graffito.dsw.core.composite.abstraction.SlideElement;
//import raf.graffito.dsw.core.composite.concrete.ImageElement;
//import raf.graffito.dsw.core.composite.concrete.LogoElement;
//import raf.graffito.dsw.core.composite.concrete.Slide;
//import raf.graffito.dsw.core.composite.concrete.TextElement;
//
//import java.awt.*;
//import java.awt.image.BufferedImage;
//
//public class SlideElementFactory {
//
//    public static SlideElement createElement(String type, Slide parent, Object content, Point location, Dimension dimension) {
//        switch (type.toLowerCase()) {
//            case "image":
//                return new ImageElement("image", parent, (BufferedImage) content);
//            case "logo":
//                return new LogoElement("logo", parent, (BufferedImage) content);
//            case "text":
//                return new TextElement("text", parent, location, dimension, (String) content);
//            default:
//                throw new IllegalArgumentException("Unknown element type: " + type);
//        }
//    }
//}
