package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Rappresenta il tavolo da gioco.
 */
public class JUnoTable {

	/**
	 * Mazzo di pesca.
	 */
	private JUnoDeck drawDeck;

	/**
	 * Mazzo di scarto.
	 */
	private List<JUnoCard> discardDeck;

	/**
	 * Lista con i giocatori del tavolo.
	 */
	private List<Player> playersList;

	/**
	 * Direzione di gioco: true indica il senso orario, false il senso antiorario.
	 */
	private boolean gameDirection;

	/**
	 * Colore della carta in cima al mazzo di scarto.
	 */
	private Color validColor;

	/**
	 * Valore della carta in cima al mazzo di scarto.
	 */
	private Value validValue;

	/**
	 * Indice del giocatore di turno.
	 */
	private int playersTurn;

	/**
	 * Array con i punti dei giocatori del tavolo.
	 */
	private int[] points;

	/**
	 * Mani dei giocatori del tavolo.
	 */
	private List<List<JUnoCard>> playersHands;

	/**
	 * Indica se la carta in cima al mazzo di scarto, al primo turno, e' un jolly.
	 */
	private boolean isFirstCardWild;

	/**
	 * Indica se la carta in cima al mazzo di scarto e' anche la prima.
	 */
	private boolean isFirstTurn;

	/**
	 * Indica se il giocatore umano ha detto "uno".
	 */
	private boolean hasPushedUno;

	/**
	 * Contiene il messaggio da stampare a video, legato al tipo di carta in cima al mazzo di scarto.
	 */
	private String cardMessage;

	/**
	 * Indica se la funzione della carta azione in cima al mazzo di scarto e' stata sfruttata.
	 */
	private boolean isTopCardUsed;

	/**
	 * Contiene il messaggio da stampare a video, legato al cambio di colore del gioco.
	 */
	private String colorMessage;

	/**
	 * Indica se e' stato cambiato il colore di gioco.
	 */
	private boolean isColorMessage;

	/**
	 * Contiene il messaggio da stampare a video, legato al numero di carte in mano degli avversari artificiali.
	 */
	private String unoMessage;

	/**
	 * Indica se uno degli avversari artificiali ha una sola carta in mano.
	 */
	private boolean isUnoMessage;

	/**
	 * Contiene il messaggio da stampare a video, legato alla pesca di una carta da parte degli avversari
	 * artificiali.
	 */
	private String drawCardMessage;

	/**
	 * Indica se uno degli avversari artificiali ha pescato una carta.
	 */
	private boolean isDrawCardMessage;

	/**
	 * Costruttore del tavolo, che imposta i giocatori del tavolo, azzera i punti e riporta allo stato
	 * iniziale tutti gli altri campi.
	 * @param player il giocatore umano indicato per la partita
	 */
	public JUnoTable(JUnoPlayer player) {
		setPlayersList(player);
		points = new int[]{0, 0, 0, 0};
		resetTable();
	}

	/**
	 * Riporta allo stato iniziale il tavolo.
	 */
	public void resetTable() {
		drawDeck = new JUnoDeck();
		drawDeck.shuffleDeck();
		playersTurn = getFirstPlayer();
		gameDirection = true;
		discardDeck = new ArrayList<>();
		playersHands = new ArrayList<>();
		for (int i = 0; i < this.playersList.size(); i++) {
			ArrayList<JUnoCard> hand = new ArrayList<>(drawDeck.pullMultipleCards(7));
			playersHands.add(hand);
		}
		isTopCardUsed = false;
		isColorMessage = false;
		isDrawCardMessage = false;
		isUnoMessage = false;
		hasPushedUno = false;
		JUnoCard topCard = drawDeck.pullCard();
		while (topCard.getValue().equals(Value.PESCA_QUATTRO)) {
			drawDeck.putCards(Arrays.asList(topCard));
			topCard =  drawDeck.pullCard();
		}
		discardDeck.add(topCard);
		validColor = getTopCard().getColor();
		validValue = getTopCard().getValue();
		isFirstCardWild = validColor.equals(Color.JOLLY);
		isFirstTurn = true;
	}

	/**
	 * Imposta giocatore umano e giocatori artificiali per il tavolo.
	 * @param player il giocatore umano
	 */
	public void setPlayersList(JUnoPlayer player) {
		Player bowser = new JUnoOpponent("Bowser", "./src/graphics/op1.png");
		Player wario = new JUnoOpponent("Wario", "./src/graphics/op2.png");
		Player waluigi = new JUnoOpponent("Waluigi", "./src/graphics/op3.png");
		playersList = new ArrayList<>();
		playersList.add(bowser);
		playersList.add(wario);
		playersList.add(waluigi);
		playersList.add(player);
	}

