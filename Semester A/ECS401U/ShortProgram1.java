//
//  Project1.java
//  - A basic program to print out two set initials, formed by their own letters
//
//  Created by Tom Knighton on 26/09/2020.
//  
public class ShortProgram1
{

    public static void main(String[] args) {
        printTK();
        System.exit(0);
    }

    // print() is an abstraction method that takes in a String and calls System.out.println(), so as not to type that method call out every time
    private static void print(String message) {
        System.out.println(message);
    }

    // printT() prints the letter 'T' out of several lines of 'T', with a newline at the end
    public static void printT() {
        print("TTTTTTT"); // print() is used so as not to type out System.out.println() every time
        print("   T   ");
        print("   T   ");
        print("   T   ");
        print("   T   ");
        print("   T   \n"); // '\n' is used to create a new line after the letter

    }

    // printK() prints the letter 'K' out of several lines of 'K', with a newline at the end
    public static void printK() {
        print("K   K"); // print() is used so as not to type out System.out.println() every time
        print("K  K");
        print("K K");
        print("KK");
        print("K K");
        print("K  K");
        print("K   K\n"); // '\n' is used to create a new line after the letter
    }

    // prinTK() is called to call both printT() and printC(), printing both initials
    public static void printTK() {
        printT(); // The main program first calls printT() to print the 'T' to the console
        printK(); // Then the program calls printK() to print the letter 'K' to the console, forming the initials 'TK'
    }

}