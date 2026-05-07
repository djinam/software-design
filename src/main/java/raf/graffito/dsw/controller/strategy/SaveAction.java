package raf.graffito.dsw.controller.strategy;

import raf.graffito.dsw.controller.AbstractGraffAction;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.abstraction.GraffNode;
import raf.graffito.dsw.core.composite.concrete.Workspace;
import raf.graffito.dsw.gui.swing.MainFrame;
import raf.graffito.dsw.gui.swing.tree.model.GraffTreeItem;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;

public class SaveAction extends AbstractGraffAction {
    private final JsonGraffNodeSerializer serializer;
    private File currentFile;

    public SaveAction() {
        this.serializer = new JsonGraffNodeSerializer();
        putValue(SMALL_ICON, loadIcon("/images/disk.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save & Save as");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffNode nodeToSave = getNodeToSave();
        if (nodeToSave == null) return;

        if (currentFile != null) {
            serializer.save(nodeToSave, currentFile);
        } else {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Save Node as JSON");
            fileChooser.setSelectedFile(new File(nodeToSave.getName().replaceAll("\\s+", "_") + ".json"));

            if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFile = fileChooser.getSelectedFile();
                if (!currentFile.getName().toLowerCase().endsWith(".json")) {
                    currentFile = new File(currentFile.getAbsolutePath() + ".json");
                }
                serializer.save(nodeToSave, currentFile);
            }
        }
    }

    private GraffNode getNodeToSave() {
        GraffTreeItem selectedItem = MainFrame.getInstance().getGraffTree().getSelectedNode();
        if (selectedItem != null) {
            return selectedItem.getGraffNode();
        }

        Workspace workspace = ApplicationFramework.getInstance().getGraffRepository().getWorkspace();
        return workspace;
    }

    public void setCurrentFile(File file) {
        this.currentFile = file;
    }
}
