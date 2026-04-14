package com.github.zipcodewilmington;

import java.util.Random;
import java.util.Scanner;

/**
 * @author Michael Sie
 * @version 1.0.0
 * @date 4/13/26 04:54 PM
 */

public class WordGuess {
    private final String[] words = {"New York City", "Miami", "Los Angeles"};
    private char[] secretWord;
    private char[] guessedLetters;
    private int remainingAttempts;
    private String guessedHistory = "";
    private String wrongHistory = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordGuess game = new WordGuess();
        game.runGame(scanner);
    }

    public void runGame(Scanner scanner) {

        boolean play = true;
        while (play) {
        Random rand = new Random();
        int randInt = rand.nextInt(words.length);
        secretWord = words[randInt].toUpperCase().toCharArray();
        guessedHistory = "";
        wrongHistory = "";

        announceGame();
        initialiseGameState();
        int count = 0;
        for (int i = 0; i < secretWord.length; i++) {
            if (secretWord[i] != ' ') {
                count++;
            }
        }
        remainingAttempts = count;

            while(remainingAttempts > 0 && !isWordGuessed()) {
                printCurrentState();
                char guess = getNextGuess(scanner);
                if(!process(guess)) {
                    remainingAttempts--;
                    wrongHistory = wrongHistory + guess + " ";
                }
            }
            if(isWordGuessed()) {
                playerWon();
                printWord();
            } else {
                playerLost();
                revealWord();
            }
            play = askToPlayAgain(scanner);
        }
        gameOver(); 
    }

    public void announceGame() {
        System.out.println("Welcome to Michael's WordGuess.");
        System.out.println("I have picked a random word.");
    }

    public void gameOver() {
        System.out.println("Game Over :( .");
    }

    public void initialiseGameState() {
        guessedLetters = new char[secretWord.length];
        for (int i = 0; i < guessedLetters.length; i++) {
            if (secretWord[i] == ' ') {
            guessedLetters[i] = ' ';
        } else {
            guessedLetters[i] = '_';
        }
        }
    }

    public char getNextGuess(Scanner scanner) {
        while (true){
            System.out.print("Enter a single character: ");
            String input = scanner.nextLine().toUpperCase();
            if(input.length() != 1) {
                System.out.println("Invalid input. Please enter one character");
                continue;
            }
            char guess = Character.toUpperCase(input.charAt(0));
            if(!((guess >= 'a' && guess <= 'z') || (guess >= 'A' && guess <= 'Z'))) {
                System.out.println("Invalid input. Please enter one character");
                continue;
            }
            if (guessedHistory.contains(String.valueOf(guess))) {
            System.out.println("You already guessed that letter");
            continue;
            }
            guessedHistory = guessedHistory + guess + " ";
            return guess;
        }
    }

    public boolean isWordGuessed() {
        for(int i = 0;i < guessedLetters.length;i++) {
            if(guessedLetters[i] == '_') {
                return false;
            }
        }
        return true;
    }

    public boolean askToPlayAgain(Scanner scanner) {
        while (true){
            System.out.println("Do you want to play again? (Yes/No)");
            String input = scanner.nextLine().toUpperCase();
            if(input.equals("YES") || input.equals("Y")) {
                return true;
            } else if(input.equals("NO") || input.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid input.");
            }
        }
    }

    public void printCurrentState(){
        for (int i = 0; i < guessedLetters.length; i++) {
            System.out.print(guessedLetters[i] + " ");
            }
        System.out.println();
        System.out.println("You have " + remainingAttempts + " remaining attempts");
        System.out.println("Wrong guesses:" + wrongHistory);
    }

    public boolean process(char guess) {
        boolean isCorrect = false;
        for(int i = 0;i < secretWord.length;i++) {
            if(secretWord[i] == guess) {
                guessedLetters[i] = guess;
                isCorrect = true;
            }
        }
        return isCorrect;
    }

    public void printWord() {
        for (int i = 0; i < guessedLetters.length; i++) {
            System.out.print(guessedLetters[i] + " ");
        }
        System.out.println();
    }

    public void revealWord() {
        System.out.print("The Secret Word is: ");
        for (int i = 0; i < secretWord.length; i++) {
            System.out.print(secretWord[i] + " ");
            }
        System.out.println();
    }

    public void playerWon() {
        System.out.println("What a Legend!");
    }

    public void playerLost() {
        System.out.println("I've seen random guesses do better than that.");
    }
}


