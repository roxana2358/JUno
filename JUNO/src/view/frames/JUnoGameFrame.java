package view.frames;

import controller.Controller;
import model.JUnoCard;
import view.interfaces.ButtonListener;
import view.interfaces.CardListener;
import view.panels.JUnoGamePanel;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta il frame di gioco.
 */
public class JUnoGameFrame extends BigBasicFrame {

    /**
     * Istanza del controller.
     */
    private final Controller controller;

    /**
     * Panel di gioco.
     */
    private JUnoGamePanel gamePanel;

    /**
     * Costruttore del frame di gioco.
     */
    public JUnoGameFrame() {
        controller = Controller.getInstance();
        gamePanel = new JUnoGamePanel();
        add(gamePanel, BorderLayout.CENTER);
        controller.setGameFrame(this);
    }

    /**
     * Imposta a livello grafico il giocatore di turno (imposta un cerchio verde intorno all'avatar del giocatore).
     * @param i l'indice del panel del giocatore di turno
     */
    public void setPlayersTurn(int i) {
        gamePanel.notifyObservers(i);
    }

    /**
     * Imposta a livello grafico le carte del giocatore umano.
     * @param cards la lista delle carte in mano al giocatore umano
     */
    public void setJunoPlayersCards(java.util.List<JUnoCard> cards) {
        gamePanel.setJunoPlayersCards(cards);
    }

    /**
     * Imposta a livello grafico la carta in cima al mazzo di scarto.
     * @param card la carta in cima al mazzo di scarto
     */
    public void setDiscardDeckTopCard(JUnoCard card) {
        gamePanel.setDiscardDeckTopCard(card);
    }

    /**
     * Imposta un CardListener al panel di gioco (per il panel del giocatore umano).
     * @param cardListener interfaccia da assegnare
     */
    public void setCardsButtonListener(CardListener cardListener) {
        gamePanel.setCardsListener(cardListener);
    }

    /**
     * Imposta un ButtonListener al panel di gioco (per il panel centrale).
     * @param buttonListener interfaccia da assegnare
     */
    public void setCenterButtonListener(ButtonListener buttonListener) {
        gamePanel.setCenterButtonListener(buttonListener);
    }

    /**
     * Restituisce il panel richiesto in base all'indice fornito in input.
     * @param i l'indice del panel richiesto
     * @return il panel richiesto
     */
    public JPanel getPanel(int i) {
        return switch (i) {
            case 0 -> gamePanel.getOpponentN();
            case 1 -> gamePanel.getOpponentO();
            case 2 -> gamePanel.getCenterPanel();
            case 3 -> gamePanel.getOpponentE();
            case 4 -> gamePanel.getCardsPanel();
            default -> null;
        };
    }
}