public class Card {
    private String color;
    private String type; 
    private int value; // 0-9 pour num des cards

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
}