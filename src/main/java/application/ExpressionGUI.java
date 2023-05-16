package application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExpressionGUI extends JPanel implements ActionListener {
    private JLabel expressionLabel;
    private List<JButton> buttonList;
    private JButton correctButton;
    private JButton updateButton;
    private JLabel Nb_questionLabel;
    private boolean alreadyHit = false;
    private int nb_question = 1;
    private int score = 0;

    public ExpressionGUI(String expression, List<String> buttonLabels) {
        setLayout(new BorderLayout());

        expressionLabel = new JLabel(expression);
        expressionLabel.setHorizontalAlignment(SwingConstants.CENTER);
        expressionLabel.setPreferredSize(new Dimension(200, 80));
        Font labelFont = expressionLabel.getFont();
        expressionLabel.setFont(labelFont.deriveFont(20f));
        add(expressionLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        buttonList = new ArrayList<>();
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4); // Add spacing between buttons
        String result = buttonLabels.get(buttonLabels.size() - 1);
        Collections.shuffle(buttonLabels);
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            if (label.equals(result))
                correctButton = button;
            button.addActionListener(this);
            Dimension buttonSize = new Dimension(150, 50);
            button.setPreferredSize(buttonSize);
            button.setMaximumSize(buttonSize);
            button.setMinimumSize(buttonSize);
            buttonList.add(button);
        }

        gbc.gridx = 0;
        gbc.gridy = 0;

        for (JButton button : buttonList) {
            centerPanel.add(button, gbc);
            gbc.gridx++;
            if (gbc.gridx > 1) {
                gbc.gridx = 0;
                gbc.gridy++;
            }
        }

        add(centerPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new BorderLayout());

        // add the next question button and hide it
        updateButton = new JButton("Next Question");
        updateButton.addActionListener(this);
        updateButton.setVisible(false);
        updateButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        bottomPanel.add(updateButton, BorderLayout.CENTER);

        // add the question number label
        Nb_questionLabel = new JLabel("Question 1/10    "); // Create the label
        Nb_questionLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Align the text to the right
        Nb_questionLabel.setPreferredSize(new Dimension(200, 80));
        Font questionfont = Nb_questionLabel.getFont();
        Nb_questionLabel.setFont(questionfont.deriveFont(16f));
        bottomPanel.add(Nb_questionLabel, BorderLayout.EAST);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle update button click
        if (e.getSource() == updateButton) {
            Application.nextQuestion();
        } else {
            JButton clickedButton = (JButton) e.getSource();
            if (alreadyHit == false) {
                if (clickedButton == correctButton) {
                    score++;
                    clickedButton.setBackground(Color.GREEN);
                    for (JButton button : buttonList) {
                        if (button != correctButton) {
                            button.setBackground(Color.RED);
                            button.setEnabled(false);
                        }
                    }
                } else {
                    clickedButton.setBackground(Color.RED);
                    clickedButton.setEnabled(false);
                }
                alreadyHit = true;
            }
            updateButton.setVisible(true);
        }
    }
    public void updateComponents(String newExpression, List<String> newButtonLabels) {
        expressionLabel.setText(newExpression);
        nb_question++;
        Nb_questionLabel.setText("question " + nb_question + "/10   ");
        String result = newButtonLabels.get(newButtonLabels.size() - 1);
        Collections.shuffle(newButtonLabels);
        for (int i = 0; i < buttonList.size(); i++) {
            JButton button = buttonList.get(i);
            button.setText(newButtonLabels.get(i));
            if (newButtonLabels.get(i).equals(result))
                correctButton = button;
            button.setBackground(null);
            button.setEnabled(true);
        }

        updateButton.setVisible(false);
        alreadyHit = false;
    }
    public void endScreen() {
        String message = "<html>Congratulations, you made it to the end!<br/>You have answered " + score + " questions correctly out of 10</html>";
        removeAll(); // Remove all components from the panel
        expressionLabel.setText(message); // Set the message on the label
        add(expressionLabel); // Add the label back to the panel
        revalidate(); // Revalidate the container
        repaint();

    }
}
