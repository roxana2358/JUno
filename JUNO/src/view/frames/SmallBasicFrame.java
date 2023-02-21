package view.frames;

import javax.swing.*;

/**
 * Rappresenta lo schema base del frame di gioco di dimensione piccola.
 */
public abstract class SmallBasicFrame extends JFrame {

    /**
     * Costruttore del frame di piccole dimensioni.
     * @param titolo il titolo da assegnare al frame
     */
    public SmallBasicFrame(String titolo) {
        super(titolo);
        setSize(580, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }

}
