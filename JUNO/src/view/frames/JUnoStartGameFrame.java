package view.frames;

import view.interfaces.MenuOpener;
import view.panels.*;

import java.awt.*;
import java.io.IOException;

/**
 * Rappresenta il frame di apertura del gioco.
 */
public class JUnoStartGameFrame extends BigBasicFrame {

    /**
     * Unico panel del frame.
     */
    private JUnoStartGamePanel startPanel;

    /**
     * Costruttore del frame di apertura del gioco.
     */
    public JUnoStartGameFrame() {
        super();
        setLayout(new BorderLayout());

        startPanel = new JUnoStartGamePanel();
        startPanel.setMenuOpener(new MenuOpener() {
            @Override
            public void openMenu() throws IOException {
                dispose();
                JUnoMenuFrame menuFrame = new JUnoMenuFrame();
                menuFrame.setVisible(true);
            }
        });
        add(startPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}