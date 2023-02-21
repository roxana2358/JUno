package view.frames;

import javax.swing.*;
import java.awt.*;

/**
 * Rappresenta lo schema base del frame di gioco di dimensione grande.
 */
public abstract class BigBasicFrame extends JFrame {

    /**
     * Costruttore del frame di dimensioni grandi.
     */
    public BigBasicFrame() {
        super("Uno Game developed with Java");
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
