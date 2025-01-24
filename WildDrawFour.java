
import java.util.ArrayList;
import java.util.Scanner;

public class WildDrawFour extends Card {
    public WildDrawFour() {
        super("Wild", "Wild Draw Four", -1);
    }

    // method to apply the effect of the Wild Draw Four card
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Wild Draw Four played! " + players.get(currentPlayerIndex).getName() + " chooses the next color.");

        // prompting the player to choose a NEW color
        System.out.println("Choose a color (Red, Green, Blue, Yellow): ");
        String chosenColor = scanner.nextLine();

        // change current color in the game (needs to be implemented in the Game class)
        System.out.println("The new color is now: " + chosenColor);

        // forcing next player to draw four cards
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        System.out.println(nextPlayer.getName() + " must draw four cards!");

        for (int i = 0; i < 4; i++) {
            Card drawnCard = deck.drawCard(); // draw card from deck
            if (drawnCard != null) {
                nextPlayer.drawCard(drawnCard); // we assum drawCard method adds a card to the player's hand
                System.out.println(nextPlayer.getName() + " drew: " + drawnCard.getColor() + " " + drawnCard.getType());
            } else {
                System.out.println("The deck is empty! No more cards to draw.");
            }
        }

        // Note!! : we need to store this chosen color in the game state for future
        // turns
    }
}