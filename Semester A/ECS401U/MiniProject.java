import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//
//  MiniProject.java
//  - A Program that simulates the BBC 'Pointless' game
//
//  Created by Tom Knighton on 11/10/2020.
//  

/**
 * Question 
 * An ADT to hold a question and an array of answers
 * - Method to create question
 * - Method to get answers
 * - Method to get points for an answer
 * - Method to get question and question string
 */
class Question {
    String QuestionString; // Contains the String value which is the actual quiz question
    Answer[] Answers; // Contains an array holding multiple Answer record
}

/**
 * Answer
 * An ADT to hold an answer and the points it is worth
 * - Method to create Answer
 * - Method to get points for answer
 * - Method to get answer string
 */
class Answer {
    String AnswerString; // Contains the String value that holds the actual answer
    Integer Points; // Contains the points that should be given for that answer
}

/**
 * Question Bank
 * An ADT to hold a database of Questions
 * - Method to create Question Bank
 * - Method to add question to Question Bank
 * - Method to get specific question
 * - Method to get all questions
 */

class QuestionBank { 
    int nextFreeIndex; // Pointer to the next free index
    Question[] questions; // Array of questions
}

/**
 * Player
 * An ADT to hold a Player
 * - Method to get player's name
 * - Method to get player's points
 * - Method to change player's points
 */
class Player {
    String PlayerName;
    int PlayerPoints;
}

/**
 * PlayerBank
 * An ADT to hold a bank of Players
 * - Method to create Player Bank
 * - Method to get all players
 * - Method to add player
 * - Method to get specific player
 */
class PlayerBank {
    Player[] players;
    int nextFreeIndex;
}


/**
 * MiniProject
 * - Main Class
 */
public class MiniProject {

    // getInput is a wrapper method that returns the next user input, using a Scanner
    public static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // print() is a wrapper method around System.out.println, and is passed a String
    // and prints that to the console
    public static void print(String msg) {
        System.out.println(msg);
    }

    // shuffleQuestions takes an array of questions, and uses the Random class to randomly swap elements in the array,
    // returning the shuffled array
    public static Question[] shuffleQuestions(Question[] questions) {
        Random random = new Random();
        for(int i = 0; i < questions.length; i++) {
            int nextSwap = random.nextInt(questions.length - 1);
            Question tempStore = questions[nextSwap];
            questions[nextSwap] = questions[i];
            questions[i] = tempStore;
        }
        return questions;
    }

    // shuffleQuestions takes an array of answers, and uses the Random class to randomly swap elements in the array,
    // returning the shuffled array
    public static Answer[] shuffleAnswers(Answer[] answers) {
        Random random = new Random();
        for(int i = 0; i < answers.length; i++) {
            int nextSwap = random.nextInt(answers.length - 1);
            Answer tempStore = answers[nextSwap];
            answers[nextSwap] = answers[i];
            answers[i] = tempStore;
        }
        return answers;
    }

    // getPlayers() asks the user for the number of players, and calls getInput, it
    // performs validation on that result and returns the validated integer
    public static PlayerBank getPlayers() {
        print("Please enter the number of players:");
        String answer = getInput();
        while (!answer.matches("-?\\d+") || Integer.parseInt(answer) < 1) { // While the answer is not a number or below
                                                                            // 1
            print("Please enter a valid number"); // Re-asks the question and calls getInput()
            answer = getInput();
        }

        PlayerBank bank = CreatePlayerBank();
        for (int i = 0; i < Integer.parseInt(answer); i++) {
            print("Please enter the name for player "+(i + 1)+": ");
            String name = getInput();
            Player player = CreatePlayer(name);
            AddPlayerToBank(bank, player);
        }
        return bank; // Returns the now validated number of players as an Integer
    }

    // This method gets the players sorted by their points and prints the resulting list in a friendly format
    // This is called after every question
    public static void printCurrentPoints(PlayerBank bank) {
        Player[] sortedPlayers = GetPlayersInPointsOrder(bank);
        print("-------------------------------");
        for (Player player : sortedPlayers) {
            print(GetPlayerName(player) + "    :    " + GetPlayerPoints(player));
        }
        print("-------------------------------");
    }

