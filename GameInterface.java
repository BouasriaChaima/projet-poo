import java.util.Scanner;

public class UnoGameInterface {
    private Game game;
    private Scanner scanner;

    public UnoGameInterface() {
        scanner = new Scanner(System.in);
    }

    public void startGame() {
        System.out.println("Welcome to UNO!");
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        game = new Game(numPlayers, 4); // Assuming max players is 4

        while (true) {
            displayGameState();
            Player currentPlayer = game.getPlayers().get(game.getCurrentPlayer()); // Get current player
            System.out.println(currentPlayer.getName() + "'s turn.");

            System.out.println("Choose an action:");
            System.out.println("1. Play a card");
            System.out.println("2. Draw a card");
            System.out.println("3. Quit");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    playCard(currentPlayer);
                    break;
                case 2:
                    drawCard(currentPlayer);
                    break;
                case 3:
                    System.out.println("Thanks for playing!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            game.GameLogic(); // Process game logic after each turn
        }
    }

    private void displayGameState() {
        System.out.println("Top Card: " + game.getTopCard());
        for (Player player : game.getPlayers()) {
            System.out.println(player.getName() + "'s hand: " + player.getplayerHnad());
        }
    }

    private void playCard(Player player) {
        System.out.println("Your hand: " + player.getplayerHnad());
        System.out.print("Enter the index of the card to play: ");
        int cardIndex = scanner.nextInt();

        // Validate the card index
        if (cardIndex < 0 || cardIndex >= player.playerHandSize()) {
            System.out.println("Invalid card index. Try again.");
            return;
        }

        Card playedCard = player.getplayerHnad().get(cardIndex); // Get the card to be played
        Card topCard = game.getTopCard();

        // Validate if the card can be played
        if (player.canPlayCard(playedCard, topCard)) {
            player.playCard(cardIndex); // Play the card
            game.setTopCard(playedCard); // Update the top card

            // Handle special card effects
            if (playedCard.getType().equals("Wild")) {
                game.Wildcard(); // Call the Wildcard method from Game
            } else if (playedCard.getType().equals("Wild Draw Four")) {
                game.drawFour(); // Call the drawFour method from Game
            } else if (playedCard.getType().equals("Skip")) {
                game.Skip(); // Call the Skip method from Game
            } else if (playedCard.getType().equals("Reverse")) {
                game.ReverseDirection(); // Call the ReverseDirection method from Game
            }
        } else {
            System.out.println("You cannot play that card. Please choose another action.");
        }
    }

    private void drawCard(Player player) {
        Card drawnCard = game.getDeck().drawCard(); // Draw a card from the deck
        if (drawnCard != null) {
            player.drawCard(drawnCard); // Add the drawn card to the player's hand
            System.out.println(player.getName() + " drew: " + drawnCard.getColor() + " " + drawnCard.getType());
        }
    }

    public static void main(String[] args) {
        UnoGameInterface ui = new UnoGameInterface();
        ui.startGame();
    }
}
