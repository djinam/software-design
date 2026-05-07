package raf.graffito.dsw.gui.swing.slide;

import lombok.Getter;
import raf.graffito.dsw.controller.proxy.model.ImageProxy;
import raf.graffito.dsw.controller.state.stateConcrete.AddImageState;
import raf.graffito.dsw.core.composite.abstraction.SlideElement;
import raf.graffito.dsw.core.composite.concrete.ImageElement;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.composite.concrete.TextElement;
import raf.graffito.dsw.core.observer.ISubscriber;
import raf.graffito.dsw.controller.state.IState;
import raf.graffito.dsw.core.StateManager;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

@Getter
public class SlideView extends JPanel implements ISubscriber {

    private final Slide slide;
    private final StateManager stateManager;
    private AffineTransform viewTransform = new AffineTransform();
    private double scale = 1.0;
    private Dimension normalSize = new Dimension(800, 600);
    private Dimension smallSize = new Dimension(400, 300);
    private String currentMode = "NORMAL";

    public SlideView(Slide slide) {
        this.slide = slide;
        slide.addSubscriber(this);

        setBackground(Color.pink);
        setLayout(new BorderLayout());

        stateManager = new StateManager();
    }

    @Override
    public void update(Object notification) {
            repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        AffineTransform oldGlobal = g2.getTransform();
        g2.transform(viewTransform); // APPLY ZOOM HERE

        for (var child : slide.getChildren()) {
            if (child instanceof SlideElement elem) {
                Point loc = elem.getLocation();
                Dimension dim = elem.getDimension();
                double angle = elem.getRotationAngle();

                AffineTransform oldLocal = g2.getTransform();
                g2.rotate(angle, loc.x + dim.width / 2.0, loc.y + dim.height / 2.0);

                if (elem instanceof ImageElement imgElem) {
                    BufferedImage img = imgElem.getImage();
                    if (img != null) {
                        g2.drawImage(img, loc.x, loc.y, dim.width, dim.height, null);
                    }
                } else if (elem instanceof TextElement txtElem) {
                    g2.drawString(txtElem.getText(), loc.x, loc.y + dim.height / 2);
                }

                g2.setTransform(oldLocal); // reset rotation
            }
        }

        g2.setTransform(oldGlobal); // reset zoom
    }


    public void zoom(double factor) {
        scale *= factor;

        Dimension size = getSize();
        viewTransform.translate(size.width / 2.0, size.height / 2.0);
        viewTransform.scale(factor, factor);
        viewTransform.translate(-size.width / 2.0, -size.height / 2.0);

        repaint();
    }

    private void updateScale() { // elements scaled proportionally when the slide size changes
        Dimension currentSize = getPreferredSize();
        double scaleX = currentSize.width / 800.0; // original width
        double scaleY = currentSize.height / 600.0; // original height
        scale = Math.min(scaleX, scaleY);

        viewTransform = new AffineTransform();
        viewTransform.scale(scale, scale);
    }

    public void startAddLogoState(){
        this.stateManager.setAddLogoState();
        installState(stateManager.getCurrent());
    }

    public void startMoveState(){
        this.stateManager.setMoveState();
        installState(stateManager.getCurrent());
    }

    public void startSelectState(){
        this.stateManager.setSelectState();
        installState(stateManager.getCurrent());
    }

    public void startDeleteState(){
        this.stateManager.setDeleteState();
        installState(stateManager.getCurrent());
    }

    public void startResizeState(){
        this.stateManager.setResizeState();
        installState(stateManager.getCurrent());
    }

    public void startRotateRightState(){
        this.stateManager.setRotateRightState();
        installState(stateManager.getCurrent());
    }

    public void startRotateLeftState(){
        this.stateManager.setRotateLeftState();
        installState(stateManager.getCurrent());
    }

    public void startZoomState(){
        this.stateManager.setZoomState();
        installState(stateManager.getCurrent());
    }

    public void startAddTextState(){
        this.stateManager.setAddTextState();
        installState(stateManager.getCurrent());
    }

    public void startAddImageState(ImageProxy proxy) {
        AddImageState state = new AddImageState(proxy);
        this.stateManager.setAddImageState(state);
        installState(state);
    }

    private void installState(IState state) {
        // remove old listeners
        for (MouseListener ml : getMouseListeners()) {
            removeMouseListener(ml);
        }
        for (MouseMotionListener mml : getMouseMotionListeners()) {
            removeMouseMotionListener(mml);
        }
        for (MouseWheelListener mwl : getMouseWheelListeners()) {
            removeMouseWheelListener(mwl);
        }

        // add new state as listener
        addMouseListener(state);
        addMouseMotionListener(state);
        addMouseWheelListener(state);
    }

    public void showTextInput(Point p, Consumer<String> input) {
        JTextField field = new JTextField();
        field.setBounds(p.x, p.y, 200, 30);
        setLayout(null);
        add(field);
        field.requestFocus();

        field.addActionListener(e -> {
            input.accept(field.getText());
            remove(field);
        });
    }

    public void showNormal() {
        setSlideSize(normalSize);
        currentMode = "NORMAL";
    }

    public void showFullscreen() {
        JFrame main = MainFrame.getInstance();

        // remove all existing components from the main window
        main.getContentPane().removeAll();

        // add a new SlideView for fullscreen
        SlideView fullscreenView = new SlideView(this.slide);
        main.getContentPane().add(fullscreenView, BorderLayout.CENTER);

        main.revalidate();
        main.repaint();
        currentMode = "FULLSCREEN";
    }

    public void showSmall() {
        setSlideSize(smallSize);
        currentMode = "SMALL";
    }

    private void setSlideSize(Dimension size) {
        setPreferredSize(size);
        updateScale();
        revalidate();
        repaint();
    }

}

