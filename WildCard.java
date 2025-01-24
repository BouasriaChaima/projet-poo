import java.util.ArrayList;
import java.util.Scanner;

public class WildCard extends Card {
    public WildCard() {
        super("Wild", "Wild", -1); // cette carte n a pas de couleur specifique ou valeur numerique
    }

    // method to apply the effect of the Wild card
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(players.get(currentPlayerIndex).getName() + " played a Wild card!");

        // prompt the player to choose a new color
        System.out.println("Choose a color (Red, Green, Blue, Yellow): ");
        String chosenColor = scanner.nextLine();

        // a logic to change the current color in the game (NOTE!! : we will need to
        // implement this in the Game class)
        System.out.println("The new color is now: " + chosenColor);

        // Note!! : we would need to store this chosen color in the game state for futur
        // turns
    }
}
