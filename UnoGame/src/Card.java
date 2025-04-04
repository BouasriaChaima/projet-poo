
public class Card {
    private String color;
    private String type;
    private int value; // 0-9 for number cards

    public Card(String color, String type, int value) {
        this.color = color;
        this.type = type;
        this.value = value;
    }

    // Getter&Setter for color
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }

    // Getter&Setter for type
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    // Getter&Setter for value
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        // Display the card's color, type, and value (if applicable)
        if (value >= 0) {
            return color + " " + type + " " + value; // For number cards
        } else {
            return color + " " + type; // For special cards (like Wild, Draw Two, etc.)
        }
    }
}