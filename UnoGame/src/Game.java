import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players; 
    private Deck UnoDeck;
    private Card TopCard;
    private int numPlayers;
    private int CurrentPlayer;
    private int numMaxPlayers;
    private Boolean OpenGame;
    private String Direction; // "right" for clockwise, "left" for counter-clockwise
    private Scanner scanner;

    public Game(int numP, int numMaxP) {
        UnoDeck = new Deck();
        numPlayers = numP;
        CurrentPlayer = 0; // The index of the first player in the array
        numMaxPlayers = numMaxP;
        OpenGame = true;
        Direction = "right"; // Default direction
        
        // Draw initial top card and make sure it's a regular card
        TopCard = UnoDeck.drawCard();
        
        // Check if the top card is a special card and get a different one if it is
        while (TopCard.getType().equals("Wild") || 
               TopCard.getType().equals("Wild_Four") || 
               TopCard.getType().equals("Skip") || 
               TopCard.getType().equals("Reverse") || 
               TopCard.getType().equals("Draw_Two")) {
            
            // Put the card back in the deck and shuffle
            UnoDeck.addCard(TopCard);
            UnoDeck.shuffle();
            
            // Draw a new card
            TopCard = UnoDeck.drawCard();
        }
        
        scanner = new Scanner(System.in);
        players = new ArrayList<>(); // Initialize the players list
        
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.next();
            Player P = new Player(name);
            initialCards(P);
            players.add(P);
        }
    }

    // Method to get the list of players
    public ArrayList<Player> getPlayers() {
        return players;
    }

    // Method to get the current player index
    public int getCurrentPlayer() {
        return CurrentPlayer;
    }

    // Method to set the top card
    public void setTopCard(Card card) {
        this.TopCard = card;
    }

    // Method to get the top card
    public Card getTopCard() {
        return TopCard;
    }

    // Method to get the deck
    public Deck getDeck() {
        return UnoDeck;
    }

    // Method to initialize the player's hand with 7 cards
    private void initialCards(Player p) {
        for (int i = 0; i < 7; i++) {
            p.drawCard(UnoDeck.drawCard());
        }
    }
    
    // Method to handle playing a card
       private void playCard(Player player) {
           System.out.print("Enter the index of the card to play: ");
           int cardIndex = scanner.nextInt();

           // Validate the card index
           if (cardIndex < 0 || cardIndex >= player.playerHandSize()) {
               System.out.println("Invalid card index. Try again.");
               return;
           }

           Card cardToPlay = player.getplayerHand().get(cardIndex);
           
           // Validate if the card can be played
           if (player.canPlayCard(cardToPlay, TopCard)) {
               Card playedCard = player.playCard(cardIndex);
               TopCard = playedCard; // Update the top card
               System.out.println(player.getName() + " played: " + playedCard);
           } else {
               System.out.println("You cannot play that card. It must match the color, type, or value of the top card.");
               System.out.println("Choose another action:");
               System.out.println("1. Play a different card");
               System.out.println("2. Draw a card");
               
               int choice = scanner.nextInt();
               switch (choice) {
                   case 1:
                       playCard(player); // Recursively call playCard to allow selecting another card
                       break;
                   case 2:
                       PlayerDraw(player);
                       if (Direction.equals("right")) {
                           CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Move to the next player
                       } else {
                           CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Move to the previous player
                       }
                       break;
                   default:
                       System.out.println("Invalid choice. You will draw a card.");
                       PlayerDraw(player);
                       if (Direction.equals("right")) {
                           CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Move to the next player
                       } else {
                           CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Move to the previous player
                       }
                       break;
               }
           }
       }
   
    
    // Method to check if any player has won
    public boolean checkWinner() {
        for (Player player : players) {
            if (player.playerHandSize() == 0) {
                System.out.println(player.getName() + " has won the game!");
                OpenGame = false;
                return true;
            }
        }
        return false;
    }

    // Method to handle special card effects
    private void handleSpecialCards() {
        Card topCard = getTopCard();

        // Check for special card effects
        if (topCard instanceof DrawTwo) {
            ((DrawTwo) topCard).applyEffect(players, CurrentPlayer, UnoDeck);
        } else if (topCard instanceof WildDrawFour) {
            ((WildDrawFour) topCard).applyEffect(players, CurrentPlayer, UnoDeck);
        } else if (topCard instanceof Reverse) {
            ReverseDirection();
        } else if (topCard instanceof Skip) {
            Skip();
        } else if (topCard instanceof WildCard) {
            ((WildCard) topCard).applyEffect(players, CurrentPlayer, UnoDeck);
        }
    }

    // Method to handle Skip card logic
    public void Skip() {
        System.out.println("Skip card played! Next player's turn skipped.");
        // Skip the next player's turn
        if (Direction.equals("right")) {
            CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Skip the next player
        } else {
            CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Skip the previous player
        }
    }

    // Method to draw a card for a player
    public void PlayerDraw(Player P) {
        Card drawnCard = UnoDeck.drawCard();
        if (drawnCard != null) {
            P.drawCard(drawnCard); // Add the drawn card to the player's hand
            System.out.println(P.getName() + " drew a card: " + drawnCard);
        } else {
            System.out.println("No more cards to draw!");
        }
    }

    // Method to reverse the direction of play
    public void ReverseDirection() {
        if (Direction.equals("right")) {
            Direction = "left";
        } else {
            Direction = "right";
        }
        System.out.println("Direction reversed to " + Direction);
    }
    // Method to handle game logic after each turn
    public void GameLogic() {
        // Check if the current player has won
        if (checkWinner()) {
            return;
        }
        
        // Check if player has only one card left
        if (players.get(CurrentPlayer).playerHandSize() == 1) {
            System.out.println(players.get(CurrentPlayer).getName() + " says UNO!");
        }

        // Handle special card effects
        handleSpecialCards();

        // Move to the next player
        if (Direction.equals("right")) {
            CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Move to the next player
        } else {
            CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Move to the previous player
        }
    }

    // Method to start the game
    public void startGame() {
    	
        while (OpenGame) {
            System.out.println("\n--- Current player: " + players.get(CurrentPlayer).getName() + " ---");
            System.out.println("Top Card: " + TopCard);
            
            // Display hand for current player
            System.out.println(players.get(CurrentPlayer).getName() + "'s hand: ");
            ArrayList<Card> hand = players.get(CurrentPlayer).getplayerHand();
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i) + ": " + hand.get(i));
            }
            
            // Get player action
            System.out.println("\nChoose an action:");
            System.out.println("1. Play a card");
            System.out.println("2. Draw a card");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    playCard(players.get(CurrentPlayer));
                   
                    break;
                case 2:
                    PlayerDraw(players.get(CurrentPlayer));
                    if (Direction.equals("right")) {
                        CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Move to the next player
                    } else {
                        CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Move to the previous player
                    }
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    continue;
            }
            
            GameLogic();
        }
    }
    }