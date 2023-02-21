package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Rappresenta il mazzo di carte da gioco.
 */
public class JUnoDeck
{
	/**
	 * Mazzo di carte.
	 */
	private List<JUnoCard> deck;

	/**
	 * Numero di carte nel mazzo.
	 */
	private int cardsInDeck;

	/**
	 * Costruttore che crea il mazzo di carte.
	 */
	public JUnoDeck() {
		resetDeck();
	}

	/**
	 * Riporta allo stato iniziale il mazzo di carte.
	 */
	public void resetDeck() {
		List<JUnoCard> deck = new ArrayList<>();
		for (Color color:Color.ordinaryColors) {
			for (Value value:Value.onePerDeck)	deck.add(new JUnoCard(value, color));
			for (Value value:Value.twoPerDeck) {
				for (int i = 0; i < 2; i++)			deck.add(new JUnoCard(value, color));
			}
		}
		for (Color color:Color.specialColors) {
			for (Value value:Value.fourPerDeck)	{
				for (int i = 0; i < 4; i++)			deck.add(new JUnoCard(value, color));
			}
		}
		this.deck = deck;
		cardsInDeck = 108;
	}

	/**
	 * Sostituisce il mazzo di carte con quello fornito in input.
	 * @param newDeck il mazzo di carte con cui quello vecchio deve essere sostituito
	 */
	public void replaceDeck(List<JUnoCard> newDeck) {
		this.deck = newDeck;
		cardsInDeck = newDeck.size();
	}

	/**
	 * Mischia le carte nel mazzo.
	 */
	public void shuffleDeck() {
		Collections.shuffle(deck);
	}

	/**
	 * Controlla se nel mazzo ci sono carte o meno.
	 * @return indica se nel mazzo ci sono carte
	 */
	public boolean isEmpty()	{ return deck.isEmpty(); }

	/**
	 * Prende e rimuove una carta dal mazzo.
	 * @return la carta presa dal mazzo
	 */
	public JUnoCard pullCard() {
		if (isEmpty()) {
			throw new IllegalArgumentException("Non ci sono piu' carte nel mazzo!");
		}
		JUnoCard card = deck.get(cardsInDeck-1);
		deck.remove(--cardsInDeck);
		return card;
	}

	/**
	 * Permette di prendere piu' carte dal mazzo.
	 * @param number il numero di carte da prendere
	 * @return la lista con le carte prese
	 */
	public List<JUnoCard> pullMultipleCards(int number) {
		if (number < 1) throw new IllegalArgumentException("Non e' possibile pescare un numero di carte minore di 1!");
		else if (number > cardsInDeck) throw new IllegalArgumentException("Le carte rimaste nel mazzo non sono sufficienti!");
		List<JUnoCard> cards = new ArrayList<>();
		for (int i = 0; i < number; i++)	cards.add(pullCard());
		return cards;
	}

	/**
	 * Permette di rimettere nel mazzo alcune carte.
	 * @param cards la lista con le carte da rimettere nel mazzo
	 */
	public void putCards(List<JUnoCard> cards) {
		deck.addAll(cards);
		cardsInDeck += cards.size();
	}
}
