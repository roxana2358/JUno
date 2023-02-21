package view.interfaces;

import java.io.IOException;

/**
 * Interfaccia che permette di eseguire delle istruzioni personalizzate in base al button premuto.
 */
public interface ButtonListener {

    void listenButton(String instruction) throws IOException;
}
