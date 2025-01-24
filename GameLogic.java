import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
	 private ArrayList<Player> players; 
	 private Deck UnoDeck;
	 private Card TopCard;
	 private int numPlayers;
	 private int CurrentPlayer;
	 private int numMaxPlayers;
	 private Boolean OpenGame;
	 private String Direction;// 1 if clockwise -1 if otherwise
	 private Scanner scanner;
	 
	 public GameLogic(int numP, int numMaxP) {
		 UnoDeck = new Deck();
		 numPlayers = numP;
		 CurrentPlayer =0;// the index of the first player in the array
		 numMaxPlayers=numMaxP;
		 OpenGame = true;
		 Direction= "right";
		 TopCard = UnoDeck.drawCard();
		 scanner = new Scanner(System.in);
	        for (int i = 0; i < numPlayers; i++) {
	            System.out.println("Enter name for Player " + (i + 1) + ": ");
	            String name = scanner.next();
	            Player P= new Player(name);
	            initialCards(P);
	            players.add(P);
	        }

	 }
	// a method to give 7 cards to the player 
	   public void initialCards (Player p) {
			   for (int i = 0 ; i<7;i++) {
				   p.playerHand.add(UnoDeck.drawCard());
			   }
	   }  
	 public void  addPlayer(Player Newplayer){
			if(this.OpenGame == true){
				this.players.add(Newplayer);
			}
				if(this.players.size() ==this.numMaxPlayers){
				this.OpenGame= false;
				}
		}
	 public void rotate(){
		      if(this.CurrentPlayer == (players.size()-1) && this.Direction == "right") {
		    	  this.CurrentPlayer=0;
		      }
		      else if(this.Direction == "right") {
		    	  this.CurrentPlayer++;
		      }
		      else if(this.Direction =="left" && this.CurrentPlayer==0) {
		    	  this.CurrentPlayer = ( players.size() -1);
		      }
		    	  else if(this.Direction) == "left") {
		    		  this.CurrentPlayer--;
		    	  }
		}
	 public void PlayerDraw(Player P) {
		 P.playerHand.add(this.UnoDeck.drawCard());
	 }
	 
	 public void drawTwo() {
              if(this.TopCard.getType() == "DrawTwo") {
            	  PlayerDraw( this.players.get(this.CurrentPlayer));
            	  PlayerDraw( this.players.get(this.CurrentPlayer));
              }
	 }
	 
	 public void ReverseDirection() {
		 if(this.TopCard.getType() == "Reverse") {
			if( this.Direction == "right") {
				this.Direction = "left";
			}
			else if(this.Direction == "left") {
				this.Direction = "right";
			}
			}
	 }
	 public void drawFour() {
         if(this.TopCard.getType() == "Wild Draw Four") {
        	 PlayerDraw( this.players.get(this.CurrentPlayer));
        	 PlayerDraw( this.players.get(this.CurrentPlayer));
        	 PlayerDraw( this.players.get(this.CurrentPlayer));
        	 PlayerDraw( this.players.get(this.CurrentPlayer));
         }
	 }
	 public void Skip() {
		 if(this.TopCard.getType() == "Skip1") {
			 this.rotate();
		 }
		 
	 }
	 public void Wildcard() {
		 if(this.TopCard.getType() == "Wild") {
			 System.out.println("Choose the color for the next player to play");
			 scanner = new Scanner(System.in);
			 String newColor = scanner.next();
			 this.TopCard.setColor(newColor);
		 }
	 }
	 
	 public void CheckUno() {
		 if(players.get(this.CurrentPlayer).playerHandSize() == 1) {
			 System.out.println("Uno");
		 }
	 }
	 
	 public void checkWinner() {
		 if(players.get(this.CurrentPlayer).playerHandSize() == 0) {
			 System.out.println("Player number "+(this.CurrentPlayer + 1) +"is a winner");
		 }
	 }
	
	public void GameLogic() {
		 if(this.TopCard.getType() == "Draw two") {
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
		 }
		 if(this.TopCard.getType() == "Wild Draw Four") {
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
			 this.players.get(this.CurrentPlayer).addCardHand(this.UnoDeck.drawCard());
		 }
		 if(this.TopCard.getType() == "Reverse") {
			 this.ReverseDirection();
		 }
		 if(this.TopCard.getType() == "Skip") {
			 this.Skip();
		 }
		 if(this.TopCard.getType() == "Wild Card") {
			this.Wildcard();
		 }
		 for(int i=0;i<this.players.get(this.CurrentPlayer).playerHandSize();i++) {
		 
			 if((this.players.get(this.CurrentPlayer).canPlayCard(this.players.get(this.CurrentPlayer).playerHand.get(i), this.TopCard)) == true) {
                  this.TopCard= this.players.get(this.CurrentPlayer).playCard(i);
			 }
		 }
		 
		 this.CheckUno();
		 this.checkWinner();
		 this.rotate();
	}
}
