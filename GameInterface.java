import java.util.ArrayList;
import java.util.Scanner;

public class GameInterface {
    private Game game;
    private Scanner scanner;

    public GameInterface(int numPlayers, int maxPlayers) {
        game = new Game(numPlayers, maxPlayers);
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        while (true) {
            printGameStatus();
            Player currentPlayer = game.getPlayers().get(game.getCurrentPlayer());
            System.out.println("It's " + currentPlayer.getName() + "'s turn.");

            System.out.println("Your hand: ");
            displayHand(currentPlayer);

            System.out.println("Top card: " + game.getTopCard().getColor() + " " + game.getTopCard().getType());
            System.out.println("1. Play a card");
            System.out.println("2. Draw a card");
            System.out.println("3. Skip turn");

            int choice = scanner.nextInt();

            if (choice == 1) {
                playCard(currentPlayer);
            } else if (choice == 2) {
                drawCard(currentPlayer);
            } else if (choice == 3) {
                game.rotate(); // Skip the turn by rotating to the next player
            }

            game.GameLogic(); // Execute the game logic (handle special card effects)

            if (currentPlayer.playerHandSize() == 0) {
                System.out.println(currentPlayer.getName() + " wins the game!");
                break;
            }
        }
    }

    private void printGameStatus() {
        System.out.println("\n------ Game Status ------");
        System.out.println("Number of players: " + game.getPlayers().size());
        System.out.println("Current player: " + game.getPlayers().get(game.getCurrentPlayer()); // Get current player
    }

    private void displayHand(Player player) {
        for (int i = 0; i < player.playerHandSize(); i++) {
            Card card = player.getplayerHand().get(i);  // Corrected method name
            System.out.println(i + ". " + card.getColor() + " " + card.getType());
        }
    }

    private void playCard(Player currentPlayer) {
        System.out.println("Select a card to play by entering its index: ");
        int cardIndex = scanner.nextInt();
        if (currentPlayer.canPlayCard(currentPlayer.getplayerHand().get(cardIndex), game.getTopCard())) {  // Corrected method name
            Card playedCard = currentPlayer.playCard(cardIndex);
            game.setTopCard(playedCard);
        } else {
            System.out.println("You cannot play that card!");
        }
    }

    private void drawCard(Player currentPlayer) {
        game.playerDraw(currentPlayer);
        System.out.println(currentPlayer.getName() + " drew a card.");
    }
}