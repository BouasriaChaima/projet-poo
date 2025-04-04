
public class Reverse extends Card {
    public Reverse(String color) {
        super(color, "Reverse", -1);
    }
    
    // Method to apply the effect of Reverse
    public void applyEffect(Game game) {
        game.ReverseDirection();
    }
}
