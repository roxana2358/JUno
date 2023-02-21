package view.panels.game;

import model.JUnoCard;
import view.interfaces.ButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il panel al centro del tavolo, con il mazzo di scarto, il mazzo di pesca, il button UNO e il
 * button CONTINUA. Implementa ActionListener per gestire la pressione dei button.
 */
public class CenterPanel extends JPanel implements ActionListener {

    /**
     * Etichetta con la carta in cima al mazzo di scarto.
     */
    private JLabel topCard;

    /**
     * Carta in cima al mazzo di scarto.
     */
    private JUnoCard junoTopCard;

    /**
     * Button UNO.
     */
    private JButton unoButton;

    /**
     * Button CONTINUA.
     */
    private JButton continueButton;

    /**
     * Button PESCA-CARTA.
     */
    private JButton drawCardbutton;

    /**
     * Colore del tavolo da gioco.
     */
    private Color tableColor;

    /**
     * Interfaccia relativa al comportamento dei button.
     */
    private ButtonListener buttonListener;

    /**
     * Costruttore del panel al centro del tavolo.
     * @param tableColor il colore del tavolo
     */
    public CenterPanel(Color tableColor) {
        this.tableColor = tableColor;
        setBackground(this.tableColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        drawCardbutton = new JButton();
        drawCardbutton.addActionListener(this);
        drawCardbutton.setIcon(new ImageIcon(new ImageIcon("./src/graphics/back-card.png")
                        .getImage().getScaledInstance(105,170, Image.SCALE_SMOOTH)));
        drawCardbutton.setBorder(BorderFactory.createLineBorder(Color.white, 0));
        drawCardbutton.setBackground(tableColor);
        gbc.gridx = 0;
        add(drawCardbutton, gbc);

        topCard = new JLabel();
        gbc.gridx = 1;
        add(topCard, gbc);

        unoButton = new JButton("UNO!");
        unoButton.addActionListener(this);
        unoButton.setFont(new Font("Dialog", Font.BOLD, 30));
        unoButton.setBackground(Color.red);
        unoButton.setBorder(BorderFactory.createLineBorder(Color.red, 1));
        unoButton.setPreferredSize(new Dimension(100,100));
        gbc.gridx = 2;
        add(unoButton, gbc);

        continueButton = new JButton("CONTINUA");
        continueButton.addActionListener(this);
        continueButton.setFont(new Font("Dialog", Font.PLAIN, 15));
        continueButton.setBackground(tableColor);
        continueButton.setBorder(BorderFactory.createLineBorder(Color.black, 0));
        continueButton.setPreferredSize(new Dimension(100,30));
        gbc.gridy = 1;
        gbc.gridx = 1;
        add(continueButton, gbc);
    }

    /**
     * Imposta la carta in cima al mazzo di scarto.
     * @param card la carta in cima al mazzo di scarto
     */
    public void setDiscardDeckTopCard(JUnoCard card) {
        junoTopCard = card;
        refreshTopCardView();
    }

    /**
     * Aggiorna la carta in cima al mazzo di scarto a livello grafico
     */
    private void refreshTopCardView() {
        topCard.setIcon(new ImageIcon(junoTopCard.getImage()
                .getImage().getScaledInstance(105,170, Image.SCALE_SMOOTH)));
    }

    /**
     * Imposta un ButtonListener al panel.
     * @param buttonListener interfaccia da assegnare
     */
    public void setButtonListener(ButtonListener buttonListener) { this.buttonListener = buttonListener; }

    /**
     * Stabilisce le istruzioni da eseguire in risposta alla pressione di uno dei button.
     * @param e l'evento in base al quale scegliere il comportamento
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        JButton pushedButton = (JButton) e.getSource();
        if (buttonListener != null) {
            try {
                if (pushedButton == unoButton) buttonListener.listenButton("uno");
                else if (pushedButton == drawCardbutton) buttonListener.listenButton("draw");
                else if (pushedButton == continueButton) buttonListener.listenButton("next");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
