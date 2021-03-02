import java.util.Scanner;

//
//  ShortProject5.java
//  - A program to enter information on footballers
//
//  Created by Tom Knighton on 26/10/2020.
//  

public class ShortProgram5 {

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

    // The GetFootballers method is passed a final int FOOTBALLER_COUNT from the calling method, this is used
    // to declare the three arrays used to store information on the footballers. The program then declares three variables to 
    // keep track of the total goals, the highest goals, and the index of the highest scorer. The program then runs a for loop up to the 
    // value of FOOTBALLER_COUNT, each time calling getAnswerForQuestion() to get the name of the footballer, their goals and country. For the goals,
    // the program ensures that the answer is a valid integer and then appends the number to totalGoals, before checking if it is the new highest goals, and if so,
    // changing the highestGoals and indexOfHighestScorer variables to current i value. After the for loop, the method calls printFootballerScores
    public static void getFootballers(final int FOOTBALLER_COUNT) {
        String[] footballerNames = new String[FOOTBALLER_COUNT];
        Integer[] goalsScored = new Integer[FOOTBALLER_COUNT];
        String[] footballerCountries = new String[FOOTBALLER_COUNT];

        int totalGoals = 0;
        int highestGoals = 0;
        int indexOfHighestScorer = 0;

        for (int i = 0; i < FOOTBALLER_COUNT; i++) {
            print("\nFootballer " + String.valueOf(i + 1) + ":");
            footballerNames[i] = getAnswerForQuestion("Name footballer " + String.valueOf(i + 1) + ": ");

            String goalsAnswer = getAnswerForQuestion("How many goals did they score?");
            while (!isValidNumber(goalsAnswer)) { // While the amount is not a valid number
                System.out.println("Please enter a valid number of goals");
                goalsAnswer = getAnswerForQuestion("How many goals did they score?");
            }
            goalsScored[i] = Integer.parseInt(goalsAnswer);
            totalGoals += goalsScored[i];
            if (goalsScored[i] > highestGoals) { 
                highestGoals = goalsScored[i];
                indexOfHighestScorer = i;
            }

            footballerCountries[i] = getAnswerForQuestion("What country did they play for?");
        }

        printFootballerScores(footballerNames, goalsScored, footballerCountries, totalGoals, indexOfHighestScorer);
    }

    // This method is passed three arrays, an integer of the total goals, and an integer of the highest scorer. The method uses this to first print the
    // name of the highest scorer, using the highestScorer index passed and the footballers array passed. It then prints the total number of goals passed,
    // before running through a for loop to print the full details for each player in the arrays.
    public static void printFootballerScores(String[] footballers, Integer[] goals, String[] countries, int totalGoals, int highestScorer) {
        print("\n\nThe highest scorer was " + footballers[highestScorer] + ".");
        print("These players scored " + totalGoals + " between them\n");
        print("The details of the players are:\n ");
        for (int i = 0; i < footballers.length; i ++ ) {
            print(footballers[i] + ", " + countries[i] + ", " + goals[i]);
        }
    }
    
    // The main method here declares a final int FOOTBALLER_COUNT, initialises it to 4 and passes it to GetFootballers()
    public static void main(String[] args) {
        final int FOOTBALLER_COUNT = 4;
        getFootballers(FOOTBALLER_COUNT);
    }
}
