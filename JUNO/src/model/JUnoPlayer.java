package model;

/**
 * Rappresenta i giocatori umani. Estende la classe Player, estesa anche dai giocatori artificiali.
 */
public class JUnoPlayer extends Player {

	/**
	 * Numero di partite giocate.
	 */
	private int playedGames;

	/**
	 * Numero di partite vinte.
	 */
	private int wonGames;

	/**
	 * Numero di partite perse.
	 */
	private int lostGames;

	/**
	 * Livello raggiunto.
	 */
	private int level;

	/**
	 * Coostruttore che imposta nome, avatar e livello base.
	 * @param nickname il nickname del giocatore umano
	 * @param avatar l'avatar del giocatore umano
	 */
	public JUnoPlayer(String nickname, String avatar) {
		super(nickname, avatar);
		level = 1;
	}

	/**
	 * Restituisce il numero di partite giocate dal giocatore.
	 * @return il numero di partite giocate
	 */
	public int getPlayedGames() {
		return playedGames;
	}

	/**
	 * Restituisce il numero di partite vinte dal giocatore.
	 * @return il numero di partite vinte
	 */
	public int getWonGames() {
		return wonGames;
	}

	/**
	 * Restituisce il numero di partite perse dal giocatore.
	 * @return il numero di partite perse
	 */
	public int getLostGames() {
		return lostGames;
	}

	/**
	 * Restituisce il livello raggiunto dal giocatore.
	 * @return il livello raggiunto
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * Aggiorna il valore delle partite giocate e di quelle vinte.
	 */
	public void winGame() {
		playedGames++;
		wonGames++;
		if (playedGames % 10 == 0) levelUp();
	}

	/**
	 * Aggiorna il valore delle partite giocate e di quelle perse.
	 */
	public void loseGame() {
		playedGames++;
		lostGames++;
		if (playedGames % 10 == 0) levelUp();
	}

	/**
	 * Fa salire di livello il giocatore.
	 */
	public void levelUp() {
		level++;
	}
}
