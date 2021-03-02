import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//
//  ShortProject4.java
//  - A program to inform user's of step-free access on the tube
//
//  Created by Tom Knighton on 23/10/2020.
//  

// Station record declares stationName, whether it has step-free access and the distance from the entrance
class Station {
    String stationName;
    Boolean hasStepFreeAccess;
    Integer distanceFromEntrance;

    // The record also has a constructor to allow easy creation of a record
    public Station(String name, Boolean stepfree, int distance) {
        stationName = name;
        hasStepFreeAccess = stepfree;
        distanceFromEntrance = distance;
    }
}

// Main ShortProgram4 class
public class ShortProgram4 {

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

    // Returns an array of Station records, the method creates a List and then adds Station records by calling their constructor,
    // then returns the array
    public static Station[] getStationsList() {
        List<Station> stations = new ArrayList<Station>();
        stations.add(new Station("Stepney Green", false, 100));
        stations.add(new Station("Kings Cross", true, 700));
        stations.add(new Station("Oxford Circus", true, 200));
        stations.add(new Station("Harrow & Wealdstone", true, 0));
        stations.add(new Station("Wembley Central", true, 0));
        stations.add(new Station("Willesden Junction", true, 0));
        return stations.toArray(new Station[0]);
    }

    // Returns whether a name passed to the method is included in the array of Stations from getStationsList()
    public static Boolean isValidStationName(String name) {
        name = name.toLowerCase();
        Station[] stations = getStationsList();
        boolean isValidName = false;
        for (int i = 0; i < stations.length; i++) {
            if ((stations[i]).stationName.equalsIgnoreCase(name)) {
                isValidName = true;
                break;
            }
        }
        return isValidName;
    }

    // Returns the Station record object returned from a string passed in.
    public static Station getStationFromStationName(String name) {
        Station s = null;
        if (isValidStationName(name)) {
            for (Station station : getStationsList()) {
                if (station.stationName.equalsIgnoreCase(name)) {
                    s = station;
                }
            }
        }
        return s;
    }

    // This method calls getAnswerForQuestion to ask the user how many stations they wanted to know about,
    // and saves the result as a final integer, it then runs through a for loop, looping up to to that final variable,
    // each time asking the user for the station name, calling isValidName to get whether it is a valid name. If so, 
    // it gets the Station from getStationFromName and prints whether the station has step free access or not (building a string with ternary operator), 
    // and then the distance. If not, it prints that the station is invalid.
    public static void main(String[] args) {    
        final int NUMBER_OF_STATIONS = Integer.parseInt(getAnswerForQuestion("How many stations do you need to know about?"));
        print("\n\n");
        for (int i = 1; i <= NUMBER_OF_STATIONS; i++) {
            String stationName = getAnswerForQuestion("What station do you need to know about?");
            if(isValidStationName(stationName)) {
                Station station = getStationFromStationName(stationName);
                print(station.stationName + (station.hasStepFreeAccess ? " does" : " does not") + " have step free access");
                print("It is " + station.distanceFromEntrance + "m from entrance to platform");
            } else {
                print(stationName + " is not a valid station");
            }
            print("\n");
        }
    }
}
