import java.util.ArrayList; 

public class player {
 private String name;
 private int playerId;
 private ArrayList<Card> playerHand ;
 
 //controctor 
   public player (String name, int playerId) {
	    this.name =  name ;
	    this.playerId = playerId;
	    this.playerHand = new ArrayList <>();
   }
     
   // get player's name 
   public String getName() {
       return name;
   }
   // get player'd id
   public int getPlayerId() {
	   return playerId;
   }
   // get player's hand 
   public ArrayList<Card> getplayerHnad(){
	   return new ArrayList<>(playerHand);
   }
   
	// get number of cards in player's hand 
   int playerHandSize() {
	   return playerHand.size();
   }
   
   // remove a card from the player's hand 
	   public Card playCard (int cardIndex) {
		   if (cardIndex >= 0 && cardIndex < playerHand.size()) {
			   return playerHand.remove(cardIndex);
		   }
		return null;
	   }
   
   // add a card to the player's hand 
   public void drawCard (Card card) {
	   if(card != null) {
		   playerHand.add(card);
	   }


	   
	   
   }
   
   
}
