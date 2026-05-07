package raf.graffito.dsw.gui.swing;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
@Getter
public class MyToolBarRight extends JToolBar {
    private final JButton selectButton;
    private final JButton zoomButton;
    private final JButton moveButton;
    private final JButton deleteButton;
    private final JButton resizeButton;
    private final JButton rotateLeftButton;
    private final JButton rotateRightButton;
    private final JButton addLogoButton;
    private final JButton addTextButton;
    private final JButton undoButton;
    private final JButton cloneButton;
    private final JButton redoButton;

    public MyToolBarRight() {
        super(VERTICAL);
        setFloatable(false);
        setPreferredSize(new Dimension(120, 0));
        setBackground(new Color(200, 200, 200));
        setLayout(new GridLayout(0, 1, 5, 5));

        selectButton = new JButton("Select");
        zoomButton = new JButton("Zoom");
        moveButton = new JButton("Move");
        deleteButton = new JButton("Delete");
        resizeButton = new JButton("Resize");
        rotateLeftButton = new JButton("RotateLeft");
        rotateRightButton = new JButton("RotateRight");
        addLogoButton = new JButton("Add Logo");
        addTextButton = new JButton("Add Text");
        undoButton = new JButton("Undo");
        cloneButton = new JButton("Clone");
        redoButton = new JButton("Redo");

        add(selectButton);
        add(zoomButton);
        add(moveButton);
        add(deleteButton);
        add(resizeButton);
        add(rotateLeftButton);
        add(rotateRightButton);
        add(addLogoButton);
        add(addTextButton);
        add(cloneButton);
        add(undoButton);
        add(redoButton);

        //ImageElementAction imageElementAction = MainFrame.getInstance().getActionManager().getImageElementAction();
        //add(imageElementAction);

        moveButton.addActionListener(MainFrame.getInstance().getActionManager().getMoveAction());
        zoomButton.addActionListener(MainFrame.getInstance().getActionManager().getZoomAction());
        selectButton.addActionListener(MainFrame.getInstance().getActionManager().getSelectAction());
        deleteButton.addActionListener(MainFrame.getInstance().getActionManager().getDeleteAction());
        resizeButton.addActionListener(MainFrame.getInstance().getActionManager().getResizeAction());
        rotateRightButton.addActionListener(MainFrame.getInstance().getActionManager().getRotateRightAction());
        rotateLeftButton.addActionListener(MainFrame.getInstance().getActionManager().getRotateLeftAction());
        addLogoButton.addActionListener(MainFrame.getInstance().getActionManager().getAddLogoAction());
        addTextButton.addActionListener(MainFrame.getInstance().getActionManager().getAddTextAction());
        undoButton.addActionListener(MainFrame.getInstance().getActionManager().getUndoAction());
        cloneButton.addActionListener(MainFrame.getInstance().getActionManager().getCloneAction());
        redoButton.addActionListener(MainFrame.getInstance().getActionManager().getRedoAction());
    }

}
