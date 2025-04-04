import java.util.Scanner;

public class UnoGameInterface {
    private Game game;
    private Scanner scanner;

    public UnoGameInterface() {
        scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("Welcome to UNO!");
        System.out.print("Enter number of players (2-4): ");
        int numPlayers = scanner.nextInt();
        
        // Validate player count
        while (numPlayers < 2 || numPlayers > 4) {
            System.out.println("Invalid number of players. Please enter a number between 2 and 4:");
            numPlayers = scanner.nextInt();
        }
        
        game = new Game(numPlayers, 4); // Assuming max players is 4
        game.startGame();
        
        System.out.println("Game Over. Thanks for playing!");
    }

    public static void main(String[] args) {
        UnoGameInterface ui = new UnoGameInterface();
        ui.start();
    }
}