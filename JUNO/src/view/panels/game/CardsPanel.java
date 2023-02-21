package view.panels.game;

import controller.Controller;
import model.JUnoCard;
import view.interfaces.CardListener;
import view.interfaces.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Rappresenta il panel con le carte del giocatore umano. Implementa ActionListener per gestire la scelta
 * della carta da giocare. Implementa Observer per poter essere notificato quando cambia il giocatore di turno.
 */
public class CardsPanel extends JPanel implements ActionListener, Observer {

    // 15 button per le 15 possibili carte da tenere in mano
    private JButton card1;
    private JButton card2;
    private JButton card3;
    private JButton card4;
    private JButton card5;
    private JButton card6;
    private JButton card7;
    private JButton card8;
    private JButton card9;
    private JButton card10;
    private JButton card11;
    private JButton card12;
    private JButton card13;
    private JButton card14;
    private JButton card15;

    /**
     * Lista dei button delle carte.
     */
    private List<JButton> cardsList;

    /**
     * Lista delle carte.
     */
    private List<JUnoCard> junoCardList;

    /**
     * Interfaccia relativa al comportamento dei button.
     */
    private CardListener cardListener;

    /**
     * Etichetta con il nome del giocatore.
     */
    private JLabel name;

    /**
     * Etichetta con l'avatar del giocatore.
     */
    private JLabel avatar;

    /**
     * Colore del tavolo da gioco.
     */
    private Color tableColor;

    /**
     * Costruttore del panel con le carte.
     * @param tableColor il colore del tavolo
     */
    public CardsPanel(Color tableColor) {
        this.tableColor = tableColor;
        setBackground(this.tableColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        card1 = new JButton();
        card2 = new JButton();
        card3 = new JButton();
        card4 = new JButton();
        card5 = new JButton();
        card6 = new JButton();
        card7 = new JButton();
        card8 = new JButton();
        card9 = new JButton();
        card10 = new JButton();
        card11 = new JButton();
        card12 = new JButton();
        card13 = new JButton();
        card14 = new JButton();
        card15 = new JButton();

        cardsList = new ArrayList<>();
        cardsList.add(card1);
        cardsList.add(card2);
        cardsList.add(card3);
        cardsList.add(card4);
        cardsList.add(card5);
        cardsList.add(card6);
        cardsList.add(card7);
        cardsList.add(card8);
        cardsList.add(card9);
        cardsList.add(card10);
        cardsList.add(card11);
        cardsList.add(card12);
        cardsList.add(card13);
        cardsList.add(card14);
        cardsList.add(card15);

        int i = 0;
        for (JButton card:cardsList) {
            card.addActionListener(this);
            card.setBorder(BorderFactory.createLineBorder(Color.white, 0));
            card.setBackground(tableColor);
            card.setPreferredSize(new Dimension(65,100));
            gbc.gridx = i++;
            gbc.gridy = 1;
            add(card, gbc);
        }

        name = new JLabel(Controller.getInstance().getCurrentJunoPlayer().getNickname());
        avatar = new JLabel(new ImageIcon(Controller.getInstance().getCurrentJunoPlayer().getAvatar()));
        gbc.gridy = 0;
        gbc.gridx = 1;
        add(name, gbc);
        gbc.gridx = 2;
        add(avatar,gbc);

        junoCardList = new ArrayList<>();
    }

    /**
     * Imposta le carte in mano al giocatore.
     * @param cards la lista delle carte del giocatore
     */
    public void setPlayersCards(List<JUnoCard> cards) {
        junoCardList = cards;
        refreshCardsView();
    }

    /**
     * Aggiorna le carte del giocatore a livello grafico.
     */
    private void refreshCardsView() {
        // se le carte sono piu' di 15, vengono visualizzate solo le prime 15
        int bound = junoCardList.size();
        if (bound >= 15) bound = 15;
        for (int i = 0; i < bound; i++)
        {
            cardsList.get(i).setIcon(new ImageIcon(junoCardList.get(i).getImage()
                    .getImage().getScaledInstance(65, 100, Image.SCALE_SMOOTH)));
        }
        // se le carte sono meno di 15, i rimanenti button non vengono visualizzati
        for (int i = bound; i < cardsList.size(); i++) {
            cardsList.get(i).setIcon(null);
        }
    }

    /**
     * Imposta un CardListener al panel.
     * @param cardListener interfaccia da assegnare
     */
    public void setCardListener(CardListener cardListener) { this.cardListener = cardListener; }

    /**
     * Stabilisce le istruzioni da eseguire in risposta alla pressione di uno dei button.
     * @param e l'evento in base al quale scegliere il comportamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushedButton = (JButton) e.getSource();
        if (cardListener != null) {
            try {
                if (pushedButton == card1) cardListener.listenCard(0);
                else if (pushedButton == card2) cardListener.listenCard(1);
                else if (pushedButton == card3) cardListener.listenCard(2);
                else if (pushedButton == card4) cardListener.listenCard(3);
                else if (pushedButton == card5 ) cardListener.listenCard(4);
                else if (pushedButton == card6) cardListener.listenCard(5);
                else if (pushedButton == card7) cardListener.listenCard(6);
                else if (pushedButton == card8) cardListener.listenCard(7);
                else if (pushedButton == card9) cardListener.listenCard(8);
                else if (pushedButton == card10) cardListener.listenCard(9);
                else if (pushedButton == card11) cardListener.listenCard(10);
                else if (pushedButton == card12) cardListener.listenCard(11);
                else if (pushedButton == card13) cardListener.listenCard(12);
                else if (pushedButton == card14) cardListener.listenCard(13);
                else if (pushedButton == card15) cardListener.listenCard(14);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Quando il numero fornito in input corrisponde alla posizione del giocatore nella lista dei giocatori del
     * tavolo significa che e' il suo turno e dev'essere impostato un bordo verde intorno al suo avatar.
     * @param i l'indice del giocatore di turno
     */
    @Override
    public void update(int i) {
        if (i == 3) {
            avatar.setBorder(BorderFactory.createLineBorder(Color.green, 6));
        } else {
            avatar.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        }
    }
}
