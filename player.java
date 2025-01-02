import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Card> playerHand ;
	 
	 //controctor 
	   public Player (String name) {
		    this.name =  name ;
		    this.playerHand = new ArrayList <>();
	   }
	   
	   // set player's name 
	   public void setPlayerName(String name) {
		   this.name = name ;
	   }
	     
	   // get player's name 
	   public String getName() {
	       return name;
	   }
	   
	   // get player's hand 
	   public ArrayList<Card> getplayerHnad(){
		   return new ArrayList<>(playerHand);
	   }
	   
		// get number of cards in player's hand 
	   int playerHandSize() {
		   return playerHand.size();
	   }
	   
	 /*  // a method to give 7 cards to the player 
	   public void initialCards () {
			   for (int i = 0 ; i<7;i++) {
				   playerHand.add(deck.drawCard());
			   }
	   }  */
	   
	   // a method to check if a specific card can be played 
	public boolean canPlayCard (Card card, Card topCard) {
		 // Wild cards are always be played 
		 if (card.getType().equals("Wild") || card.getType().equals("Wild Draw Four")) {
		    return true;
		 }
		  // Check for matches
		 return card.getColor().equals(topCard.getColor()) || 
		           (card.getType().equals(topCard.getType()) && card.getValue() == topCard.getValue());
	   }
	   
	   // a method to remove a card from the player's hand 
		   public Card playCard (int cardIndex) {
			   if (cardIndex >= 0 && cardIndex < playerHand.size()) {
				   return playerHand.remove(cardIndex);
			   }
			return null;
		   }
		   
		   // add a card to the player's hand 
		   public void addCardHnad (Card card ) {
			   if ( card != null) {
				   playerHand.add(card);  
			   }   
		   }
	   

		   
	   }
