import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private ArrayList<Player> players;
    private Deck unoDeck;
    private Card topCard;
    private int currentPlayerIndex;
    private int numMaxPlayers;
    private boolean openGame;
    private String direction;
    private Scanner scanner;

    public Game(int numPlayers, int numMaxPlayers) {
        unoDeck = new Deck();
        players = new ArrayList<>();
        this.numMaxPlayers = numMaxPlayers;
        currentPlayerIndex = 0; // first player
        direction = "right";
        openGame = true;
        topCard = unoDeck.drawCard();
        scanner = new Scanner(System.in);
        
        // Initialize players
        for (int i = 0; i < numPlayers; i++) {
            System.out.println("Enter name for Player " + (i + 1) + ": ");
            String name = scanner.next();
            Player player = new Player(name);
            initialCards(player);
            players.add(player);
        }
    }

    public ArrayList<Player> getPlayers() {
        return players;  // Return the list of players
    }
    
    public   int getCurrentPlayer() {
        return currentPlayerIndex;
    }

    public void initialCards(Player p) {
        for (int i = 0; i < 7; i++) {
            p.addCardHnad(unoDeck.drawCard());
        }
    }

    public void rotate() {
        if (direction.equals("right")) {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        }
    }

    public void playerDraw(Player p) {
        p.addCardHnad(unoDeck.drawCard());
    }

    public void setTopCard(Card card) {
        this.topCard = card;
    }

    public Card getTopCard() {
        return topCard;
    }

    public void GameLogic() {
        // Implement card logic like DrawTwo, Reverse, Skip, etc.
        // This would call methods that check for and handle special card actions
    }
}