    // The askQuestion() method is passed a Question record, prints the question,
    // the available answers and prompts the user to select one
    // It then calls getResultForAnswer on the question record and informs the user
    // of the points they received for that answer
    public static void askQuestion(Question question, Player player) {
        print(GetQuestionString(question)); // Gets the questionString and prints it
        print("Please type out your answer from:");
        Answer[] answers = shuffleAnswers(GetAnswersForQuestion(question)); // Calls method to shuffle questions' answers
        for (Answer answer : answers) { // foreach answer in the array returned by getAnswers() on the question record
            print("- " + GetAnswerText(answer)); // Print the answer
        }
        String answerInput = getInput(); // Gets the user's input
        Answer inputAnswer = GetAnswerByText(question, answerInput);
        Integer points = 0;
        if (inputAnswer == null) { points = 100; }
        else { points = GetPointsForAnswer(inputAnswer); }
        
        int currentPoints = GetPlayerPoints(player);
        SetPlayerPoints(player, currentPoints + points);
        if (points == 100) { // If the points are 100, answer was incorrect
            print("Incorrect! 100 points have been added to your total");
        } else if (points == 0) { // If the points were 0, a 'pointless' answer was given
            print("Correct! That was a pointless answer, gaining you 0 points");
        } else { // Else, a correct, but not pointless answer was given
            print("Correct! That answer got you " + points + " points.");
        }
    }

    // playGame() is passed the array of questions and the number of players in the game
    // It loops through each question, and within each question loops through the number of players (incrementing each time)
    // It then calls askQuestion() on the question object in the loop
    public static void playGame(final QuestionBank bank, final PlayerBank players) {
        Question[] questionArray = GetRegisteredQuestionsFromBank(bank);
        Player[] playerArray = GetRegisteredPlayersFromBank(players);
        questionArray = shuffleQuestions(questionArray);
        int questionNumber = 1;
        for (Question question : questionArray) { // Foreach loop through each Question in the question array
            print("\n\n*** QUESTION "+questionNumber+" **");
            for (int player = 0; player < playerArray.length; player++) { // For loop looping from 0 to the player count, incrementing each time
                print("\n"); // Prints a newline for user clarity
                print("Player " + (player + 1) + " ("+ GetPlayerName(playerArray[player])+"): "); // Prints the player number, prompting for the question
                askQuestion(question, GetPlayerFromBank(players, player)); // Calls askQuestion 
            }
            print("\n\nAfter question " + questionNumber + ", here are the current scores: ");
            printCurrentPoints(players);

            questionNumber += 1;
        }   
    }

    // This method creates and returns the Question Bank
    public static QuestionBank setupQuestions() throws IOException, ParseException {
        QuestionBank bank = CreateQuestionBank();

        FileReader reader = new FileReader("questions.json");
        JSONParser parser = new JSONParser();
        JSONArray arrayReader = (JSONArray) parser.parse(reader);
        for (Object o : arrayReader) {
            JSONObject questionEntry = (JSONObject) o;
            String questionString = questionEntry.get("question").toString();
            JSONArray answersEntry = (JSONArray) questionEntry.get("answers");
            Answer[] answers = new Answer[answersEntry.size()];
            for (int i = 0; i < answers.length; i++) {
                Object ansObject = (Object) answersEntry.get(i);
                String answer = ((JSONObject) ansObject).get("answerstring").toString();
                Integer points = Integer.parseInt(((JSONObject) ansObject).get("points").toString());
                answers[i] = CreateAnswer(answer, points);
            }

            Question question = CreateQuestion(questionString, answers);
            AddQuestionToBank(bank, question);
        }
        reader.close();
        return bank;
    }

    // The main method of this progtam calls getPlayers() to get the player count, as well as createQuestions() to get the questions array
    // It then calls playGame() to start the game
    public static void main(String[] args) {
        try {
            print("Welcome to the Pointless Java game, please press (1) to play the game or (2) to enter admin mode");
            String input = getInput();
            if (input.equals("2")) {
                print("\nWelcome to admin mode:");
                adminMode();

            } else {
                final PlayerBank playersBank = getPlayers(); // Calls getPlayers() and stores the result
                final QuestionBank questionBank = setupQuestions(); // Calls createQuestion() and stores the result
                playGame(questionBank, playersBank); // calls playGame
            }  
        } catch (Exception ex) {
            print(ex.getLocalizedMessage());
            print("An unexpected error occurred");
        }      
    }

