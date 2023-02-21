package view.frames;

import controller.Controller;
import view.tables.PlayersTable;
import view.interfaces.ButtonPusher;
import view.interfaces.PlayerManager;
import view.panels.SelectPlayerPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta il frame per la selezione del profilo con cui giocare.
 */
public class SelectPlayerFrame extends PlayersFrame {

    /**
     * Unico panel del frame.
     */
    private SelectPlayerPanel selectPlayerPanel;

    /**
     * Istanza del controller.
     */
    private final Controller controller;

    /**
     * Costruttore del frame per la selezione del profilo di gioco.
     * @param playersTable la tabella dei giocatori
     */
    public SelectPlayerFrame(PlayersTable playersTable) {
        super("Scegli il profilo con cui giocare!", playersTable);
        setLayout(new BorderLayout());
        controller = Controller.getInstance();

        selectPlayerPanel = new SelectPlayerPanel(getPlayersTable(), controller.getPlayers());

        selectPlayerPanel.setButtonPusher(new ButtonPusher() {
            @Override
            public void pushButton() {
                dispose();
            }
        });

        selectPlayerPanel.setPlayerManager(new PlayerManager() {
            @Override
            public void selectPlayer(String name) {
                controller.setCurrentJunoPlayer(name);
                dispose();
            }

            @Override
            public void removePlayer(String name) {
                JLabel textMessage = new JLabel("Sei sicuro di voler eliminare il profilo?");
                textMessage.setFont(new Font("Arial", Font.BOLD, 35));
                if (JOptionPane.showConfirmDialog(SelectPlayerFrame.this, textMessage, "Eliminare il profilo?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    controller.removePlayer(name);
                    selectPlayerPanel.getComboBox().removeItem(name);
                    playersTable.refresh();
                }
            }
        });

        add(selectPlayerPanel, BorderLayout.CENTER);
    }
}