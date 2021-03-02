import java.util.Scanner;

//
//  ShortProject3.java
//  - A program to estimate the body age of a user
//
//  Created by Tom Knighton on 11/10/2020.
//  
public class ShortProgram3 {
    
    // askQuestion is a generic method that is passed a string, prints that string to the console
    // and then returns the next user input (answer)
    public static String askQuestion(String question) { 
        Scanner scanner = new Scanner(System.in);
        System.out.println(question);
        return scanner.nextLine();
    }

    // getRealAge is a method that calls askQuestion to ask the user their age, validates the result and returns the Integer
    // representation of the valid answer
    public static Integer getRealAge() {
        String answer = askQuestion("How old are you? (In years)");
        while (!answer.matches("-?\\d+") || Integer.parseInt(answer) < 1) { // While the answer is not a valid number or over 0 
            System.out.println("Please enter how old you are in years. (I.e. 18)");
            answer = askQuestion("How old are you? (In years)");
        }
        return Integer.parseInt(answer);
    }

    // getBPMCaluclation calls askQuestion to ask the user their heart rate, validates the result and returns a modifier value
    // to be applied to their body age
    public static Integer getBPMCalculation() {
        String answer = askQuestion("What is your heart rate?");
        while (!answer.matches("-?\\d+")) { // While the answer is not a valid number
            System.out.println("Please enter your BPM (i.e. 70)");
            answer = askQuestion("What is your heart rate?");
        }
        Integer bpm = Integer.parseInt(answer);
        Integer bodyAgeModifier = 0;

        if (bpm < 62) bodyAgeModifier = -5;
        else if (bpm >= 62 && bpm <= 64) bodyAgeModifier = -1;
        else if (bpm >= 65 && bpm <= 70) bodyAgeModifier = 1;
        else bodyAgeModifier = 2;

        return bodyAgeModifier;
    }

    // getDistanceCalculation calls askQuestion to ask the user how far they can stretch, validates the result and
    // returns a modifier value to be applied to their body age
    public static Integer getDistanceCalculation() {
        String answer = askQuestion("How far can you stretch? (in c.m.)");
        while (!answer.matches("-?\\d+")) { // While the answer is not a valid number
            System.out.println("Please enter how far you can stretch in centimeters (i.e. 20)");
            answer = askQuestion("How far can you stretch? (in c.m.)");
        }
        Integer distance = Integer.parseInt(answer);
        Integer distanceModifier = 0;

        if (distance < 20) distance = 4;
        else if (distance >= 20 && distance <= 32) distanceModifier = 1;
        else if (distance >= 33 && distance <= 37) distanceModifier = 0;
        else distanceModifier = -3;

        return distanceModifier;
    }

    // The method calls getRealAge(), getBPMCalculation() and getDistanceCalculation() and stores the results for each call. 
    // It then creates a new variable called bodyAge which is simply the sum of the realAge, bpm modifier and stretch modifier. That result is then printed to the user.
    public static void main(String[] args) {
        Integer realAge = getRealAge();
        Integer bpmModifier = getBPMCalculation();
        Integer stretchModifier = getDistanceCalculation();

        Integer bodyAge = realAge + bpmModifier + stretchModifier;
        System.out.println("Your body's age is: " + bodyAge);
    }
}
