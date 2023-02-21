package view.panels.game;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta il panel del giocatore artificiale nella posizione piu' a destra.
 */
public class OPlayerPanel extends PlayerPanel {

    /**
     * Costruttore del panel del giocatore.
     * @param color il colore del tavolo
     */
    public OPlayerPanel(Color color) {
        super("Bowser", "./src/graphics/op1.png", color);
        GridBagConstraints gbc = new GridBagConstraints();

        cardImage = new ImageIcon(new ImageIcon("./src/graphics/back-card-o.png")
                .getImage().getScaledInstance(100, 65, Image.SCALE_SMOOTH));

        card1 = new JLabel(cardImage);
        card1.setBackground(color);
        card2 = new JLabel(cardImage);
        card2.setBackground(color);
        card3 = new JLabel(cardImage);
        card3.setBackground(color);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(card1, gbc);
        gbc.gridy = 1;
        add(card2, gbc);
        gbc.gridy = 2;
        add(card3, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(name, gbc);
        gbc.gridy = 1;
        add(avatar,gbc);
    }

    /**
     * Mostra a livello grafico che il giocatore ha piu' di una carta.
     */
    @Override
    public void unSetUno() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(card1, gbc);
        gbc.gridy = 2;
        add(card3, gbc);
    }

    /**
     * Quando il numero fornito in input corrisponde alla posizione del giocatore nella lista dei giocatori del
     * tavolo significa che e' il suo turno e dev'essere impostato un bordo verde intorno al suo avatar.
     * @param i l'indice del giocatore di turno
     */
    @Override
    public void update(int i) {
        if (i == 0) {
            avatar.setBorder(BorderFactory.createLineBorder(Color.green, 6));
        } else {
            avatar.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        }
    }
}
