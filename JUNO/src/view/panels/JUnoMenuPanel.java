package view.panels;

import view.interfaces.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il panel del menu di gioco. Implementa ActionListener per gestire le opzioni del menu.
 */
public class JUnoMenuPanel extends JPanel implements ActionListener {

    /**
     * Button per l'aggiunta di un nuovo profilo.
     */
    private JButton newPlayerButton;

    /**
     * Button per la selezione di un profilo preesistente.
     */
    private JButton selectPlayerButton;

    /**
     * Button per l'inizio della partita.
     */
    private JButton startGameButton;

    /**
     * Button per salvataggio dei dati e uscita dal gioco.
     */
    private JButton exitButton;

    /**
     * Interfaccia relativa alla pressione dei button.
     */
    private ButtonListener buttonListener;

    /**
     * Costruttore del panel.
     */
    public JUnoMenuPanel() {
        Color backgroundColor = new Color(76, 147, 255, 255);
        Color buttonColor = new Color(255, 234, 124);
        Font panelFont = new Font(Font.DIALOG, Font.BOLD, 25);
        setBackground(backgroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // new player button
        Dimension d = new Dimension(430, 45);
        newPlayerButton = new JButton("INSERISCI NUOVO GIOCATORE");
        newPlayerButton.addActionListener(this);
        newPlayerButton.setFont(panelFont);
        newPlayerButton.setBackground(buttonColor);
        newPlayerButton.setPreferredSize(d);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(newPlayerButton,gbc);

        // select player button
        selectPlayerButton = new JButton("SELEZIONA GIOCATORE");
        selectPlayerButton.addActionListener(this);
        selectPlayerButton.setFont(panelFont);
        selectPlayerButton.setBackground(buttonColor);
        selectPlayerButton.setPreferredSize(d);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(selectPlayerButton,gbc);

        // play button
        startGameButton = new JButton("GIOCA");
        startGameButton.addActionListener(this);
        startGameButton.setFont(panelFont);
        startGameButton.setBackground(buttonColor);
        startGameButton.setPreferredSize(d);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(startGameButton, gbc);

        // save and exit button
        exitButton = new JButton("SALVA ED ESCI");
        exitButton.addActionListener(this);
        exitButton.setFont(panelFont);
        exitButton.setBackground(buttonColor);
        exitButton.setPreferredSize(d);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.5;
        add(exitButton,gbc);
    }

    /**
     * Imposta un ButtonListener al panel.
     * @param buttonListener interfaccia da assegnare
     */
    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    /**
     * Stabilisce le istruzioni da eseguire in risposta alla pressione di uno dei button.
     * @param e l'evento in base al quale scegliere il comportamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushed = (JButton) e.getSource();
        if (buttonListener != null) {
            try {
                if (pushed == newPlayerButton) {
                    buttonListener.listenButton("new");
                } else if (pushed == selectPlayerButton) {
                    buttonListener.listenButton("select");
                } else if (pushed == startGameButton) {
                    buttonListener.listenButton("start");
                } else if (pushed == exitButton) {
                    buttonListener.listenButton("exit");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(JUnoMenuPanel.this,
                        "ERRORE GIOCO! BISOGNA CONTROLLARE IL CODICE DEL MENU!",
                        "ERRORE", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
