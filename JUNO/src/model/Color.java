package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta i colori delle carte.
 */
public enum Color
{
	BLU, GIALLO, ROSSO, VERDE, JOLLY;

	/**
	 * Lista contenente tutti i colori.
	 */
	private static final List<Color> colors = Arrays.stream(Color.values()).collect(Collectors.toList());

	/**
	 * Lista con i colori "ordinari".
	 */
	static final List<Color> ordinaryColors = colors.subList(0, 4);

	/**
	 * Lista con i colori speciali.
	 */
	static final List<Color> specialColors = colors.subList(4, colors.size());

	/**
	 * Restituisce il colore in base alla posizione nella lista.
	 * @param i l'indice del colore nella lista
	 * @return il colore presente all'indice fornito in input
	 */
	public static Color getColor(int i)	{ return colors.get(i); }

	/**
	 * Restituisce il colore sotto forma di stringa.
	 * @return la stringa con il colore
	 */
	@Override
	public String toString() { return this.name().toLowerCase(); }
}
