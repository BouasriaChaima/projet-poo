import java.util.ArrayList; 
import java.util.Collections; 
 
public class Deck { 
    private ArrayList<Card> cards; 
    public Deck() { 
        cards = new ArrayList<>(); 
        initializeDeck(); 
        shuffle(); 
    } 
 
    // une methode to initialize the deck with all card types 
    private void initializeDeck() { 
        // on ajoute number cards and special cards to the deck 
        for (String color : new String[]{"Red", "Green", "Blue", "Yellow"}) { 
            //++ number cards (0-9) 
            for (int i = 0; i <= 9; i++) { 
                cards.add(new Card(color, "Number", i)); 
            } 
            // ++ special cards 
            cards.add(new DrawTwo(color)); // ++ Draw Two cards 
            cards.add(new Skip(color));     // ++ Skip cards 
            cards.add(new Reverse(color));   // ++ Reverse cards 
        } 
        // ++ Wild Cards  
        for (int i = 0; i < 4; i++) { 
            cards.add(new WildCard());       // ++ Wild cards 
            cards.add(new WildDrawFour());   // ++ Wild Draw Four cards 
        } 
    } 
 
    // method to shuffle the deck 
    public void shuffle() { 
        Collections.shuffle(cards); 
    } 
 
    //method to draw a card from the deck 
    public Card drawCard() { 
        if (cards.isEmpty()) { 
            System.out.println("The deck is empty!"); 
            return null;  
        } 
        return cards.remove(cards.size() - 1); // draws the ,top card, from the deck 
    } 
 
    //method to reset the deck (come back) 
    public void resetDeck() { 
        cards.clear(); 
        initializeDeck(); 
        shuffle(); 
    } 
 
    // method to get the number of remaining cards in the deck () 
    public int getRemainingCards() { 
        return cards.size(); 
    } }