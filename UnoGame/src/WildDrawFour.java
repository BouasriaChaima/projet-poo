import java.util.ArrayList;
import java.util.Scanner;

public class WildDrawFour extends Card {
    public WildDrawFour() {
        super("Wild", "Wild Draw Four", -1);
    }
    
    // Method to apply the effect of the Wild Draw Four card
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(
                "Wild Draw Four played! " + players.get(currentPlayerIndex).getName() + " chooses the next color.");
        // Prompting the player to choose a NEW color
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
        
        // Forcing next player to draw four cards
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        System.out.println(nextPlayer.getName() + " must draw four cards!");
        for (int i = 0; i < 4; i++) {
            Card drawnCard = deck.drawCard(); // Draw card from deck
            if (drawnCard != null) {
                nextPlayer.drawCard(drawnCard);
                System.out.println(nextPlayer.getName() + " drew: " + drawnCard);
            } else {
                System.out.println("The deck is empty! No more cards to draw.");
                break;
            }
        }
      
    }
}