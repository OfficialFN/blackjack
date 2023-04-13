import java.util.Scanner;
import java.util.Random;
import java.util.ArrayList;

public class Blackjack {
    static int dealervalue = 0;
    public static String hit() {
        Random r = new Random();
        String[] cards = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
        int randomNumber = r.nextInt(12);
        String card = cards[randomNumber];
        return card;
    }

    public static void stand(ArrayList<String> dealercards, ArrayList<String> playercards) {
        while (dealervalue <= 17) {
            dealervalue = 0;
            dealercards.add(Main.hit());
            for (int i = 0; i < dealercards.size(); i++) {
                dealervalue += convertToValue(dealercards.get(i));
            }
        }
        System.out.println("The dealer's cards are now: " + dealercards + " - " + dealervalue);
    }

    public static int convertToValue(String num) {
        if (num == "K" || num == "Q" || num == "J") {
            return 10;
        } else if (num == "A") {
            return 1;
        } else {
            return Integer.parseInt(num);
        }
    }

    public static void main(String[] args) {
        int playervalue = 0;
        Scanner s = new Scanner(System.in);
        ArrayList<String> playercards = new ArrayList<String>();
        ArrayList<String> dealercards = new ArrayList<String>();
        dealercards.add(Main.hit());
        playercards.add(Main.hit());
        playercards.add(Main.hit());

        System.out.println("The dealer's card is " + dealercards);
        System.out.println("Your cards are: " + playercards);
        while (playervalue < 21) {
            System.out.print("Hit (1) or Stand (2)? ");
            int choice = s.nextInt();
            if (choice == 1) {
                playercards.add(Main.hit());
                playervalue = 0;
                for (int i = 0; i < playercards.size(); i++) {
                    playervalue += convertToValue(playercards.get(i));
                }
                System.out.println("Your cards now are: " + playercards + " - " + playervalue);

            } else if (choice == 2) {
                stand(dealercards, playercards);
                break;
            }
        }
        if (playervalue > 21) {
            System.out.println("Bust! You lose!");
        }  else if (playervalue == dealervalue) {
            System.out.print("Tie!!");
        } else if (dealervalue > playervalue) {
            System.out.println("Dealer is higher! You lose!");
        } else if (dealervalue > 21) {
            System.out.println("Dealer busted! You win!");
        } else if (playervalue > dealervalue) {
            System.out.println("You win! Good job!");
        }
    }
}