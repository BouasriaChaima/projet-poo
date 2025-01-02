import java.util.ArrayList;
import java.util.Scanner;

import poo.player; 
public class game {
	 private ArrayList<player> players; 
	 private Deck unoDeck;
	 private Card TopCard;
	 private int numPlayers;
	 private int CurrentPlayer;
	 private int numMaxPlayers;
	 private Boolean OpenGame;
	 private String Direction;// 1 if clockwise -1 if otherwise
	 private Scanner scanner;
	 
	 public game(int numP, int numMaxP) {
		 unoDeck = new Deck();
		 numPlayers = numP;
		 CurrentPlayer =0;// the index of the first player in the array
		 numMaxPlayers=numMaxP;
		 OpenGame = true;
		 Direction= "right";
		 TopCard = unoDeck.drawCard();
		 scanner = new Scanner(System.in);
	        for (int i = 0; i < numPlayers; i++) {
	            System.out.println("Enter name for Player " + (i + 1) + ": ");
	            String name = scanner.next();
	            Player P= new Player(name);
	            players.add(P);
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
		    	  else if(this.Direction == "left") {
		    		  this.CurrentPlayer--;
		    	  }
		}
	 
	 public void drawTwo() {
              if(this.TopCard.getType() == "DrawTwo") {
            	  this.players.get(this.CurrentPlayer).drawcard;
            	  this.players.get(this.CurrentPlayer).drawcard;
              }
              this.rotate();
	 }
	 public void ReverseDirection() {
		 if(this.TopCard.getType() == "Reverse") {
			if( this.Direction == "right") {
				this.Direction = "left";
			}
			else if(this.Direction == "left") {
				this.Direction = "right";
			}
			this.rotate();
			}
	 }
	 public void drawFour() {
         if(this.TopCard.getType() == "DrawFour") {
       	  this.players.get(this.CurrentPlayer).drawcard;
       	  this.players.get(this.CurrentPlayer).drawcard;
       	this.players.get(this.CurrentPlayer).drawcard;
     	  this.players.get(this.CurrentPlayer).drawcard;
         }
         this.rotate();
		 
	 }
	 public void Skip() {
		 if(this.TopCard == "Skip1") {
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
		 if(players.get(this.CurrentPlayer).PlayerHandSize == 1) {
			 System.out.println("Uno");
		 }
	 }
	 public void checkWinner() {
		 if(players.get(this.CurrentPlayer).PlayerHandSize == 0) {
			 System.out.println("Player number "+(this.CurrentPlayer + 1) +"is a winner");
		 }
	 }
	public void GameLogic() {
		if(this.TopCard.getColor()=="Red"){//"Red", "Green", "Blue", "Yellow"
			for(int i=0;i<players.get(this.CurrentPlayer).PlayerHand.size();i++) {
				if(players.get(this.CurrentPlayer).PlayerHand.get(i).getColor()=="red") {
					this.TopCard =
				}
			}
		}
	}
	 
	 
	 
	 
}
