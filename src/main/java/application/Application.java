package application;

import application.generator.QuestionGenerator;
import application.questions.Question;

import javax.swing.*;
import java.util.*;

public class Application {
    private static QuestionGenerator Q;
    private static int questionIndex = 0;
    public static ExpressionGUI gui = null;
    private static Menu m = null;

    public static void main(String[] args) {
        m = new Menu();
    }

    public static void launchExpressionGUI(int difficulty) {
        Q = new QuestionGenerator(difficulty);
        // to print all questions generated
        /*for (int i = 0; i < Q.getQuestions().size(); i++) {
            System.out.println(Q.getQuestions().get(i));
        }*/
    }
    public static void nextQuestion() {
        if (questionIndex == 10) {
            m.callEndScreen();
            return;
        }
        Question currentQuestion = Q.getQuestions().get(questionIndex);
        int correctResult = currentQuestion.getResult();
        List<Integer> errorResult = Q.generateRandomResult(correctResult);
        List<String> buttonLabels = new ArrayList();
        for (Integer i : errorResult) {
            buttonLabels.add(String.valueOf(i));
        }
        buttonLabels.add(String.valueOf(correctResult));

        if (questionIndex == 0)
             gui = new ExpressionGUI(currentQuestion.toString(), buttonLabels);
        else
            gui.updateComponents(currentQuestion.toString(), buttonLabels);
        questionIndex++;
    }
}



