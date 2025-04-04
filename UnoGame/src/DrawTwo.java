import java.util.ArrayList;

public class DrawTwo extends Card {
    public DrawTwo(String color) {
        super(color, "Draw Two", -1); // -1 indicates a special card without a numeric value
    }
    
    // Method to apply the effect of DrawTwo
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) {
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        System.out.println(nextPlayer.getName() + " must draw two cards!");
        for (int i = 0; i < 2; i++) {
            Card drawnCard = deck.drawCard();
            if (drawnCard != null) {
                nextPlayer.drawCard(drawnCard);
            }
        }
    }
}
