
import java.util.Random;
import java.util.Scanner;

public class RockPaperScissor {

    public static void main(String[] args) {
        // Initialize the variable
        int rock = 1;
        int papers = 2;
        int scissors = 3;
        int round = 0;
        int tie = 0;
        int winner = 0;
        int loser = 0;

        int player1;
        int computer;
        boolean res = true;

        Scanner userInput = new Scanner(System.in); // User input
        Random comChoice = new Random(); // Computer random
        System.out.println("How many round do you want to play?"); // Asks user # of time to play
        round = userInput.nextInt();
        userInput.nextLine();
        while (round < 1 || round > 10){
            System.out.println("Please select between 1 and 10!"); // Tell the user to pick 1-10
            round = userInput.nextInt();
            userInput.nextLine();
        }

        while (res == true) {
            for (int i = 0; i < round; i++) {

                System.out.println("Player enter: 1 Rock, 2 Paper, 3 Scissors: ");
                player1 = userInput.nextInt();
                userInput.nextLine();
                computer = comChoice.nextInt(3) + 1;

                if (player1 == computer) {
                    System.out.println("It's tie");
                    tie++;
                } else if (player1 == 1 && computer == 3) {
                    System.out.println("Player1 wins!");
                    winner++;
                } else if (player1 == 1 && computer == 2) {
                    System.out.println("Computer wins!");
                    loser++;

                } else if (player1 == 2 && computer == 1) {
                    System.out.println("Player1 wins!");
                    winner++;
                } else if (player1 == 2 && computer == 3) {
                    System.out.println("Computer wins!");
                    loser++;

                } else if (player1 == 3 && computer == 1) {
                    System.out.println("Computer wins!");
                    loser++;
                } else if (player1 == 3 && computer == 2) {
                    System.out.println("Player1 wins!");
                    winner++;

                } else {
                    System.out.println("Invalid entery");

                }
                System.out.println("Tie: " + tie);
                System.out.println("Won: " + winner);
                System.out.println("lose: " + loser);

            }

            System.out.println("Do you want to play again ?");
            String again = userInput.nextLine();
            if (again.equals("yes")) {
                System.out.println("How many round do you want to play?"); // Asks user # of time to play
                round = userInput.nextInt();
                while (round < 1 || round > 10){
                    System.out.println("Please select between 1 and 10!"); // Tell the user to pick 1-10
                    round = userInput.nextInt();
                    userInput.nextLine();
                }
            } else {
                res = false;
                System.out.println("Thanks for playing! ");
            }

        }
    }
}
