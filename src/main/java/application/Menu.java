package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static application.Application.launchExpressionGUI;

public class Menu extends JFrame implements ActionListener {

    private JButton facile; // only + and - operators, max value: 100
    private JButton moyen; // only +, -, * operators, max value: 100
    private JButton difficile; // all operators +, extends max value to 150
    private JButton descriptionButton;
    private JPanel contentPanel;
    private String description = "<html><body>" +
            "<b>Facile:</b><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Operators: + -<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Max value (right/left numbers) 100<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Error range between 15% and 25%<br/>" +
            "<b>Moyen:</b><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Operators: + - *<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Max value (right/left numbers) 100<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Error range between 10% and 17%<br/>" +
            "<b>Difficile:</b><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Operators: + - * /<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Max value (right/left numbers) 150<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Error range between 5% and 9%<br/>" +
            "<b></b>" +
            "<b>Exemple: (difficulty: expression [result, result+max%, result-max%, result+min%]</b><br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Facile: 5 + 80 = [85, 106, 63, 97]/<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Moyen: 5 * 80 = [400, 468, 332, 440]<br/>" +
            "&nbsp;&nbsp;&nbsp;&nbsp;Difficile: 80 / 5 = [16, 17, 14, 15]<br/>" +
            "</body></html>";

    public Menu() {
        setTitle("Menu Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel(new GridLayout(3,1));

        facile = new JButton("Facile");
        facile.addActionListener(this);
        JPanel buttonWrapper1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper1.add(facile);
        buttonPanel.add(buttonWrapper1);

        moyen = new JButton("Moyen");
        moyen.addActionListener(this);
        JPanel buttonWrapper2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper2.add(moyen);
        buttonPanel.add(buttonWrapper2);

        difficile = new JButton("Difficile");
        difficile.addActionListener(this);
        JPanel buttonWrapper3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonWrapper3.add(difficile);
        buttonPanel.add(buttonWrapper3);

        contentPanel.add(buttonPanel, BorderLayout.CENTER);

        descriptionButton = new JButton("description of the differents difficulties");
        descriptionButton.addActionListener(this);

        contentPanel.add(descriptionButton, BorderLayout.SOUTH);
        add(contentPanel);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == descriptionButton) {

            JOptionPane.showMessageDialog(Menu.this, description);
            return;
        }
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        if (e.getSource() == facile) {
            callToGUI(0);
        } else if (e.getSource() == moyen) {
            callToGUI(1);
        } else if (e.getSource() == difficile) {
            callToGUI(2);
        }
    }
    private void callToGUI(int difficulty) {
        launchExpressionGUI(difficulty);
        Application.nextQuestion();
        contentPanel.add(Application.gui, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
        Application.gui.setVisible(true);
    }
    public void callEndScreen() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        Application.gui.endScreen();
        contentPanel.add(Application.gui, BorderLayout.CENTER);
        contentPanel.revalidate();
        contentPanel.repaint();
        Application.gui.setVisible(true);
    }
}
