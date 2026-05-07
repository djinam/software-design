package raf.graffito.dsw.gui.swing;

import javax.swing.*;
import java.awt.*;

public class NewChildTypeDialog extends JDialog
{
    public enum Choice {PRESENTATION, SLIDE, CANCEL}
    private Choice choice = Choice.CANCEL;

    public NewChildTypeDialog(Frame owner)
    {
        super(owner, "Dodaj u projekat", true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel root = new JPanel(new BorderLayout(12, 12));
        root.setBorder(BorderFactory.createEmptyBorder(16, 16, 16, 16));
        root.add(new Label("Sta zelite da dodate u projekat"), BorderLayout.NORTH);

        JPanel center = new JPanel(new GridLayout(1, 2, 12, 0));
        JButton btnPres = new JButton("Prezentacija");
        JButton btnSlide = new JButton("Slajd");

        btnPres.addActionListener(e -> { choice = Choice.PRESENTATION; dispose(); });
        btnSlide.addActionListener(e -> { choice = Choice.SLIDE; dispose(); });

        center.add(btnPres);
        center.add(btnSlide);
        root.add(center, BorderLayout.CENTER);

        JButton cancel = new JButton("Otkazi");
        cancel.addActionListener(e -> { choice = Choice.CANCEL; dispose(); });
        JPanel south = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        south.add(cancel);
        root.add(south, BorderLayout.SOUTH);

        setContentPane(root);
        pack();
        setLocationRelativeTo(owner);
        setResizable(false);
    }

    public Choice getChoice() {return choice;}
}