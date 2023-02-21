package model;

import java.io.Serializable;

/**
 * Rappresenta i giocatori artificiali e umani del tavolo. Implementa Serializable per rendere possibile il
 * caricamento e il salvataggio dei dati.
 */
public abstract class Player implements Serializable {

    /**
     * Avatar del giocatore.
     */
    protected String avatar;

    /**
     * Nickname del giocatore.
     */
    protected String nickname;

    /**
     * Costruttore che assegna al giocatore il nickname e l'avatar.
     * @param nickname il nickname da assegnare
     * @param avatar l'avatar da assegnare
     */
    public Player(String nickname, String avatar) {
        this.nickname = nickname;
        this.avatar = avatar;
    }

    /**
     * Restituisce la fonte (nome file) dell'avatar del giocatore.
     * @return la fonte dell'avatar del giocatore
     */
    public String getAvatar() { return avatar; }

    /**
     * Restituisce il nickname del giocatore.
     * @return il nickname del giocatore
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Restituisce il giocatore sotto forma di stringa.
     * @return la stringa corrispondente al giocatore
     */
    @Override
    public String toString() {
        return nickname;
    }
}