	/**
	 * Permette di stabilire il primo giocatore del turno.
	 * @return l'indice del primo giocatore del turno
	 */
	private int getFirstPlayer() {
		List<JUnoCard> cards = new ArrayList<>();
		for (int i = 0; i < playersList.size(); i++)
			cards.add(drawDeck.pullCard());
		// il primo a giocare sara' quello con la carta piu' alta
		JUnoCard starter = cards.stream().sorted().collect(Collectors.toList()).get(playersList.size()-1);
		drawDeck.putCards(cards);
		drawDeck.shuffleDeck();
		return cards.indexOf(starter);
	}

	/**
	 * Restituisce l'indice del giocatore di turno.
	 * @return l'indice del giocatore di turno
	 */
	public int getPlayersTurn() { return playersTurn; }

	/**
	 * Restituisce il colore della carta in cima al mazzo di scarto.
	 * @return il colore della carta in cima al mazzo di scarto
	 */
	public Color getValidColor() { return validColor; }

	/**
	 * Restituisce il valore della carta in cima al mazzo di scarto.
	 * @return il valore della carta in cima al mazzo di scarto
	 */
	public Value getValidValue() { return validValue; }

	/**
	 * Restituisce la carta in cima al mazzo di scarto.
	 * @return la carta in cima al mazzo di scarto
	 */
	public JUnoCard getTopCard() { return discardDeck.get(discardDeck.size()-1); }

	/**
	 * Controlla se la funzione della carta azione in cima al mazzo di scarto e' stata sfruttata.
	 * @return indica se la funzione della carta e' stata sfruttata
	 */
	public boolean getIsTopCardUsed() { return isTopCardUsed; }

	/**
	 * Restituisce il punteggio del giocatore il cui indice e' fornito in input.
	 * @param player l'indice del giocatore i cui punti devono essere restituiti
	 * @return i punti ottenuti dal giocatore
	 */
	public int getPoints(int player) { return points[player]; }

	/**
	 * Controlla se il giocatore umano ha detto "uno".
	 * @return indica se il giocatore umano ha detto "uno"
	 */
	public boolean getHasPushedUno() { return hasPushedUno; }

	/**
	 * Restituisce la lista dei giocatori del tavolo.
	 * @return la lista dei giocatori
	 */
	public List<Player> getPlayersList() { return this.playersList; }

	/**
	 * Restituisce il giocatore umano della partita corrente.
	 * @return il giocatore umano
	 */
	public JUnoPlayer getCurrentJunoPlayer() { return (JUnoPlayer) playersList.get(3); }

	/**
	 * Restituisce il nome del giocatore di turno.
	 * @return il nome del giocatore di turno
	 */
	public String getPlayersTurnName() { return playersList.get(playersTurn).getNickname(); }

	/**
	 * Restituisce il messaggio che dev'essere stampato a video.
	 * @return il messaggio da visualizzare
	 */
	public String getCardMessage() {
		switch (validValue) {
			case PESCA_DUE -> cardMessage = getPlayersTurnName()+" pesca due carte.";
			case INVERTI -> cardMessage = "E' cambiata la direzione del gioco!";
			case SALTO -> cardMessage = getPlayersTurnName()+" salta il turno.";
			case PESCA_QUATTRO -> cardMessage = getPlayersTurnName() + " pesca quattro carte.";
		}
		return cardMessage;
	}

	/**
	 * Restituisce il messaggio con il nuovo colore di gioco, se questo e' stato cambiato.
	 * @return il messaggio legato al nuovo colore di gioco
	 */
	public String getColorMessage() {
		if (isColorMessage) {
			isColorMessage = false;
			return colorMessage;
		}
		return null;
	}

	/**
	 * Restituisce il messaggio con il nome del giocatore artificiale che ha una sola carta in mano, se necessario.
	 * @return il messaggio legato al giocatore con una sola carta in mano
	 */
	public String getUnoMessage() {
		if (isUnoMessage) {
			isUnoMessage = false;
			return unoMessage;
		}
		return null;
	}

	/**
	 * Restituisce il messaggio con il nome del giocatore artificiale che ha pescato una carta, se necessario.
	 * @return il messaggio legato al giocatore che ha pescato una carta
	 */
	public String getDrawCardMessage() {
		if (isDrawCardMessage) {
			isDrawCardMessage = false;
			return drawCardMessage;
		}
		return null;
	}

