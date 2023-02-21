package model;

import javax.swing.*;

/**
 * Rappresenta le carte di gioco. Implementa Comparable per rendere le carte comparabili in base al
 * loro valore numerico.
 */
public class JUnoCard implements Comparable<JUnoCard>
{
	/**
	 * Valore della carta.
	 */
	private final Value value;

	/**
	 * Colore della carta.
	 */
	private final Color color;

	/**
	 * Costruttore che assegna il valore e il colore alla carta.
	 * @param value il valore da assegnare
	 * @param color il colore da assegnare
	 */
	public JUnoCard(final Value value, final Color color) {
		this.value = value;
		this.color = color;
	}

	/**
	 * Restituisce il valore della carta.
	 * @return il valore della carta
	 */
	public Value getValue()	{ return value; }

	/**
	 * Restituisce il colore della carta.
	 * @return il colore della carta
	 */
	public Color getColor()	{ return color; }

	/**
	 * Restituisce la carta sotto forma di stringa.
	 * @return la stringa con la carta
	 */
	@Override
	public String toString() { return color.toString()+"-"+value.toString(); }

	/**
	 * Restituisce l'immagine della carta.
	 * @return l'immagine della carta
	 */
	public ImageIcon getImage() {
		return new ImageIcon("./src/graphics/"+ this +".png");
	}

	/**
	 * Restituisce il valore ottenuto dal confronto tra due carte in base al loro valore numerico.
	 * @param o la carta con cui fare il confronto
	 * @return il valore del confronto tra i due valori numerici delle carte
	 */
	@Override
	public int compareTo(JUnoCard o) {
		return Integer.compare(this.value.getValue(), o.value.getValue());
	} 
}
