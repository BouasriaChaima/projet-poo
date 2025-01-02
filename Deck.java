import java.util.ArrayList;
import java.util.Collections;

import javax.smartcardio.Card;

public class Deck {
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    // une methode to initialize the deck with all card types
    private void initializeDeck() {
        // on ajoute number cards and special cards to deck
        for (String color : new String[] { "Red", "Green", "Blue", "Yellow" }) {

            cards.add(new Card(color, "Number", 0)); // one 0 card for each color

            // adding two of each number card (1 - 9)
            for (int i = 1; i <= 9; i++) {
                cards.add(new Card(color, "Number", i)); // 1st instance
                cards.add(new Card(color, "Number", i)); // scc instance
            }

            // ading two special action cards
            cards.add(new DrawTwo(color)); // two Draw Two cards
            cards.add(new DrawTwo(color)); // second Draw Two card
            cards.add(new Skip(color)); // two Skip cards
            cards.add(new Skip(color)); // second Skip card
            cards.add(new Reverse(color)); // two Reverse cards
            cards.add(new Reverse(color)); // second Reverse card
        }

        // adding Wild and Wild DrawFour cards
        for (int i = 0; i < 4; i++) {
            cards.add(new WildCard()); // four Wild cards
            cards.add(new WildDrawFour()); // four Wild Draw Four cards
        }
    }

    // method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(cards);
    }

    // method to draw a card from the deck
    public Card drawCard() {
        if (cards.isEmpty()) {
            System.out.println("The deck is empty!");
            return null;
        }
        return cards.remove(cards.size() - 1); // draws the ,top card, from the deck
    }

    // method to reset the deck (come back)
    public void resetDeck() {
        cards.clear();
        initializeDeck();
        shuffle();
    }

    // method to get the number of remaining cards in the deck ()
    public int getRemainingCards() {
        return cards.size();
    }
}