import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> playerHand;

    // Constructor
    public Player(String name) {
        this.name = name;
        this.playerHand = new ArrayList<>();
    }

    // Set player's name
    public void setPlayerName(String name) {
        this.name = name;
    }

    // Get player's name
    public String getName() {
        return name;
    }

    // Get player's hand
    public ArrayList<Card> getplayerHand() {
        return new ArrayList<>(playerHand);
    }

    // Set player's hand
    public void setPlayerHand() {
        this.playerHand = new ArrayList<>();
    }

    // Get number of cards in player's hand
    int playerHandSize() {
        return playerHand.size();
    }

    // A method to check if a specific card can be played
    public boolean canPlayCard(Card card, Card topCard) {
        // Wild cards are always playable
        if (card.getType().equals("Wild") || card.getType().equals("Wild Draw Four")) {
            return true;
        }
        // Check for matches (same color OR same type OR same value for number cards)
        return card.getColor().equals(topCard.getColor()) ||
               card.getType().equals(topCard.getType()) ||
               (card.getType().equals("Number") && topCard.getType().equals("Number") && 
                card.getValue() == topCard.getValue());
    }

    // A method to remove a card from the player's hand
    public Card playCard(int cardIndex) {
        if (cardIndex >= 0 && cardIndex < playerHand.size()) {
            return playerHand.remove(cardIndex);
        }
        return null;
    }

    // A method to draw a card from the deck
    public void drawCard(Card card) {
        if (card != null) {
            playerHand.add(card);
        }
    }
}
