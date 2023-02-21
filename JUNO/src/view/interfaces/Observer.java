package view.interfaces;

/**
 * Interfaccia Observer del pattern Observer. Ha un unico metodo che permette di aggiornare i dati della classe
 * che la implementa quando viene ricevuta la notifica.
 */
public interface Observer {
    void update(int i);
}
