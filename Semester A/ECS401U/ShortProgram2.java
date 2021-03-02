import java.util.Scanner;

//
//  ShortProject2.java
//  - A program to calculate the remaining student loan to pay
//
//  Created by Tom Knighton on 11/10/2020.
//  
public class ShortProgram2 {
    
    public final static Double interestRate = 1.07;
    // askQuestion is a generic method that is passed a string, prints that string to the console
    // and then returns the next user input (answer)
    public static String askQuestion(String question) { 
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // getLoanStartAmount calls askQuestion() to ask how much the loan was at the start of the year, performs validation,
    // and then returns the valid answer as an integer, or re-asks the question if the answer was invalid
    public static Integer getLoanStartAmount() {
        String answer = askQuestion("How much was your loan at the start of the year? (In Pounds)");
        while (!answer.matches("-?\\d+") || Integer.parseInt(answer) < 1) { // While the answer is not a valid number or over 0 
            System.out.println("Please enter a valid amount in pounds that is over 0. (I.e. 1000)");
            answer = askQuestion("How much was your loan at the start of the year? (In Pounds)");
        }
        return Integer.parseInt(answer);
    }

    // getAmountPaidOff calls askQuestion() to ask how much of the loan has been paid off, performs validation,
    // and then returns the valid answer as an integer, or re-asks the question if the answer was invalid
    public static Integer getAmountPaidOff() {
        String answer = askQuestion("How much of your loan have you paid off this year? (In Pounds)");
        while (!answer.matches("-?\\d+")) { // While the amount is not a valid number
            System.out.println("Please enter a valid amount in pounds (I.e. 50)");
            answer = askQuestion("How much of your loan have you paid off this year? (In Pounds)");
        }
        return Integer.parseInt(answer);
    }

    // The main method of this program calls getLoanStartAmount and getAmountPaidOff and stores the answers as variables
    // It then adds 1.7% interest to the remaining loan, and rounds that down to 1 dp before printing the remaining loan to the user
    public static void main(String[] args) {
        Integer loan = getLoanStartAmount();
        Integer loanPaid = getAmountPaidOff();

        Double loanRemaining = (loan - loanPaid) * interestRate;
        Double remainingPercentage = (double)((int)(loanRemaining * 10)) / 10;
        System.out.println("You now owe: £" + remainingPercentage);

    }
}
