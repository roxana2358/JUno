package view.panels;

import model.JUnoPlayer;
import view.tables.PlayersTable;
import view.interfaces.ButtonPusher;
import view.interfaces.PlayerManager;

import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il panel per la selezione di un profilo preesistente. Implementa ActionListener per la gestione
 * dei profili.
 */
public class SelectPlayerPanel extends JPanel implements ActionListener {

    /**
     * Button INDIETRO.
     */
    private JButton backButton;

    /**
     * Menu a tendina per la selezione del profilo.
     */
    private JComboBox comboBox;

    /**
     * Button SELEZIONA.
     */
    private JButton selectPlayerButton;

    /**
     * Button RIMUOVI.
     */
    private JButton removePlayerButton;

    /**
     * Tabella con i giocatori.
     */
    private PlayersTable playersTable;

    /**
     * Interfaccia relativa alla pressione del button INDIETRO.
     */
    private ButtonPusher buttonPusher;

    /**
     * Interfaccia relativa alla gestione dei profili.
     */
    private PlayerManager playerManager;

    /**
     * Costruttore del panel per la selezione del profilo con cui giocare.
     * @param playersTable la tabella con i giocatori
     * @param players la lista dei giocatori
     */
    public SelectPlayerPanel(PlayersTable playersTable, List<JUnoPlayer> players) {
        Color backGroundColor = new Color(255, 57, 57, 255);
        setBackground(backGroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // back button
        backButton = new JButton("Torna indietro");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonPusher != null) {
                    buttonPusher.pushButton();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(backButton, gbc);

        // players table
        this.playersTable = playersTable;
        this.playersTable.setData(players);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 2;
        gbc.weighty = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(new JScrollPane(this.playersTable), gbc);

        // select player combo box
        Dimension d = new Dimension(110, 25);
        comboBox = new JComboBox(playersTable.getPlayersNicknames().toArray());
        comboBox.setEditable(false);
        comboBox.setPreferredSize(d);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(comboBox, gbc);

        // select player button
        selectPlayerButton = new JButton("Seleziona!");
        selectPlayerButton.addActionListener(this);
        selectPlayerButton.setPreferredSize(d);
        gbc.gridx = 2;
        gbc.gridy = 2;
        add(selectPlayerButton, gbc);

        // remove player button
        removePlayerButton = new JButton("Rimuovi!");
        removePlayerButton.addActionListener(this);
        removePlayerButton.setPreferredSize(d);
        gbc.gridx = 2;
        gbc.gridy = 3;
        add(removePlayerButton, gbc);

    }

    /**
     * Imposta un ButtonPusher al panel.
     * @param buttonPusher interfaccia da assegnare
     */
    public void setButtonPusher(ButtonPusher buttonPusher) {
        this.buttonPusher = buttonPusher; }

    /**
     * Imposta un PlayerManager al panel.
     * @param playerManager interfaccia da assegnare
     */
    public void setPlayerManager(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    /**
     * Restituisce il menu a tendina per la selezione del profilo.
     * @return il menu a tendina con i nickname dei giocatori
     */
    public JComboBox getComboBox() { return comboBox; }

    /**
     * Stabilisce le istruzioni da eseguire in risposta alla pressione di uno dei button.
     * @param e l'evento in base al quale scegliere il comportamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushedButton = (JButton) e.getSource();
        if (playerManager != null) {
            if (pushedButton == selectPlayerButton) {
                playerManager.selectPlayer((String) comboBox.getSelectedItem());
            } else if (pushedButton == removePlayerButton) {
                playerManager.removePlayer((String) comboBox.getSelectedItem());
            }
        }
    }
}
