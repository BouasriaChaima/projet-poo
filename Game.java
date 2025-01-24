import java.util.ArrayList;
import java.util.Scanner;

  public class Game {
   private ArrayList<Player> players; 
   private Deck UnoDeck;
   private Card TopCard;
   private int numPlayers;
   private int CurrentPlayerIndex;
   private int numMaxPlayers;
   private Boolean OpenGame;
   private String Direction;// 1 if clockwise -1 if otherwise
   private Scanner scanner;
   
   public Game(int numP, int numMaxP) {
     UnoDeck = new Deck();
     numPlayers = numP;
     CurrentPlayerIndex =0;// the index of the first player in the array
     numMaxPlayers=numMaxP;
     OpenGame = true;
     Direction= "right";
     TopCard = UnoDeck.drawCard();
     scanner = new Scanner(System.in);
          for (int i = 0; i < numPlayers; i++) {
              System.out.println("Enter name for Player " + (i + 1) + ": ");
              String name = scanner.next();
              Player P= new Player (name);
              initialCards(P);
              players.add(P);
          }

   }
   public ArrayList<Player> getPlayers() {
          return players;  // Return the list of players
      }
   public Player  getCurrentPlayer() {
          return players.get(this.CurrentPlayerIndex);
      }
   public void setTopCard(Card card) {
          this.TopCard = card;
      }

      public Card getTopCard() {
          return TopCard;
      }
  // a method to give 7 cards to the player 
     public void initialCards (Player p) {
         for (int i = 0 ; i<7;i++) {
           p.addCardHand(UnoDeck.drawCard());
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
          if(this.Direction == "right") {
            CurrentPlayerIndex = (CurrentPlayerIndex + 1) % players.size();
          }
          else if(this.Direction =="left" ) {
            CurrentPlayerIndex = (CurrentPlayerIndex - 1 + players.size() ) % players.size();
          }
    }
   public void PlayerDraw(Player P) {
     P.addCardHand(this.UnoDeck.drawCard());
   }
   
   public void drawTwo() {
              if(this.TopCard.getType() == "DrawTwo") {
                PlayerDraw( this.players.get(this.CurrentPlayerIndex));
                PlayerDraw( this.players.get(this.CurrentPlayerIndex));
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
           PlayerDraw( this.players.get(this.CurrentPlayerIndex));
           PlayerDraw( this.players.get(this.CurrentPlayerIndex));
           PlayerDraw( this.players.get(this.CurrentPlayerIndex));
           PlayerDraw( this.players.get(this.CurrentPlayerIndex));
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
     if(players.get(this.CurrentPlayerIndex).playerHandSize() == 1) {
       System.out.println("Uno");
     }
   }
   
   public void checkWinner() {
     if(players.get(this.CurrentPlayerIndex).playerHandSize() == 0) {
       System.out.println("Player number "+(this.CurrentPlayerIndex + 1) +"is a winner");
     }
   }
  
  public void GameLogic() {
     if(this.TopCard.getType() == "Draw two") {
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
     }


if(this.TopCard.getType() == "Wild Draw Four") {
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
       this.players.get(this.CurrentPlayerIndex).addCardHand(this.UnoDeck.drawCard());
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
     for(int i=0;i<this.players.get(this.CurrentPlayerIndex).playerHandSize();i++) {
     
       if((this.players.get(this.CurrentPlayerIndex).canPlayCard(this.players.get(this.CurrentPlayerIndex).getplayerHnad.get(i), this.TopCard)) == true) {
                  this.TopCard= this.players.get(this.CurrentPlayerIndex).playCard(i);
       }
     }
     
     this.CheckUno();
     this.checkWinner();
     this.rotate();
  }
  }
