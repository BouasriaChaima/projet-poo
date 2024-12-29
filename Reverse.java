import java.util.ArrayList;
import java.util.Collections;

public class Reverse extends Card {
    public Reverse(String color) {
        super(color, "Reverse", -1);
    }

    // mazal ici additional methods specific to Reverse if needed
    public void applyEffect(ArrayList<Player> players, int currentPlayerIndex) {
        // Idem logic : to reverse the order of play
        System.out.println("The direction of play has been reversed!");
        Collections.reverse(players); // Reversing the order of players
        // i wanna keep the same player, so no need to update currentPlayerIndex here
    }
}
