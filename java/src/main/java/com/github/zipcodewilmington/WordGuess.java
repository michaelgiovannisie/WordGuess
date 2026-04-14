package com.github.zipcodewilmington;

import java.util.Scanner;

/**
 * @author Michael Sie
 * @version 1.0.0
 * @date 4/13/26 04:54 PM
 */

public class WordGuess {

    private final char[] secretWord = "Liverpool".toCharArray();
    private char[] guessedLetters;
    private int remainingAttempts = secretWord.length;\

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordGuess game = new WordGuess();
        game.runGame(scanner);
    }

    public void runGame(Scanner scanner) {

        while (remainingAttempts > 0) {
            System.out.println(secretWord);


            remainingAttempts--;
        }
    }

    public void announceGame() {
        System.out.println("Welcome to Michael's WordGuess.");
    }

    public void gameOver() {
        System.out.println("Game Over :( .");
    }

    public void initializeGameState() {
        guessedLetters = new char[secretWord.length];
        for (int i = 0; i < guessedLetters.length; i++) {
            guessedLetters[i] = '_';
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
            char guess = input.charAt(0);
            if(!((guess >= 'a' && guess <= 'z') || (guess >= 'A' && guess <= 'Z'))) {
                System.out.println("Invalid input. Please enter one character");
                continue;
            }
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
            System.out.print("Do you want to play again? (Yes/No)");
            String input = scanner.nextLine().toLowerCase();
            if(input.equals("yes") || input.equals("y")) {
                return true;
            } else if(input.equals("no") || input.equals("n")) {
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
    }

    public void process(char guess) {
        for(int i = 0;i < secretWord.length;i++) {
            if(secretWord[i] == guess) {
                guessedLetters[i] = guess;
            }
        }
    }

    public void playerWon() {
        System.out.println("What a Legend!");
    }

    public void playerLost() {
        System.out.println("Loser.");
    }

}


