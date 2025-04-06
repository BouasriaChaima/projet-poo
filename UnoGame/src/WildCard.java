
import java.util.ArrayList;
import java.util.Scanner;

public class WildCard extends Card {
    public WildCard() {
        super("Wild", "Wild", -1); // This card has no specific color or numeric value
    }
    
    // Method to apply the effect of the Wild card
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) {
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the player to choose a new color
        System.out.println("Choose a color (Red, Green, Blue, Yellow): ");
        String chosenColor = scanner.next();

        // Validate the color input
        while (!chosenColor.equalsIgnoreCase("Red") &&
               !chosenColor.equalsIgnoreCase("Green") &&
               !chosenColor.equalsIgnoreCase("Blue") &&
               !chosenColor.equalsIgnoreCase("Yellow")) {
            System.out.println("Invalid color. Please choose Red, Green, Blue, or Yellow:");
            chosenColor = scanner.next();
        }

        // Set the color of this card (will be used as the top card)
        this.setColor(chosenColor);
        System.out.println("The new color is now: " + chosenColor);
    }
}
