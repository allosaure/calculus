package application.generator;

import application.questions.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionGenerator {
    int difficulty;
    List<Question> questions;
    public QuestionGenerator(int difficulty) {
        this.difficulty = difficulty;
        questions = generateQuestionList();
    }

    public String generateOperator() {
        List<String> operator = new ArrayList<>();
        operator.add("+");
        operator.add("-");
        if (difficulty >= 1)
            operator.add("*");
        if (difficulty >= 2)
            operator.add("/");
        Random random = new Random();
        return operator.get(random.nextInt(operator.size()));
    }
    public Question generateQuestion() {
        // define the born of the random [0, max[
        int max;
        if (difficulty == 0)
            max = 100;
        else if (difficulty == 1)
            max = 100;
        else
            max = 200;

        Random random = new Random();

        String oper = generateOperator();

        if (oper.equals("/")) {
            int divisor = random.nextInt(10) + 1;   // Random divisor between 1 and 10
            int quotient = random.nextInt(100) + 1; // Random quotient between 1 and 100
            int dividend = quotient * divisor;      // Calculate the dividend
            return new Question(dividend, oper, divisor, quotient);
        }
        int left = random.nextInt(max);
        int right = random.nextInt(max);
        return new Question(left, oper, right, calcul(left, oper, right));
    }
    public List<Question> generateQuestionList() {
        List<Question> QList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            QList.add(generateQuestion());
        }
        return QList;
    }
    public int calcul(int left, String oper, int right) {
        int result = -1;
        if (oper.equals("+"))
            result = left + right;
        else if  (oper.equals("-"))
            result = left - right;
        else if  (oper.equals("*"))
            result = left * right;
        else if  (oper.equals("/"))
            result = left / right;
        return result;
    }

    public List<Integer> generateRandomResult(int exactresult) {
        double pourcentage = 0.25 - difficulty * 0.08;
        Random random = new Random();
        List<Integer> resultList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            int res = exactresult;
            int exactresult_ = exactresult;
            int maxloop = 0;
            while (resultList.indexOf(res) != -1 | res == exactresult) {
                res = exactresult_;
                double epsilon = (0.15 - difficulty * 0.05) + (pourcentage - (0.15 - difficulty * 0.05)) * random.nextDouble();
                int sign = random.nextInt(2);
                if (sign == 0) {
                    res = res + (int) Math.round(res * epsilon);
                } else {
                    res = res - (int) Math.round(res * epsilon);
                }
                if (maxloop > 5)
                    exactresult_++;
                maxloop++;
            }
            resultList.add(res);
        }
        return resultList;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
