package raf.graffito.dsw.core;
import lombok.Getter;
import raf.graffito.dsw.core.command.UndoRedoManager;
import raf.graffito.dsw.core.composite.GraffRepositoryImplementation;

import raf.graffito.dsw.core.messageGenerator.MessageGenerator;
import raf.graffito.dsw.core.simpleFactory.ILogger;
import raf.graffito.dsw.core.simpleFactory.LoggerFactory;
import raf.graffito.dsw.gui.swing.IGui;
import raf.graffito.dsw.gui.swing.MainFrame;

@Getter
public class ApplicationFramework {
    // Buduća polja za model celog projekta

    private static ApplicationFramework instance;
    private MessageGenerator messageGenerator;
    private ILogger consoleLogger;
    private ILogger fileLogger;
    private GraffRepositoryImplementation graffRepository;
    private IGui gui;
    private TabColorModel tabColorModel;
    private SlideSelectionModel slideSelectionModel;
    private UndoRedoManager undoRedoManager;

    private ApplicationFramework(){
        graffRepository = new GraffRepositoryImplementation();
        messageGenerator = new MessageGenerator();
        consoleLogger = LoggerFactory.createLogger("console");
        fileLogger = LoggerFactory.createLogger("file");
        messageGenerator.addSubscriber(consoleLogger);
        messageGenerator.addSubscriber(fileLogger);
        messageGenerator.addSubscriber(MainFrame.getInstance());
        graffRepository.addSubscriber(MainFrame.getInstance().getTabbedPane());
        tabColorModel = new TabColorModel();
        slideSelectionModel = new SlideSelectionModel();
        undoRedoManager = new UndoRedoManager();

    }

    public void run(){
        this.gui.start();
    }

    public void initialise(IGui gui)
    {
        this.gui = gui;
        //this.graffRepository = graffRepository;
    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }

    public void initialize(){
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public GraffRepositoryImplementation getGraffRepository() {
        return graffRepository;
    }
}
