package view.frames;

import view.tables.PlayersTable;

import javax.swing.*;

/**
 * Rappresenta il frame di base per la gestione dei profili dei giocatori.
 */
public abstract class PlayersFrame extends SmallBasicFrame {

    /**
     * Tabella con i giocatori.
     */
    private PlayersTable playersTable;

    /**
     * Costruttore del frame per la gestione dei giocatori.
     * @param titolo il titolo da assegnare al frame
     * @param playersTable la tabella con i giocatori
     */
    public PlayersFrame(String titolo, PlayersTable playersTable) {
        super(titolo);
        this.playersTable = playersTable;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    /**
     * Restituisce la tabella con i giocatori.
     * @return la tabella con i giocatori
     */
    public PlayersTable getPlayersTable() { return this.playersTable; }
}