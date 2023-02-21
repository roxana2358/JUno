package view.interfaces;

/**
 * Interfaccia Observable del pattern Observer. Ha un metodo che permette di notificare tutti gli Observer
 * al verificarsi di un certo evento, un metodo per aggiungere Observer e uno per rimuoverli.
 */
public interface Observable {
    void notifyObservers(int i);
    void addObserver(Observer o);
    void removeObserver(Observer o);
}
