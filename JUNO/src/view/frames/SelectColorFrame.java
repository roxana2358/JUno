package view.frames;

import view.interfaces.ColorListener;
import model.Color;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il frame che permette al giocatore umano di scegliere il nuovo colore quando viene giocata
 * una carta jolly. Implementa ActionListener per gestire la scelta del colore.
 */
public class SelectColorFrame extends JFrame implements ActionListener {

    /**
     * Unico panel del frame.
     */
    private JPanel colorPanel;

    /**
     * Button blu.
     */
    private final JButton blue;

    /**
     * Button verde.
     */
    private final JButton green;

    /**
     * Button rosso.
     */
    private final JButton red;

    /**
     * Button giallo.
     */
    private final JButton yellow;

    /**
     * Interfaccia relativa al comportamento dei button.
     */
    private ColorListener colorListener;

    /**
     * Costruttore del frame di selezione del colore.
     */
    public SelectColorFrame() {
        super("Scegli il nuovo colore di gioco");
        setSize(450, 300);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        colorPanel = new JPanel();
        colorPanel.setBackground(new java.awt.Color(0, 165, 81));
        colorPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        Dimension d = new Dimension(100,100);

        blue = new JButton();
        blue.setBackground(java.awt.Color.blue);
        blue.addActionListener(this);
        blue.setPreferredSize(d);

        green = new JButton();
        green.setBackground(java.awt.Color.green);
        green.addActionListener(this);
        green.setPreferredSize(d);

        red = new JButton();
        red.setBackground(java.awt.Color.red);
        red.addActionListener(this);
        red.setPreferredSize(d);

        yellow = new JButton();
        yellow.setBackground(java.awt.Color.yellow);
        yellow.addActionListener(this);
        yellow.setPreferredSize(d);

        gbc.gridx = 0;
        colorPanel.add(blue, gbc);
        gbc.gridx = 1;
        colorPanel.add(green, gbc);
        gbc.gridx = 2;
        colorPanel.add(red, gbc);
        gbc.gridx = 3;
        colorPanel.add(yellow, gbc);

        add(colorPanel);

        setVisible(true);
    }

    /**
     * Imposta un ColorListener al panel.
     * @param colorListener interfaccia da assegnare
     */
    public void setColorListener(ColorListener colorListener) { this.colorListener = colorListener; }

    /**
     * Stabilisce le istruzioni da eseguire in risposta alla pressione di uno dei button.
     * @param e l'evento in base al quale scegliere il comportamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushedButton = (JButton) e.getSource();
        if (colorListener != null) {
            if (pushedButton == blue) colorListener.listenColor(Color.BLU);
            else if (pushedButton == green) colorListener.listenColor(Color.VERDE);
            else if (pushedButton == red) colorListener.listenColor(Color.ROSSO);
            else if (pushedButton == yellow) colorListener.listenColor(Color.GIALLO);
        }
    }
}
