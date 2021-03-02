import java.util.Scanner;

//
//  ShortProject8.java
//  - A program to recursively parse expressions
//
//  Created by Tom Knighton on 23/11/2020.
//  

public class ShortProgram8 {

    // Method is passed a String called message and calls System.out.println to print that message to the console
    public static void print(String message) {
        System.out.println(message);
    }

    // Method is a generic method that is passed a string, calls print() to print that message and then creates a Scanner to listen for and return the user's input
    public static String getAnswerForQuestion(String question) {
        print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Method prints an error message to the console and exits the application
    public static void exit() {
        print("Invalid expression.");
        System.exit(0);
    }

    // The main method asks the user for an expression and calls evaluate() to evaluate it and print the result
    public static void main(String[] args) {      
        String expression = getAnswerForQuestion("Please input the expression:"); 
        print(String.valueOf(evaluate(expression)));
    }


    // The evaluate method turns the input string into an array of chars, and evaluates each digit in turn recursively by calling itself
    // if the char being looked at is an integer, it returns that integer, otherwise, it calls itself recursively to work out the expressions needed
    // and the operand, before calling evaluateExpression to return the result of the expression
    public static Integer evaluate(String input) {
        char[] charArray = input.toCharArray();
        if (Character.valueOf(charArray[0]).toString().matches("-?\\d+")) {
            return Integer.valueOf(Character.valueOf(input.charAt(0)).toString());
        }
        else {
            Character operator = Character.valueOf(input.charAt(0));
            input = input.substring(1);
            Integer exp1 = evaluate(input);
            input = input.substring(1);
            if (input.length() != 0) {
                Integer exp2 = evaluate(input);
                return evaluateExpression(operator, exp1, exp2);
            }
            else {
                return evaluateExpression(operator, exp1, 0);
            }
            
        }
    }

    // This method checks the passed operator to ensure it is valid and then returns the value of the two integers when applied with the operator
    public static Integer evaluateExpression(Character operator, Integer exp1, Integer exp2) {
        switch (operator) {
            case '+':
                return exp1 + exp2;
            case '-':
                return exp1 - exp2;
            case '&':
                return additorial(exp1);
            default:
                exit();
                return null;
        }
    }

    // The additorial method is passed an integer and returns the sum of all the positive numbers from 1 up to that integer
    public static Integer additorial(Integer sum) {
        if (sum <= 1) {
            return 1;
        }
        return sum + additorial(sum - 1);
    }
}