package view.panels;

import model.JUnoCard;
import view.interfaces.ButtonListener;
import view.interfaces.CardListener;
import view.interfaces.Observable;
import view.interfaces.Observer;
import view.panels.game.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta il panel di gioco.
 */
public class JUnoGamePanel extends JPanel implements Observable {

    /**
     * Panel con le carte del giocatore umano.
     */
    private CardsPanel cardsPanel;

    /**
     * Panel con i componenti al centro del tavolo: mazzo di scarto, mazzo di pesca, button UNO, button CONTINUA.
     */
    private CenterPanel centerPanel;

    /**
     * Panel del giocatore piu' a sinistra.
     */
    private OPlayerPanel opponentO;

    /**
     * Panel del giocatore piu' vicino al bordo superiore.
     */
    private NPlayerPanel opponentN;

    /**
     * Panel del giocatore piu' a destra.
     */
    private EPlayerPanel opponentE;

    /**
     * Lista di Observer.
     */
    private List<Observer> observers;

    /**
     * Costruttore del panel di gioco.
     */
    public JUnoGamePanel() {
        Color backgroundColor = new Color(38, 176, 40);
        setBackground(backgroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        observers = new ArrayList<>();

        // giocatore artificiale 1
        opponentN = new NPlayerPanel(backgroundColor);
        addObserver(opponentN);
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.gridheight = 2;
        add(opponentN, gbc);

        // giocatore artificiale 2
        opponentO = new OPlayerPanel(backgroundColor);
        addObserver(opponentO);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        add(opponentO, gbc);

        // centro del tavolo
        centerPanel = new CenterPanel(backgroundColor);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(centerPanel, gbc);

        // giocatore artificiale 3
        opponentE = new EPlayerPanel(backgroundColor);
        addObserver(opponentE);
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(opponentE, gbc);

        // giocatore umano
        cardsPanel = new CardsPanel(backgroundColor);
        addObserver(cardsPanel);
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.gridheight = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(cardsPanel, gbc);
    }

    /**
     * Restituisce il panel con le carte del giocatore umano.
     * @return il panel riservato al giocatore umano
     */
    public CardsPanel getCardsPanel() {
        return cardsPanel;
    }

    /**
     * Restituisce il panel con le componenti al centro del tavolo.
     * @return il panel riservato al centro del tavolo
     */
    public CenterPanel getCenterPanel() {
        return centerPanel;
    }

    /**
     * Restituisce il panel del giocatore piu' a sinistra.
     * @return il panel riservato al giocatore piu' a sinistra
     */
    public OPlayerPanel getOpponentO() {
        return opponentO;
    }

    /**
     * Restituisce il panel del giocatore piu' vicino al bordo superiore.
     * @return il panel riservato al giocatore piu' vicino al bordo superiore
     */
    public NPlayerPanel getOpponentN() {
        return opponentN;
    }

    /**
     * Restituisce il panel del giocatore piu' a destra.
     * @return il panel riservato al giocatore piu' a destra
     */
    public EPlayerPanel getOpponentE() {
        return opponentE;
    }

    /**
     * Inoltra al panel con le carte del giocatore umano la richiesta d'impostare le carte in mano al giocatore.
     * @param cards la lista delle carte in mano al giocatore
     */
    public void setJunoPlayersCards(List<JUnoCard> cards) {
        cardsPanel.setPlayersCards(cards);
    }

    /**
     * Inoltra al panel centrale la richiesta d'impostare la carta in cima al mazzo di scarto.
     * @param card la carta in cima al mazzo di scarto
     */
    public void setDiscardDeckTopCard(JUnoCard card) {
        centerPanel.setDiscardDeckTopCard(card);
    }

    /**
     * Imposta un CardListener al panel con le carte del giocatore umano.
     * @param cardListener interfaccia da assegnare
     */
    public void setCardsListener(CardListener cardListener) {
        cardsPanel.setCardListener(cardListener);
    }

    /**
     * Imposta un ButtonListener al panel centrale.
     * @param buttonListener interfaccia da assegnare
     */
    public void setCenterButtonListener(ButtonListener buttonListener) {
        centerPanel.setButtonListener(buttonListener);
    }

    /**
     * Notifica gli Observers quando cambia il giocatore di turno.
     * @param i l'indice del nuovo giocatore di turno
     */
    @Override
    public void notifyObservers(int i) {
        for (Observer o:observers) o.update(i);
    }

    /**
     * Permette di aggiungere un Observer alla lista.
     * @param o l'Observer da aggiungere
     */
    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    /**
     * Permette di rimuovere un Observer dalla lista.
     * @param o l'Observer da rimuovere
     */
    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }
}
