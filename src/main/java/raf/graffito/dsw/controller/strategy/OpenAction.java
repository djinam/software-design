package raf.graffito.dsw.controller.strategy;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class OpenAction extends AbstractGraffAction {
    private JsonGraffNodeSerializer serializer;

    public OpenAction() {
        this.serializer = new JsonGraffNodeSerializer();
        putValue(NAME, "Open");
        putValue(SMALL_ICON, loadIcon("/images/open-folder.png"));
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveAction saveAction = MainFrame.getInstance().getActionManager().getSaveAction();

        JFileChooser chooser = new JFileChooser();
        if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) return;

        File file = chooser.getSelectedFile();
        GraffNode loadedNode = serializer.load(file);
        if (loadedNode == null) {
            JOptionPane.showMessageDialog(null, "Failed to load file!");
            return;
        }

        Workspace workspace;
        if (loadedNode instanceof Workspace w) {
            workspace = w;
        } else {
            workspace = new Workspace("Loaded Workspace");
            workspace.addChild(loadedNode);
        }

        // restore parent links recursively
        restoreParentReferences(workspace);

        // regenerate tree in GUI
        MainFrame.getInstance().getGraffTree().generateTree(workspace);

        // update Save path only if loading succeeded
        saveAction.setCurrentFile(file);
    }

    // Helper method to fix parent links recursively
    private void restoreParentReferences(GraffNode node) {
        if (node instanceof raf.graffito.dsw.core.composite.abstraction.GraffNodeComposite composite) {
            for (GraffNode child : composite.getChildren()) {
                child.setParent(node);
                restoreParentReferences(child);
            }
        }
    }
}