	/**
	 * Restituisce la mano di carte del giocatore indicato con il numero passato in input.
	 * @param player l'indice del giocatore la cui mano di carte deve essere restituita
	 * @return la mano di carte del giocatore indicato
	 */
	public List<JUnoCard> getPlayersHand(int player)  { return playersHands.get(player); }

	/**
	 * Restituisce la mano di carte del giocatore di turno.
	 * @return la mano di carte del giocatore di turno
	 */
	public List<JUnoCard> getPlayersTurnHand() { return getPlayersHand(playersTurn); }

	/**
	 * Restituisce la mano del giocatore umano della partita.
	 * @return la mano del giocatore umano
	 */
	public List<JUnoCard> getCurrentJunoPlayersHand() { return playersHands.get(3); }

	/**
	 * Restituisce il numero di carte in mano al giocatore umano.
	 * @return il numero di carta in mano al giocatore umano
	 */
	public int getCurrentJunoPlayersHandSize() { return getCurrentJunoPlayersHand().size(); }

	/**
	 * Controlla se la mano del giocatore di turno e' vuota.
	 * @return indica se la mano del giocatore di turno e' vuota
	 */
	public boolean isPlayersTurnHandEmpty() { return getPlayersTurnHand().isEmpty(); }

	/**
	 * Controlla se la mano del giocatore umano e' vuota.
	 * @return indica se la mano del giocatore umano e' vuota
	 */
	public boolean isCurrentJunoPlayersHandEmpty() { return getCurrentJunoPlayersHand().isEmpty(); }

	/**
	 * Controlla se e' il turno del giocatore umano.
	 * @return indica se e' il turno del giocatore umano
	 */
	public boolean isCurrentJunoPlayersTurn() { return playersTurn == 3; }

	/**
	 * Passa al turno del giocatore successivo.
	 */
	public void nextPlayer() {
		if (gameDirection) playersTurn = (playersTurn +1) % playersList.size();
		else playersTurn = (playersTurn -1) % playersList.size();
		if (playersTurn == -1) playersTurn = 3;
		if (playersTurn == 4) playersTurn = 0;
	}

	/**
	 * Controlla se la mano del giocatore di turno contiene una sola carta.
	 * @return indica se la mano del giocatore di turno contiene una sola carta
	 */
	public boolean hasUno() { return playersHands.get(playersTurn).size() == 1; }

	/**
	 * Permette al giocatore umano di dire "uno".
	 */
	public void callUno() { hasPushedUno = true; }

	/**
	 * Penalizza il giocatore umano nel caso in cui abbia finito le carte senza dire "uno".
	 */
	public void hasNotCalledUno() {
		getCurrentJunoPlayersHand().addAll(drawDeck.pullMultipleCards(2));
	}

	/**
	 * Controlla che la carta fornita in input sia giocabile.
	 * @param card la carta da controllare
	 * @return indica se la carta puo' essere giocata
	 */
	public boolean isCardValid(JUnoCard card) {
		if (card.getColor().equals(validColor) || card.getValue().equals(validValue)
				|| card.getColor().equals(Color.JOLLY))
			return true;
		else if (isFirstCardWild) {
			isFirstCardWild = false;
			return true;
		}
		return false;
	}

	/**
	 * Permette di pescare una carta dal mazzo di pesca.
	 */
	public void drawCard() {
		// se questo e' vuoto, il mazzo di scarto viene mischiato e va a sostituire quello di pesca
		if (drawDeck.isEmpty()) {
			drawDeck.replaceDeck(discardDeck);
			drawDeck.shuffleDeck();
		}

		// altrimenti una carta viene pescata
		getPlayersTurnHand().add(drawDeck.pullCard());
		if (!isCurrentJunoPlayersTurn()) {
			isDrawCardMessage = true;
			drawCardMessage = getPlayersTurnName() + " ha pescato una carta.";
		}
		if (isCurrentJunoPlayersTurn()) {
			hasPushedUno = false;
		}
	}

	/**
	 * Permette ai giocatori artificiali di prendere una carta giocabile.
	 * @return la carta da giocare, se presente nella mano
	 */
	public JUnoCard getValidCard() {
		List<JUnoCard> hand = getPlayersTurnHand();
		for (JUnoCard card:hand) {
			if (isCardValid(card))
				return card;
		}
		return null;
	}

