package view.tables;

import model.JUnoPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Rappresenta la tabella con i giocatori.
 */
public class PlayersTable extends JPanel {

    /**
     * Tabella con i giocatori.
     */
    private JTable table;

    /**
     * Modello della tabella.
     */
    private TableModelPlayers tableModel;

    /**
     * Costruttore che imposta la vera tabella e il suo modello.
     */
    public PlayersTable() {
        tableModel = new TableModelPlayers();

        table = new JTable(tableModel);
        table.setRowHeight(50);

        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    /**
     * Permette d'impostare i dati nella tabella.
     * @param players la lista di giocatori da inserire nella tabella
     */
    public void setData(List<JUnoPlayer> players) {
        tableModel.setData(players);
    }

    /**
     * Aggiorna la tabella in caso d'inserimento di dati.
     */
    public void refresh() {
        tableModel.fireTableDataChanged();
    }

    /**
     * Restituisce la lista con i nickname dei giocatori presenti nella tabella.
     * @return la lista dei nickname dei giocatori
     */
    public List<String> getPlayersNicknames() {
        return tableModel.getPlayersNicknames();
    }
}