    public static void adminMode() throws IOException, ParseException {
        print("\nPress (1) to clear all questions in questions.json, (2) to add a new question, or (3) to exit");
        String input = getInput();
        if (input.equals("1")) {
            PrintWriter printer = new PrintWriter("questions.json"); // Overwrites file with blank text
            printer.close();
            print("Questions file cleared");
            adminMode();
        } else if (input.equals("2")) {
            print("Please enter the question you'd like to add:");
            String questionString = getInput();
            print("Please enter how many answers there will be for this question:");
            String ansCountInput = getInput();
            while (!ansCountInput.matches("-?\\d+") || Integer.parseInt(ansCountInput) < 1) {
                print("Please enter a valid number of answers");
                ansCountInput = getInput();
            }

            int answersLength = Integer.parseInt(ansCountInput);
            Answer[] answers = new Answer[answersLength];
            for (int i = 0; i < answersLength; i++) {
                print("Please enter the text for this answer:");
                String answerText = getInput();
                print("Please enter the number of points for this answer (0-100)");
                String pointsInput = getInput();
                while (!pointsInput.matches("-?\\d+") || Integer.parseInt(pointsInput) < 0 || Integer.parseInt(pointsInput) > 100) {
                    print("Please enter a valid point total for this answer (0-100)");
                    pointsInput = getInput();
                }
                answers[i] = CreateAnswer(answerText, Integer.parseInt(pointsInput));
            }

            Question question = CreateQuestion(questionString, answers);
            FileReader reader = new FileReader("questions.json");
            JSONParser parser = new JSONParser();
            JSONArray questionsArray = (JSONArray) parser.parse(reader);
            reader.close();

            JSONObject questionObject = new JSONObject();
            JSONArray answersArray = new JSONArray();
            for (int i = 0; i < answers.length; i++) {
                JSONObject tempObject = new JSONObject();
                tempObject.put("answerstring", answers[i].AnswerString);
                tempObject.put("points", answers[i].Points);
                answersArray.add(tempObject);

            }

            questionObject.put("question", GetQuestionString(question));
            questionObject.put("answers", answersArray);
            questionsArray.add(questionObject);

            FileWriter writer = new FileWriter("questions.json");
            writer.write(questionsArray.toJSONString());
            writer.flush();
            writer.close();

            print("Question has been added successfully.");
            adminMode();
        } else if (input.equals("3")) {
            main(new String[0]);
        } else {
            adminMode();
        }
    }


    /**
     * ADT Methods for Question Bank
     */

    // Creates and returns a QuestionBank
    private static QuestionBank CreateQuestionBank() {
        QuestionBank bank = new QuestionBank();
        bank.nextFreeIndex = 0;
        bank.questions = new Question[500];
        return bank;
    }

    // Gets next free index for bank
    private static int GetNextFreeIndexForBank(QuestionBank bank) {
        return bank.nextFreeIndex;
    }

    // Increments next free index for bank
    private static void IncrementNextFreeIndexForBank(QuestionBank bank) {
        bank.nextFreeIndex += 1;
    }

    // Gets entire array from question bank, including unfilled
    private static Question[] GetAllQuestionsFromBank(QuestionBank bank) {
        return bank.questions;
    }

    // Gets all filled in questions from bank
    private static Question[] GetRegisteredQuestionsFromBank(QuestionBank bank) {
        Question[] questions = new Question[GetNextFreeIndexForBank(bank)];
        for (int i = 0; i < questions.length; i++) {
            questions[i] = GetQuestionFromBank(bank, i);
        }
        return questions;
    }

    // Gets specific question from question bank
    private static Question GetQuestionFromBank(QuestionBank bank, int questionIndex) {
        return GetAllQuestionsFromBank(bank)[questionIndex];
    }

    // Add question to question bank
    private static void AddQuestionToBank(QuestionBank bank, Question question) {
        Question[] allQuestions = GetAllQuestionsFromBank(bank);
        allQuestions[GetNextFreeIndexForBank(bank)] = question;
        IncrementNextFreeIndexForBank(bank);
    }

    /**
     * ADT methods for Question record
     */

    // Creates a question record
    private static Question CreateQuestion(String question, Answer[] answers) {
        Question newQuestion = new Question();
        newQuestion.QuestionString = question;
        newQuestion.Answers = answers;
        return newQuestion;
    }

