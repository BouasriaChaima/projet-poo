import java.util.ArrayList;

public class Skip extends Card {
    public Skip1(String color) {
        super(color, "Skip", -1);
    }

    // method to apply the effect of the Skip card
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex) {
        // identifying next player who will be skipped
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        System.out.println("The next player, " + nextPlayer.getName() + ", has been skipped!");

        // skip the next player's turn by advancing the index
        // NOTE!!!: the actual index update should be handled in the game logic, pas
        // ici.
        // this method just informs that the next player is skipped (.)
    }
}
