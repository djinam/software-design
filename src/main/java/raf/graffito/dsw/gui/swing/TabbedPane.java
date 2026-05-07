package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.observer.ISubscriber;

import javax.swing.*;
import java.awt.*;

@Getter @Setter
public class TabbedPane extends JTabbedPane implements ISubscriber {

    @Override
    public void update(Object notification) {
        if (notification instanceof Project project) {

        } else if (notification instanceof Presentation presentation) {
            removeTabForPresentation(presentation);
        }
    }

    private void removeTabForPresentation(Presentation presentation) {
        for (int i = 0; i < getTabCount(); i++) {
            if (getTitleAt(i).equals(presentation.getName())) {
                removeTabAt(i);
                break;
            }
        }
    }

    public void repaintTabsWithColor(String colorStr) {
        Color color = parseColorStringToColor(colorStr);
        Component selected = getSelectedComponent();

        if (selected instanceof JPanel panel) {
            panel.setBackground(color);
            panel.repaint();
        }
    }

    private Color parseColorStringToColor(String colorStr) {
        return switch (colorStr.toLowerCase()) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            case "yellow" -> Color.YELLOW;
            case "orange" -> Color.ORANGE;
            case "pink" -> Color.PINK;
            case "black" -> Color.BLACK;
            case "white" -> Color.WHITE;
            case "gray" -> Color.GRAY;
            case "cyan" -> Color.CYAN;
            case "magenta" -> Color.MAGENTA;
            default -> Color.LIGHT_GRAY;
        };
    }
}
