/*
 * Concepts: arrays, methods, overriding 
 * 
 * Comprehensive problem: Battleship
 *                         @ : ship
 *                         - : empty
 *                         X : hit
 *                         O : miss
 */

import java.util.Scanner;

class module_4 {
    public static void main (String[] args) {
        System.out.println("Welcome to battleship!\n");

        //initialize stuff
        Scanner input = new Scanner(System.in);
        char[][] player1 = new char[5][5]; //keeps track of where ships are
        char[][] player1_strat = new char[5][5]; //combat board: shows where hits, misses are
        int player1_ships = 5; //track number of ships
        String player1_name = "Player 1";

        char[][] player2 = new char[5][5];
        char[][] player2_strat = new char[5][5];
        int player2_ships = 5;
        String player2_name = "Player 2";

        fill_dash(player1);
        fill_dash(player2);
        fill_dash(player1_strat);
        fill_dash(player2_strat);
        System.out.println("Game initialized\n");
        boolean play = true;
        int round = -1;

        //start placements
        place_ships(player1, input);
        place_ships(player2, input);

        //game loop
        while (play) {
            next();

            if (round == -1) {
                System.out.println(player1_name + "'s turn");
                print_board(player1_strat);
                player2_ships = attack(player1_strat, player2, player2_ships, input);
            }
            else if (round == 1) {
                System.out.println(player2_name + "'s turn");
                print_board(player2_strat);
                player1_ships = attack(player2_strat, player1, player1_ships, input);
            }

            if (player1_ships == 0) {
                endgame(player2_name, player1_name, player2_ships);
                play = false;
            }
            else if (player2_ships == 0) {
                endgame(player1_name, player2_name, player1_ships);
                play = false;
            }
            round *= -1;
        }
    }

    private static void fill_dash(char[][] arr) {
        //fill board with dashes
        for (int i=0; i < arr.length; i++) {
            for (int j=0; j < arr[i].length; j++) {
                arr[i][j] = '-';
            }
        }
    }

    private static void place_ships(char[][] arr, Scanner input) {
        System.out.println("Place your ships");
        int coor1 = -1;
        int coor2 = -1;
        boolean lp = true;

        for (int i = 1; i <= 5; i++) {
            System.out.printf("Enter the coordinates for ship %d: ", i);
            coor1 = input.nextInt();
            coor2 = input.nextInt();
            if (coor1 > 4 || coor1 < 0 || coor2 > 4 || coor2 < 0) {
                System.out.println("Out of range");
                i--;
                continue;
            }

            else if (arr[coor1][coor2] == '@') {
                System.out.println("Ship already placed there");
                i--;
                continue;
            }
            arr[coor1][coor2] = '@';
        }
        print_board(arr);
        wait(input);
        next();
    }

    private static void next() {
        //prints 100 lines to hide previous player's board
        for (int i=0; i < 100; i++) {
            System.out.print('\n');
        }
    }

    private static void wait(Scanner input) {
        System.out.println("Press a key then enter to continue");
        String hold = input.next();
    }

    private static void print_board (char[][] board) {
        System.out.print("  01234\n");
        int counter = 0;
        for (char[] row : board) {
            System.out.print(counter + " ");
            counter++;
            for (char elt : row) {
                System.out.print(elt);
            }
            System.out.print('\n');
        }
    }

    private static int attack (char[][] attacker, char[][] defender, int defender_ships, Scanner input) {
        //attacker is strat board, defender is ship loc board
        int coor1 = -1;
        int coor2 = -1;
        boolean lp = true;

        while (lp) {
            System.out.print("Enter the coordinates for your attack: ");
            coor1 = input.nextInt();
            coor2 = input.nextInt();

            if (coor1 > 4 || coor1 < 0 || coor2 > 4 || coor2 < 0) {
                System.out.println("Coordinates out of range");
                continue;
            }
            else if (attacker[coor1][coor2] != '-') {
                System.out.println("You have already attacked there");
                continue;
            }
            else {
                lp = false;
                break;
            }
        }

        if (defender[coor1][coor2] == '@') {
            System.out.println("Hit!");
            attacker[coor1][coor2] = 'X';
            defender_ships--;
        }
        else {
            System.out.println("Miss");
            attacker[coor1][coor2] = 'O';
        }    
        wait(input);
        return defender_ships;
    }

    private static void endgame (String winner, String loser, int winner_ships) {
        System.out.println("The winner is " + winner);
        System.out.println(loser + " successfully destroyed " + (5 - winner_ships) + " ships");
    }
}