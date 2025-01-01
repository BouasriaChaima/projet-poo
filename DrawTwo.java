import java.util.ArrayList; 

public class DrawTwo extends Card { 
    public DrawTwo(String color) { 
        super(color, "Draw Two", -1); //-1 indique que c une carte speciale sans une valeur numerique 
 
    } 
 
    // method to apply the effect of the Draw Two card 
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex, Deck deck) { 
        //identify the next player who will draw cards 
        Player.nextPlayer = players.get((currentPlayerIndex + 1) % players.size()); 
        System.out.println(nextPlayer.getName() + " must draw two cards!"); 
 
        //Forcing the next player to draw two cards from the deck 
        for (int i = 0; i < 2; i++) { 
            Card drawnCard = deck.drawCard(); // draw card from the deck 
            if (drawnCard != null) {  
                nextPlayer.drawCard(drawnCard); //assuming drawCard method adds a card to the player's hand 
                System.out.println(nextPlayer.getName() + " drew: " + drawnCard.getColor() + " " + drawnCard.getType()); 
            } else { 
                System.out.println("The deck is empty! No more cards to draw."); 
            } 
        } 
 
    }