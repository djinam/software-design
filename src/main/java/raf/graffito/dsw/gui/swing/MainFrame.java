package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import raf.graffito.dsw.controller.mediator.FullButton;
import raf.graffito.dsw.controller.mediator.NormalButton;
import raf.graffito.dsw.controller.mediator.Mediator;
import raf.graffito.dsw.controller.mediator.SmallButton;
import raf.graffito.dsw.controller.proxy.model.ImageGallery;
import raf.graffito.dsw.controller.proxy.view.ImageGalleryView;
import raf.graffito.dsw.core.ActionManager;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.messageGenerator.Message;
import raf.graffito.dsw.core.observer.ISubscriber;
import raf.graffito.dsw.gui.swing.slide.SlideView;
import raf.graffito.dsw.gui.swing.tree.GraffTreeImplementation;
import raf.graffito.dsw.gui.swing.tree.IGraffTree;

import javax.swing.*;
import java.awt.*;
@Getter
public class MainFrame extends JFrame implements ISubscriber{
    // Buduća polja za sve komponente view-a na glavnom prozoru

    private static MainFrame instance;
    private ActionManager actionManager;
    private IGraffTree graffTree;
    private TabbedPane tabbedPane;
    private AboutUsWindow aboutUsWindow;
    private NewChildTypeDialog.Choice lastChildChoice = NewChildTypeDialog.Choice.CANCEL;
    private TabColorView tabColorView;
    private MyToolBarRight myToolBarRight;

    private NormalButton normalButton;
    private FullButton fullscreenButton;
    private SmallButton smallButton;
    private JRadioButton normalRadio;

    private Mediator mediator;
    private ImageGallery imageGallery;
    private ImageGalleryView imageGalleryView;

    private MainFrame() {
    }


    private void initialise(){
        actionManager = new ActionManager();
        graffTree = new GraffTreeImplementation();
        //tabbedPane = new TabbedPane();
        tabColorView = new TabColorView();
        myToolBarRight = new MyToolBarRight();
        imageGallery = new ImageGallery();
        imageGalleryView = new ImageGalleryView();
        initGUI();
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    private void initGUI() {
        Toolkit kit = Toolkit.getDefaultToolkit(); // Toolkit omogućava interakciju sa platformom
        Dimension screenSize = kit.getScreenSize(); // Veličina ekrana
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationRelativeTo(null); // Centriranje prozora na ekranu
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Zatvaranje aplikacije pri zatvaranju prozora
        setTitle("Graffito"); // Naslov prozora

        // menu:
        MyMenuBar menu = new MyMenuBar(); // Kreiranje menija
        setJMenuBar(menu); // Postavljanje menija na prozor

        // toolbars:
        MyToolBar toolBar = new MyToolBar(); // Kreiranje toolbar-a
        add(toolBar, BorderLayout.NORTH); // Postavljanje toolbar-a na vrh prozora
        add(myToolBarRight, BorderLayout.EAST);

        // workspace tree:
        JTree workspace = graffTree.generateTree(ApplicationFramework.getInstance().getGraffRepository().getWorkspace());
        JScrollPane scroll = new JScrollPane(workspace);
        scroll.setMinimumSize(new Dimension(200, 150));

        // tabbed pane + split:
        tabbedPane = new TabbedPane();
        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scroll, tabbedPane);
        split.setDividerLocation(250);
        split.setOneTouchExpandable(true);
        getContentPane().add(split, BorderLayout.CENTER);

        // radio buttons:
        JPanel sizePanel = new JPanel();
        sizePanel.setLayout(new BoxLayout(sizePanel, BoxLayout.X_AXIS)); // vertical layout

        mediator = new Mediator();

        normalButton = new NormalButton(mediator);
        fullscreenButton = new FullButton(mediator);
        smallButton = new SmallButton(mediator);

        normalRadio = new JRadioButton("Normal");
        JRadioButton fullscreenRadio = new JRadioButton("FullScreen");
        JRadioButton smallRadio = new JRadioButton("Small");

        normalRadio.addActionListener(actionManager.getNormalAction());
        fullscreenRadio.addActionListener(actionManager.getFullAction());
        smallRadio.addActionListener(actionManager.getSmallAction());

        ButtonGroup group = new ButtonGroup();
        group.add(normalRadio);
        group.add(fullscreenRadio);
        group.add(smallRadio);

        sizePanel.add(normalRadio);
        sizePanel.add(fullscreenRadio);
        sizePanel.add(smallRadio);

        add(sizePanel, BorderLayout.SOUTH); // add panel at the bottom

        add(imageGalleryView, BorderLayout.WEST);

    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public IGraffTree getGraffTree() {
        return graffTree;
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof Message msg) {
            switch (msg.getMessageType()) {
                case GRESKA:
                    JOptionPane.showMessageDialog(this, msg.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    break;
                case OBAVESTENJE:
                    JOptionPane.showMessageDialog(this, msg.getMessage(), "Info", JOptionPane.INFORMATION_MESSAGE);
                    break;
            }
        }
    }

    public NewChildTypeDialog.Choice showNewChildTypeDialog() {
        NewChildTypeDialog dialog = new NewChildTypeDialog(this);
        dialog.setVisible(true);
        lastChildChoice = dialog.getChoice();
        return lastChildChoice;
    }

    public NewChildTypeDialog.Choice getLastChildChoice() {
        return lastChildChoice;
    }

    public TabbedPane getTabbedPane() {
        return tabbedPane;
    }
    public void showAboutUsWindow() {
        if (aboutUsWindow == null) {
            aboutUsWindow = new AboutUsWindow();
        }
        aboutUsWindow.setVisible(true);
    }

    public PresentationView getActivePresentationView() {
        if (tabbedPane == null) return null;

        Component selected = tabbedPane.getSelectedComponent();
        if (selected instanceof PresentationView pv) {
            return pv;
        }
        return null;
    }

    public Slide getActiveSlide() {
        PresentationView pv = getActivePresentationView();
        if (pv != null && pv.getActiveSlideView() != null) {
            return pv.getActiveSlideView().getSlide();
        }
        return null;
    }

    public void updateSlideSize(String event) {
        SlideView activeSlideView = getActivePresentationView().getActiveSlideView();
        if (activeSlideView == null) return;
        switch (event) {
            case "SIZE_SELECTED_NORMAL" -> activeSlideView.showNormal();
            case "SIZE_SELECTED_FULLSCREEN" -> activeSlideView.showFullscreen();
            case "SIZE_SELECTED_SMALL" -> activeSlideView.showSmall();
        }
    }

}
