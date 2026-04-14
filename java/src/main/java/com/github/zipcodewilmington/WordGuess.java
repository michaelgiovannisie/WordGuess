package com.github.zipcodewilmington;

import java.util.Scanner;

/**
 * @author Michael Sie
 * @version 1.0.0
 * @date 4/13/26 04:54 PM
 */

public class WordGuess {

    private final String secretWord = "Liverpool";
    private char[] guessedLetters;
    private int remainingAttempts = secretWord.length();

    public static void main(String[] args) {
        System.out.println("Welcome to Word Guess!");
        WordGuess game = new WordGuess();
        game.play(new Scanner(System.in));
    }

    public void play(Scanner scanner) {
        guessedLetters = new char[secretWord.length()];
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
        }
        System.out.println(guessedLetters);

        while (remainingAttempts > 0) {
            System.out.println(secretWord);
            System.out.print("Enter a single character: ");
            String input = scanner.nextLine().toUpperCase();

            if(input.length() != 1) {
                System.out.println("Invalid input. Please enter one character");
                continue;
            }

            char guess = input.charAt(0);

            if(!((guess >= 'a' && guess <= 'z') || (guess >= 'A' && guess <= 'Z'))) {
                System.out.println("Invalid input. Please enter one character");
                continue;
            }

            remainingAttempts--;
        }
    }

}
