/*
 * Concepts learned: loops and control statments
 * 
 * Comprehensive problem: number guessing game
 */

import java.util.Scanner;
import java.util.Random;

 class module_3 {
    public static void main (String[] args) {
        //construct new classes
        Random generator = new Random();
        Scanner input = new Scanner(System.in);

        System.out.println("----- Welcome to the number guessing game -----");

        boolean play = true;
        int difficulty;
        int max = 10;
        int guess;
        int counter;
        char resp;

        while (play) {
            //game setup
            guess = -1;
            counter = 0;

            do {
                System.out.print("Enter the difficulty (1-5): ");
                difficulty = input.nextInt();
            } while (difficulty!=1 && difficulty!=2 && difficulty!=3 && difficulty!=4 && difficulty!=5);

            switch (difficulty) {
                case 1:
                    max = 10;
                    break;
                case 2:
                    max = 50;
                    break;
                case 3:
                    max = 100;
                    break;
                case 4:
                    max = 500;
                    break;
                case 5:
                    max = 1000;
                    break;
            }
            int number = generator.nextInt(max); 
            System.out.println("Your number has been generated. Time to start guessing numbers betwen " + 0 + "and " + max);

            while (guess != number) {
                System.out.printf("Guess a number between 0 and %d: ", max);
                guess = input.nextInt();
                counter++;
            }

            System.out.printf("You correctly guessed %d in %d attempts. Play again (y/n)? ", number, counter);
            resp = input.next().charAt(0);

            if (resp != 'y') {
                play = false;
            }
        }

    }
 }