package view.panels.game;

import view.interfaces.Observer;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta il panel dei giocatori artificiali. Implementa Observer per poter essere notificato quando
 * cambia il giocatore di turno ma lascia la loro effettiva implementazione alle classi concrete.
 */
public abstract class PlayerPanel extends JPanel implements Observer {

    /**
     * Immagine retro carta da gioco.
     */
    protected ImageIcon cardImage;

    // 3 carte da visualizzare per indicare che il giocatore ha piu' di una carta in mano
    protected JLabel card1;
    protected JLabel card2;
    protected JLabel card3;

    /**
     * Etichetta con il nome del giocatore.
     */
    protected JLabel name;

    /**
     * Etichetta con l'avatar del giocatore.
     */
    protected JLabel avatar;

    /**
     * Colore del tavolo da gioco.
     */
    private Color tableColor;

    /**
     * Costruttore del panel del giocatore artificiale.
     * @param name il nome del giocatore
     * @param avatar l'avatar del giocatore
     * @param tableColor il colore del tavolo
     */
    public PlayerPanel(String name, String avatar, Color tableColor) {
        this.tableColor = tableColor;
        setBackground(this.tableColor);
        setLayout(new GridBagLayout());

        this.name = new JLabel(name);
        this.avatar = new JLabel(new ImageIcon(avatar));
    }

    /**
     * Mostra a livello grafico che il giocatore ha una sola carta.
     */
    public void setUno() {
        remove(card1);
        remove(card3);
    }

    /**
     * Mostra a livello grafico che il giocatore ha piu' di una carta.
     */
    public abstract void unSetUno();
}
