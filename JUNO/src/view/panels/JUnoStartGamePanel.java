package view.panels;

import view.interfaces.MenuOpener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il panel di apertura del gioco.
 */
public class JUnoStartGamePanel extends JPanel {

    /**
     * Fonte della copertina del gioco.
     */
    private String image;

    /**
     * Etichetta con la copertina del gioco
     */
    private JLabel backgroundLabel;

    /**
     * Button d'inizio gioco.
     */
    private JButton startGameButton;

    /**
     * Interfaccia relativa all'apertura del menu.
     */
    private MenuOpener menuOpener;

    /**
     * Costruttore del panel.
     */
    public JUnoStartGamePanel() {
        image = "./src/graphics/uno-back1.png";
        setLayout(new BorderLayout());

        // start game button
        startGameButton = new JButton("INIZIA A GIOCARE!");
        startGameButton.setBackground(new Color(255, 255, 0));
        startGameButton.setFont(new Font(Font.DIALOG, Font.BOLD, 25));
        startGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (menuOpener != null) {
                    try {
                        menuOpener.openMenu();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(JUnoStartGamePanel.this,
                                "ERRORE GIOCO! BISOGNA CONTROLLARE IL CODICE DELLA SCHERMATA INIZIALE DI GIOCO!",
                                "ERRORE", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        add(startGameButton, BorderLayout.PAGE_START);

        // game image
        backgroundLabel = new JLabel(new ImageIcon(image));
        add(backgroundLabel, BorderLayout.CENTER);
    }

    /**
     * Imposta un MenuOpener al panel.
     * @param menuOpener interfaccia da assegnare
     */
    public void setMenuOpener(MenuOpener menuOpener) {
        this.menuOpener = menuOpener;
    }
}