package view.frames;

import controller.Controller;
import model.JUnoPlayer;
import view.tables.PlayersTable;
import view.panels.AddNewPlayerPanel;
import view.interfaces.ButtonPusher;
import view.interfaces.PlayerAdder;

import java.awt.*;

/**
 * Rappresenta il frame per l'aggiunta del profilo di un nuovo giocatore.
 */
public class AddNewPlayerFrame extends PlayersFrame {

    /**
     * Unico panel del frame, contenente tutti i componenti.
     */
    private AddNewPlayerPanel newPlayerPanel;

    /**
     * Istanza del controller.
     */
    private final Controller controller;

    /**
     * Costruttore del frame per l'aggiunta dei profili.
     * @param playersTable la tabella dei giocatori
     */
    public AddNewPlayerFrame(PlayersTable playersTable) {
        super("Aggiungi un nuovo profilo!", playersTable);
        setLayout(new BorderLayout());
        controller = Controller.getInstance();

        newPlayerPanel = new AddNewPlayerPanel();

        newPlayerPanel.setButtonPusher(new ButtonPusher() {
            @Override
            public void pushButton() {
                dispose();
            }
        });

        newPlayerPanel.setPlayerAdder(new PlayerAdder() {
            @Override
            public void addPlayer(JUnoPlayer player) {
                controller.addPlayer(player);
                getPlayersTable().refresh();
                dispose();
            }
        });

        add(newPlayerPanel, BorderLayout.CENTER);
    }
}