package view.frames;

import controller.Controller;
import view.tables.PlayersTable;
import view.interfaces.ButtonListener;
import view.panels.JUnoMenuPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Rappresenta il frame per il menu di gioco.
 */
public class JUnoMenuFrame extends SmallBasicFrame {

    /**
     * Unico panel del frame.
     */
    private JUnoMenuPanel menuPanel;

    /**
     * Finestra aperta in risposta alla pressione di un button del panel, serve per gestire i giocatori umani.
     */
    private PlayersFrame newWindow;

    /**
     * Frame di gioco.
     */
    private JUnoGameFrame gameFrame;

    /**
     * Tabella con i giocatori.
     */
    private PlayersTable playersTable;

    /**
     * Istanza del controller.
     */
    private final Controller controller;

    /**
     * Costruttore del frame di gioco.
     * @throws IOException
     */
    public JUnoMenuFrame() throws IOException {
        super("Menu JUno Game");
        setLayout(new BorderLayout());

        controller = Controller.getInstance();
        controller.loadData(new File("./src/data.txt"));

        playersTable = new PlayersTable();

        menuPanel = new JUnoMenuPanel();
        menuPanel.setButtonListener(new ButtonListener() {
            @Override
            public void listenButton(String instruction) throws IOException {
                switch (instruction) {
                    case "new":
                        newWindow = new AddNewPlayerFrame(playersTable);
                        newWindow.setVisible(true);
                        break;
                    case "select":
                        newWindow = new SelectPlayerFrame(playersTable);
                        newWindow.setVisible(true);
                        break;
                    case "start":
                        if (controller.isCurrentPlayerSet() && controller.doesCurrentPlayerExist()) {
                            gameFrame = new JUnoGameFrame();
                            gameFrame.setVisible(true);
                            JOptionPane.showMessageDialog(gameFrame,
                                    "Il gioco sta per iniziare! Quando sei pronto clicca \"OK\"",
                                    "Gioca!", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(JUnoMenuFrame.this,
                                    "Non hai ancora selezionato il profilo con cui giocare!",
                                    "Errore giocatore", JOptionPane.WARNING_MESSAGE);
                        }
                        break;
                    case "exit":
                        controller.saveData(new File("./src/data.txt"));
                        dispose();
                        break;
                }
            }
        });

        add(menuPanel, BorderLayout.CENTER);
    }
}