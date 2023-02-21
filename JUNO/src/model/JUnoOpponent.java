package model;

/**
 * Rappresenta i giocatori artificiali. Estende la classe Player, estesa anche dai giocatori umani.
 */
public class JUnoOpponent extends Player {

    /**
     * Costruttore che assegna il nome e l'avatar al giocatore artificiale.
     * @param nickname il nome dell'avversario artificiale
     * @param avatar l'avatar dell'avversario artificiale
     */
    public JUnoOpponent(String nickname, String avatar) {
        super(nickname, avatar);
    }
}
