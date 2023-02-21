package view.interfaces;

import model.JUnoPlayer;

/**
 * Interfaccia che permette di aggiungere un nuovo profilo al database di gioco.
 */
public interface PlayerAdder {
    void addPlayer(JUnoPlayer player);
}