	/**
	 * Da eseguire prima di ogni turno, bisogna guardare la carta in cima al mazzo di scarto e controllare che
	 * non sia una carta azione: in caso positivo il giocatore pesca carte e/o salta il turno, altrimenti puo'
	 * usare una delle proprie carte.
	 * @return un valore che indica se il giocatore puo' usare una delle proprie carte
	 */
	public boolean seeTopCard() {
		List<JUnoCard> hand = getPlayersTurnHand();
		if (!isTopCardUsed) {
			if (validValue.equals(Value.PESCA_DUE)) {
				isTopCardUsed = true;
				try {
					hand.addAll(drawDeck.pullMultipleCards(2));
				} catch (IllegalArgumentException ex) {
					drawDeck.putCards(discardDeck);
					drawDeck.shuffleDeck();
					hand.addAll(drawDeck.pullMultipleCards(2));
				}
				isFirstTurn = false;
				return false;
			} else if (validValue.equals(Value.INVERTI)) {
				isTopCardUsed = true;
				gameDirection = !gameDirection;
				if (!isFirstTurn) nextPlayer();
				isFirstTurn = false;
				return false;
			} else if (validValue.equals(Value.SALTO)) {
				isTopCardUsed = true;
				isFirstTurn = false;
				return false;
			} else if (validValue.equals(Value.PESCA_QUATTRO)) {
				isTopCardUsed = true;
				try {
					hand.addAll(drawDeck.pullMultipleCards(4));
				} catch (IllegalArgumentException ex) {
					drawDeck.putCards(discardDeck);
					drawDeck.shuffleDeck();
					hand.addAll(drawDeck.pullMultipleCards(4));
				}
				isFirstTurn = false;
				return false;
			}
		}
		isFirstTurn = false;
		return true;
	}

	/**
	 * Permette di usare la carta fornita in input insieme a un colore da usare per il cambio colore.
	 * @param card la carta da giocare
	 * @param requestedColor il colore da usare per il cambio colore
	 */
	public void submitCard(JUnoCard card, Color requestedColor) {
		isFirstTurn = false;
		if (card.getColor().equals(Color.JOLLY)) {
			colorMessage = "Il colore richiesto e' " + requestedColor + ".";
			isColorMessage = true;
			validColor = requestedColor;
			validValue = card.getValue();
		}
		if (card.getValue().equals(Value.PESCA_QUATTRO)) {
			isTopCardUsed = false;
		}
		else if (!card.getColor().equals(Color.JOLLY)){
			validColor = card.getColor();
			validValue = card.getValue();
			if (validValue.equals(Value.PESCA_DUE) || validValue.equals(Value.SALTO) || validValue.equals(Value.INVERTI))
				isTopCardUsed = false;
		}
		getPlayersTurnHand().remove(card);
		discardDeck.add(card);
		if (hasUno() && playersTurn != 3) {
			unoMessage = getPlayersTurnName() + " ha UNA carta!";
			isUnoMessage = true;
		}
	}

	/**
	 * Controlla se il giocatore umano puo' usare una propria carta/pescare oppure deve seguire le indicazioni
	 * di una carta azione.
	 * @return indica se il giocatore umano puo' usare una carta
	 */
	public boolean isCurrentJunoPlayerAllowedToUseAnyCard() {
		if (isTopCardUsed) return true;
		Value value = getTopCard().getValue();
		return !value.equals(Value.PESCA_DUE) && !value.equals(Value.PESCA_QUATTRO) &&
				!value.equals(Value.SALTO) && !value.equals(Value.INVERTI);
	}

	/**
	 * Svolge le azioni di un giocatore artificiale, restituendo un valore positivo nel caso in cui il
	 * giocatore artificiale abbia usato/pescato una carta, un valore negativo nel caso in cui abbia seguito
	 * le indicazioni di una carta azione.
	 * @return indica se il giocatore artificiale ha usato una carta
	 */
	public boolean playOpponentsTurn() {
		if (!seeTopCard()) {
			return false;
		}
		Color color = Color.getColor(new Random().nextInt(4));
		JUnoCard card = getValidCard();
		if (card == null) drawCard();
		else submitCard(card, color);
		return true;
	}

	/**
	 * Calcola i punteggi alla fine del turno.
	 */
	public void roundsEnd() {
		int sum = 0;
		JUnoCard topCard = getTopCard();
		if (topCard.getValue().equals(Value.PESCA_DUE) || topCard.getValue().equals(Value.PESCA_QUATTRO)) {
			sum += topCard.getValue().getValue();
		}
		for (int i = 0; i < playersList.size(); i++) {
			List<JUnoCard> hand = getPlayersHand(i);
			for (JUnoCard card : hand) {
				sum += card.getValue().getValue();
			}
		}
		points[playersTurn] += sum;
	}
}
