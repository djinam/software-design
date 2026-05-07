package raf.graffito.dsw.core.command;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class UndoRedoManager {
    private final Stack<ICommand> undoStack = new Stack<>();
    private final List<ICommand> redoList = new ArrayList<>();

    public void executeCommand(ICommand command) {
        command.execute();
        undoStack.push(command);
        redoList.clear(); // new command invalidates redo
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            ICommand command = undoStack.pop();
            command.undo();
            redoList.add(command); // add to redo list
        }
    }

    public void redo() {
        if (!redoList.isEmpty()) {
            ICommand command = redoList.removeLast();
            command.execute();
            undoStack.push(command); // back to undo stack
        }
    }

    public boolean canUndo() {
        return !undoStack.isEmpty();
    }

    public boolean canRedo() {
        return !redoList.isEmpty();
    }
}
