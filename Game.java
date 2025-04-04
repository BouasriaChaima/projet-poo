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
        CurrentPlayer = 0; // the index of the first player in the array
        numMaxPlayers = numMaxP;
        OpenGame = true;
        Direction = "right"; // Default direction
        TopCard = UnoDeck.drawCard();
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
            p.addCardHand(UnoDeck.drawCard());
        }
    }

    // Method to handle game logic after each turn
    public void GameLogic() {
        // Check if the current player has won
        if (players.get(CurrentPlayer).playerHandSize() == 0) {
            System.out.println(players.get(CurrentPlayer).getName() + " has won the game!");
            OpenGame = false; // End the game
            return;
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

    // Method to handle special card effects
    private void handleSpecialCards() {
        Card topCard = getTopCard();

        // Check for special card effects
        if (topCard.getType().equals("Draw Two")) {
            for (int i = 0; i < 2; i++) {
                PlayerDraw(players.get(CurrentPlayer));
            }
        } else if (topCard.getType().equals("Wild Draw Four")) {
            drawFour(); // Call the drawFour method
        } else if (topCard.getType().equals("Reverse")) {
            ReverseDirection();
        } else if (topCard.getType().equals("Skip")) {
            Skip(); // Call the Skip method
        } else if (topCard.getType().equals("Wild")) {
            Wildcard(); // Call the Wildcard method
        }
    }

    // Method to handle Skip card logic
    public void Skip() {
        System.out.println(players.get(CurrentPlayer).getName() + " played a Skip card!");
        // Skip the next player's turn
        if (Direction.equals("right")) {
            CurrentPlayer = (CurrentPlayer + 1) % numPlayers; // Skip the next player
        } else {
            CurrentPlayer = (CurrentPlayer - 1 + numPlayers) % numPlayers; // Skip the previous player
        }
        System.out.println("Next player's turn skipped.");
    }

    // Method to handle Wild Draw Four logic
    public void drawFour() {
		System.out.println(players.get(CurrentPlayer).getName() + " played a Wild Draw Four!");
		for (int i = 0; i < 4; i++) {
			PlayerDraw(players.get((CurrentPlayer + 1) % numPlayers)); // Next player draws 4 cards
		}
	}
    // Method to handle Wild card logic
    public void Wildcard() {
        System.out.println("Choose the color for the next player to play (Red, Green, Blue, Yellow):");
        String newColor = scanner.next();

        // Validate the color input
        while (!newColor.equalsIgnoreCase("Red") && 
               !newColor.equalsIgnoreCase("Green") && 
               !newColor.equalsIgnoreCase("Blue") && 
               !newColor.equalsIgnoreCase("Yellow")) {
            System.out.println("Invalid color. Please choose Red, Green, Blue, or Yellow:");
            newColor = scanner.next();
        }

        TopCard.setColor(newColor); // Set the new color for the top card
    }

    // Method to draw a card for a player
    public void PlayerDraw(Player P) {
        Card drawnCard = UnoDeck.drawCard();
        if (drawnCard != null) {
            P.addCardHand(drawnCard); // Add the drawn card to the player's hand
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

    // Method to start the game
    public void startGame() {
        while (OpenGame) {
            System.out.println("Current player: " + players.get(CurrentPlayer).getName());
            // Additional game logic can be implemented here
            GameLogic();
        }
    }
}
