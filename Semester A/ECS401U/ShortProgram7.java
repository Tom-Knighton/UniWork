import java.util.Scanner;

//
//  ShortProject7.java
//  - A program to get information on disabled paralympic
//
//  Created by Tom Knighton on 01/11/2020.
//  


class ParaRelayTeam {
    String country;
    int[] points;
}

public class ShortProgram7 {

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

    // Method asks for a max classification and ensures it is a valid number, then asks for the country for this team. The method then
    // initialises a points array, and uses a for loop to ask the user about information for 4 swimmers, modifying the array as the loop continues.
    // THe program then creates a ParaRelayTeam record and informs the user if the team is legal or not
    public static void getParaTeamInformation() {
        String maxClassificationAnswer = getAnswerForQuestion("What is the classification (maximum points) of this relay event?");
        while (!isValidNumber(maxClassificationAnswer)) { maxClassificationAnswer = getAnswerForQuestion("Invalid number, What is the classification (maximum points) of this relay event?"); }
        int maxClassification = Integer.parseInt(maxClassificationAnswer);

        String country = getAnswerForQuestion("What country is the team representing?");
        int[] points = { 0, 0, 0, 0};
        for (int i = 0; i < 4; i++) {
            String disabilityAnswer = getAnswerForQuestion("What is the disability category for Swimmer " + (i+1) + "?");
            while (!isValidNumber(disabilityAnswer)) { disabilityAnswer = getAnswerForQuestion("Please enter a valid disability category: "); }
            points[i] = Integer.parseInt(disabilityAnswer);
        }

        ParaRelayTeam team = createParaTeam(country, points);
        Boolean isLegal = getTotalTeamPoints(team) <= maxClassification;
        print("That " + getParaTeamCountry(team) + " team has " + getTotalTeamPoints(team) + " points so is " + (isLegal ? "legal." : "not legal."));
    }

    // Main method just calls getParaTeamInforamtion.
    public static void main(String[] args) {
        getParaTeamInformation();
    }

    // Accessor Methods:

    // Creates and returns a ParaRelayTeam record
    private static ParaRelayTeam createParaTeam(String country, int[] points) {
        ParaRelayTeam team = new ParaRelayTeam();
        team.country = country;
        team.points = points;
        return team;
    }

    // returns the passed team's country
    private static String getParaTeamCountry(ParaRelayTeam team) {
        return team.country;
    }

    // returns the passed team's points
    private static int[] getParaTeamPoints(ParaRelayTeam team) {
        return team.points;
    }

    // uses a for loop to loop through the team's points and returns the total.
    private static int getTotalTeamPoints(ParaRelayTeam team) {
        int total = 0;
        for (int i = 0; i < getParaTeamPoints(team).length; i++) {
            total += getParaTeamPoints(team)[i];
        }
        return total;
    }

}
