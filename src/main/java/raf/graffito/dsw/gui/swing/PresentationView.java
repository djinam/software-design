package raf.graffito.dsw.gui.swing;

import lombok.Getter;
import lombok.Setter;
//import raf.graffito.dsw.controller.SlideMouseController;
import raf.graffito.dsw.controller.SlideMouseController;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.composite.concrete.Presentation;
import raf.graffito.dsw.core.composite.concrete.Project;
import raf.graffito.dsw.core.composite.concrete.Slide;
import raf.graffito.dsw.core.observer.ISubscriber;
import raf.graffito.dsw.gui.swing.slide.ISlideHost;
import raf.graffito.dsw.gui.swing.slide.SlideView;

import javax.swing.*;
import java.awt.*;
@Getter @Setter
public class PresentationView extends JPanel implements ISubscriber, ISlideHost {
    private Presentation presentation;
    private Project project;
    private JLabel projectLabel;
    private JLabel authorLabel;
    private JLabel slideCountLabel;

    private JPanel headerPanel;
    private JPanel slideContainer;

    private SlideView activeSlideView;

    public PresentationView(Presentation presentation, Project project) {
        this.presentation = presentation;
        this.project = project;

        presentation.addSubscriber(this);
        project.addSubscriber(this);

        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(230, 245, 255));

        headerPanel = new JPanel(new GridLayout(1, 3, 10, 0));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        headerPanel.setBackground(new Color(210, 235, 250));

        projectLabel = new JLabel("Project: " + project.getTitle());
        authorLabel = new JLabel("Author: " + project.getAuthor());
        slideCountLabel = new JLabel("Slides: " +
                presentation.getChildren().stream()
                        .filter(c -> c instanceof Slide)
                        .count()
        );

        headerPanel.add(projectLabel);
        headerPanel.add(authorLabel);
        headerPanel.add(slideCountLabel);

        slideContainer = new JPanel(new FlowLayout());
        slideContainer.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        add(headerPanel, BorderLayout.NORTH);
        add(slideContainer, BorderLayout.CENTER);

        ApplicationFramework.getInstance().getSlideSelectionModel().addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        if (notification instanceof Project p) {
            projectLabel.setText("Project: " + p.getTitle());
            authorLabel.setText("Author: " + p.getAuthor());
            slideCountLabel.setText("Slides: " + p.getChildren().stream().filter(child -> child instanceof Slide).count());
        }else if (notification instanceof Slide slide) {
            if (slide.getParent() == this.presentation) {
                showSlide(slide);
            }
        }
    }

    public void showSlide(Slide slide) {
        if (!presentation.getChildren().contains(slide)) return;

        slideContainer.removeAll();
        activeSlideView = new SlideView(slide);
        MainFrame.getInstance().getNormalRadio().setSelected(true);
        MainFrame.getInstance().getNormalButton().click();
        SlideMouseController slideMouseController = new SlideMouseController(slide);
        slideContainer.add(activeSlideView, BorderLayout.CENTER);
        activeSlideView.addMouseListener(slideMouseController);
        activeSlideView.addMouseMotionListener(slideMouseController);

        revalidate(); /// treba - inace mora malo vise puta da se klikce na slide node
    }

}
