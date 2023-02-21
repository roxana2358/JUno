package model;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta i valori delle carte.
 */
public enum Value
{
	ZERO(0), UNO(1), DUE(2), TRE(3), QUATTRO(4), CINQUE(5), SEI(6), SETTE(7), OTTO(8), NOVE(9),
	PESCA_DUE(20), INVERTI(20), SALTO(20), JOLLY(50), PESCA_QUATTRO(50);

	/**
	 * Valore numerico della carta.
	 */
	private final int value;

	/**
	 * Lista contenente tutti i valori.
	 */
	private static final List<Value> values = Arrays.stream(Value.values()).collect(Collectors.toList());

	/**
	 * Lista con i valori per cui e' necessaria una sola carta per mazzo.
	 */
	static final List<Value> onePerDeck = values.subList(0, 1);

	/**
	 * Lista con i valori per cui sono necessarie due carte per mazzo.
	 */
	static final List<Value> twoPerDeck = values.subList(1, 13);

	/**
	 * Lista con i valori per cui sono necessarie quattro carte per mazzo.
	 */
	static final List<Value> fourPerDeck = values.subList(13, values.size());

	/**
	 * Costruttore che assegna il valore numerico alla carta.
	 * @param value il valore numerico della carta
	 */
	Value(int value)	{ this.value = value; }

	/**
	 * Restituisce il valore numerico.
	 * @return il valore numerico della carta
	 */
	public int getValue()	{ return value; }

	/**
	 * Restituisce il valore sotto forma di stringa.
	 * @return la stringa con il valore
	 */
	@Override
	public String toString()	{ return this.name().toLowerCase(); }
}
