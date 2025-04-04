
public class Skip extends Card {
    public Skip(String color) {
        super(color, "Skip", -1);
    }
    
    // Method to apply the effect of Skip
    public void applyEffect(Game game) {
        game.Skip();
    }
}