import java.util.Scanner;

//
//  ShortProject6.java
//  - A program to count bus travellers
//
//  Created by Tom Knighton on 01/11/2020.
//  

public class ShortProgram6 {

    // Method is passed a String called message and calls System.out.println to print that message to the console
    public static void print(String message) {
        System.out.println(message);
    }

    // Method is a generic method that is passed a string, calls print() to print that message and then creates a Scanner
    // to listen for and return the user's input
    public static String getAnswerForQuestion(String question) {
        print(question);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

     // Method used regex matching to return whether a string input is a valid number or not
     public static Boolean isValidNumber(String input) {
        return input.matches("-?\\d+");
    }

    // GetPassengers initialises some counter variables for passengers and buses, and asks the user for the initial number of passengers
    // It then uses a while loop to run while the user's input does not equal x, and inside that, another while loop to detect when the user is 
    // entering an invalid input. Once a valid input is received the buses is incremented by 1, and the passengers by the input. Then the question
    // is asked again. After the while loop exits, a final message is printed informing the user of the final bus and passenger count
    private static void GetPassengers() {
        int passengers = 0;
        int buses = 0;
        String answer = getAnswerForQuestion("How many passengers were on the bus?");

        while (!answer.toLowerCase().equals("x")) {
            while (!isValidNumber(answer) || Integer.parseInt(answer) < 0) {
                answer = getAnswerForQuestion("Invalid answer, please enter how many passengers were on the bus");
            }
            buses += 1;
            passengers += Integer.parseInt(answer);
            answer = getAnswerForQuestion("How many passengers were on the bus?");
        }  

        print("There were a total of " + passengers + " passengers on " + buses + " buses");
    }
   
    // main method calls GetPassengers
    public static void main(String[] args) {
        GetPassengers();
    }
}
