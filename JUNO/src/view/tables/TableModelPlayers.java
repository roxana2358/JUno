package view.tables;

import model.JUnoPlayer;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta il modello per la tabella con i giocatori.
 */
public class TableModelPlayers extends AbstractTableModel {

    /**
     * Lista con i giocatori.
     */
    private List<JUnoPlayer> players;

    /**
     * Array con i nomi delle colonne nella tabella.
     */
    private String[] columns;

    /**
     * Costruttore che assegna i nomi alle colonne della tabella.
     */
    public TableModelPlayers() {
        columns = new String[]{ "Avatar", "Nickname", "Giocate", "Vinte", "Perse", "Livello" };
    }

    /**
     * Permette d'impostare la lista dei giocatori.
     * @param players la lista dei giocatori
     */
    public void setData(List<JUnoPlayer> players) {
        this.players = players;
    }

    /**
     * Restituisce il numero di righe presenti nella tabella.
     * @return il numero di righe
     */
    @Override
    public int getRowCount() {
        return players.size();
    }

    /**
     * Restituisce il nome della colonna il cui indice e' fornito in input.
     * @param column l'indice della colonna richiesta
     * @return il nome della colonna
     */
    @Override
    public String getColumnName(int column) {
        return columns[column];
    }

    /**
     * Restituisce il tipo di dato contenuto nella colonna il cui indice e' fornito in input.
     * @param column l'indice della colonna richiesta
     * @return il tipo di dato contenuto nella colonna
     */
    @Override
    public Class getColumnClass(int column) {
        return (column == 0) ? Icon.class : Object.class;
    }

    /**
     * Restituisce il numero di colonne presenti nella tabella.
     * @return il numero di colonne
     */
    @Override
    public int getColumnCount() {
        return 6;
    }

    /**
     * Restituisce il valore contenuto nella casella corrispondente alla riga e colonna fornite in input.
     * @param rowIndex il numero di riga richiesta
     * @param columnIndex il numero di colonna richiesta
     * @return il valore contenuto nella casella
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        JUnoPlayer player = players.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return new ImageIcon(new ImageIcon(player.getAvatar())
                        .getImage().getScaledInstance(55, 55, Image.SCALE_SMOOTH));
            case 1:
                return player.getNickname();
            case 2:
                return player.getPlayedGames();
            case 3:
                return player.getWonGames();
            case 4:
                return player.getLostGames();
            case 5:
                return player.getLevel();
            default:
                return null;
        }
    }

    /**
     * Restituisce la lista dei nickname dei giocatori.
     * @return la lista dei nickname dei giocatori
     */
    public List<String> getPlayersNicknames() {
        return players.stream().map(player->player.getNickname()).collect(Collectors.toList());
    }
}
