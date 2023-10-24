import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class HangMan {
    public static void main(String[] args) {
        // initiate it
        String fileName = "wordlist.txt";
        ArrayList<String> wordList = new ArrayList<>();

        // blank array to store used letters
        ArrayList<Character> guessedLetters = new ArrayList<>();
        int maxAttempts = 5;

        // Read words from the text file and store them in the ArrayList
        // BufferReader reads text, uses buffering to enable large reads at a time
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.length() > 3) {
                   wordList.add(line); 
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        Random random = new Random();
        // store random word to a arraylist
        String wordToGuess = wordList.get(random.nextInt(wordList.size())).toLowerCase();
        // going to plug is correct guesses here
        char[] currentGuess = new char[wordToGuess.length()];

        // .isletter method determines whether the specified char value is a letter.
        // setting up dashes for the number of letters
        for (int i =0; i < wordToGuess.length(); i++) {
            if (Character.isLetter(wordToGuess.charAt(i))) {
                currentGuess[i] = '_';
            }
        }

        int attempts = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("ayoo this is hangman!");
        while (attempts < maxAttempts) {
            System.out.println(wordToGuess.length() + " lettered word");
            System.out.println("Word to guess: " + String.valueOf(currentGuess));
            System.out.println("Guessed letters: " + guessedLetters);
            
            System.out.print("Give me a guess: ");
            char guess = scanner.next().toLowerCase().charAt(0);

            if (!Character.isLetter(guess)) {
                System.out.println("enter a valid letter dude");
                continue;
            }

            if (guessedLetters.contains(guess)) {
                System.out.println("repeated letter, stupid");
                continue;
            }
            guessedLetters.add(guess);

            boolean found = false;
            for (int i = 0; i < wordToGuess.length(); i++) {
                if (wordToGuess.charAt(i) == guess) {
                    currentGuess[i] = guess;
                    found = true;
                }
            }
            if (!found) {
                attempts++;
                System.out.println("Incorrect guess " + (maxAttempts - attempts) + " attempts remaining.");
            }
            if (String.valueOf(currentGuess).equals(wordToGuess)) {
                System.out.println("Congrats! you are smarter than you look. The correct answer is: " + wordToGuess);
            }
        }

        if (attempts >= maxAttempts) {
            System.out.println("no more attempts. The word is: " + wordToGuess);
        }
        scanner.close();
    }
}
