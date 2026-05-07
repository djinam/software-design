package raf.graffito.dsw.core.command;

public interface ICommand {
    void execute();
    void undo();
}