    // Gets the question's actual question
    private static String GetQuestionString(Question question) {
        return question.QuestionString;
    }

    // Gets the answers for a question
    private static Answer[] GetAnswersForQuestion(Question question) {
        return question.Answers;
    }

    // Get an answer object from a question's answers if one exists with the text passed
    private static Answer GetAnswerByText(Question question, String answer) {
        Answer[] answers = GetAnswersForQuestion(question);
        Answer[] matchingAnswers = Arrays.stream(answers).filter(a -> GetAnswerText(a).toLowerCase().equals(answer.toLowerCase())).toArray(n -> new Answer[n]);
        if (matchingAnswers.length == 0) {
            print("returning null");
            return null;
        } else {
            print("returning answer");
            return matchingAnswers[0];
        }
    }

    /**
     * ADT methods for Answer record
     */

    // Creates Answer record
    private static Answer CreateAnswer(String answer, int points) {
        Answer newAnswer = new Answer();
        newAnswer.AnswerString = answer;
        newAnswer.Points = points;
        return newAnswer;
    }

    // Gets An answer's string answer
    private static String GetAnswerText(Answer answer) {
        return answer.AnswerString;
    }

    // Gets an answer's points
    private static Integer GetPointsForAnswer(Answer answer) {
        return answer.Points;
    }

    /**
     * ADT methods for PlayerBank
     */
    
    // Creates and returns a PlayerBank
    private static PlayerBank CreatePlayerBank() {
        PlayerBank bank = new PlayerBank();
        bank.nextFreeIndex = 0;
        bank.players = new Player[500];
        return bank;
    }

    // Gets next free index for bank
    private static int GetNextFreeIndexForBank(PlayerBank bank) {
        return bank.nextFreeIndex;
    }

    // Increments next free index for bank
    private static void IncrementNextFreeIndexForBank(PlayerBank bank) {
        bank.nextFreeIndex += 1;
    }

    // Gets entire array from player bank, including unfilled
    private static Player[] GetAllPlayersInBank(PlayerBank bank) {
        return bank.players;
    }

    // Gets all filled in players from bank
    private static Player[] GetRegisteredPlayersFromBank(PlayerBank bank) {
        Player[] players = new Player[GetNextFreeIndexForBank(bank)];
        for (int i = 0; i < players.length; i++) {
            players[i] = GetPlayerFromBank(bank, i);
        }
        return players;
    }

    // Gets specific player from player bank
    private static Player GetPlayerFromBank(PlayerBank bank, int playerIndex) {
        return GetAllPlayersInBank(bank)[playerIndex];
    }

    // Add player to player bank
    private static void AddPlayerToBank(PlayerBank bank, Player player) {
        Player[] allPlayers = GetAllPlayersInBank(bank);
        allPlayers[GetNextFreeIndexForBank(bank)] = player;
        IncrementNextFreeIndexForBank(bank);
    }

    // Gets the players in order of their points
    private static Player[] GetPlayersInPointsOrder(PlayerBank bank) {
        Player[] players = GetRegisteredPlayersFromBank(bank);
        Player[] sortedPlayers = players.clone();
        boolean arePointsSorted = false;
        Player tempPlayer;
        while (!arePointsSorted) {
            arePointsSorted = true;
            for (int i = 0; i < sortedPlayers.length - 1; i++) {
                if (GetPlayerPoints(sortedPlayers[i]) > GetPlayerPoints(sortedPlayers[i + 1])) {
                    tempPlayer = sortedPlayers[i];
                    sortedPlayers[i] = sortedPlayers[i + 1];
                    sortedPlayers[i + 1] = tempPlayer;
                    arePointsSorted = false;
                }
            }
        }
        return sortedPlayers;
    }

    /**
     * ADT methods for Player
     */

    // Creates a Player record
    private static Player CreatePlayer(String name) {
        Player player = new Player();
        player.PlayerName = name;
        player.PlayerPoints = 0;
        return player;
    }

    private static String GetPlayerName(Player player) {
        return player.PlayerName;
    }

    private static Integer GetPlayerPoints(Player player) {
        return player.PlayerPoints;
    }

    private static void SetPlayerPoints(Player player, int points) {
        player.PlayerPoints = points;
    }
     
    


}
