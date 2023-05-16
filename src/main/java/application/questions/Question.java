package application.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Question {
    int left;
    String operator;
    int right;

    int result;

    public Question(int left, String operator, int right, int result) {
        this.left = left;
        this.operator = operator;
        this.right = right;
        this.result = result;
    }
    @Override
    public String toString() {
        return left + " " + operator + " " + right + " = " + "?";
    }

    public int getResult() {
        return result;
    }
}
