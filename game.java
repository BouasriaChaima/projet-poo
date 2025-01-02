import java.util.ArrayList;

import java.util.Scanner;

  public class Game {
	 private ArrayList<Player> players; 
	 private Deck unoDeck;
	 private Card TopCard;
	 private int numPlayers;
	 private int CurrentPlayer;
	 private int numMaxPlayers;
	 private Boolean OpenGame;
	 private String Direction;// 1 if clockwise -1 if otherwise
	 private Scanner scanner;
	 
	 public Game(int numP, int numMaxP) {
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
		this.ReverseDirection();
		if(this.TopCard.getColor()=="Red"){//"Red", "Green", "Blue", "Yellow"
			for(int i=0;i<players.get(this.CurrentPlayer).PlayerHand.size();i++) {
				if(players.get(this.CurrentPlayer).PlayerHand.get(i).getColor()=="Red") {
					this.TopCard =  players.get(this.CurrentPlayer).playCard (i) ;
				}
				}
			   this.TopCard = players.get(this.CurrentPlayer).playCard (0) ;
			   this.CheckUno();
			   this.checkWinner();
			}
		if(this.TopCard.getColor()=="Green"){//"Red", "Green", "Blue", "Yellow"
			for(int i=0;i<players.get(this.CurrentPlayer).PlayerHand.size();i++) {
				if(players.get(this.CurrentPlayer).PlayerHand.get(i).getColor()=="Green") {
					this.TopCard =  players.get(this.CurrentPlayer).playCard (i) ;
				}
				}
			   this.TopCard = players.get(this.CurrentPlayer).playCard (0) ;
			   this.CheckUno();
			   this.checkWinner();
			}
		if(this.TopCard.getColor()=="Blue"){//"Red", "Green", "Blue", "Yellow"
			for(int i=0;i<players.get(this.CurrentPlayer).PlayerHand.size();i++) {
				if(players.get(this.CurrentPlayer).PlayerHand.get(i).getColor()=="Blue") {
					this.TopCard =  players.get(this.CurrentPlayer).playCard (i) ;
				}
				}
			   this.TopCard = players.get(this.CurrentPlayer).playCard (0) ;
			   this.CheckUno();
			   this.checkWinner();
			}
		if(this.TopCard.getColor()=="Yellow"){//"Red", "Green", "Blue", "Yellow"
			for(int i=0;i<players.get(this.CurrentPlayer).PlayerHand.size();i++) {
				if(players.get(this.CurrentPlayer).PlayerHand.get(i).getColor()=="Yellow") {
					this.TopCard =  players.get(this.CurrentPlayer).playCard (i) ;
				}
				}
			   this.TopCard = players.get(this.CurrentPlayer).playCard (0) ;
			   this.CheckUno();
			   this.checkWinner();
			}
		this.drawFour();
		this.drawTwo();
		this.Skip();
		this.Wildcard();
		this.CheckUno();
		this.checkWinner();
		
		}
	}
	 
