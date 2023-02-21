package view.panels;

import model.JUnoPlayer;
import view.interfaces.ButtonPusher;
import view.interfaces.PlayerAdder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Rappresenta il panel per l'aggiunta del profilo di un nuovo giocatore.
 */
public class AddNewPlayerPanel extends JPanel {

    /**
     * Etichetta del campo nickname.
     */
    private JLabel nickname;

    /**
     * Campo per aggiungere un nickname.
     */
    private JTextField addNickname;

    /**
     * Etichetta del campo avatar.
     */
    private JLabel avatar;

    /**
     * Gruppo di RadioButton per selezionare l'avatar.
     */
    private ButtonGroup selectAvatar;

    /**
     * Button INDIETRO.
     */
    private JButton backButton;

    /**
     * Button AGGIUNGI.
     */
    private JButton addButton;

    /**
     * Interfaccia relativa al bottone INDIETRO.
     */
    private ButtonPusher buttonPusher;

    /**
     * Interfaccia relativa all'aggiunta di un nuovo profilo.
     */
    private PlayerAdder playerAdder;

    /**
     * Costruttore del panel per l'aggiunta di un nuovo profilo.
     */
    public AddNewPlayerPanel() {
        Color backgroundColor = new Color(255, 57, 57, 255);
        setBackground(backgroundColor);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // go-back button
        backButton = new JButton("Torna indietro");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (buttonPusher != null) {
                   buttonPusher.pushButton();
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.FIRST_LINE_START;
        add(backButton, gbc);

        // add button
        addButton = new JButton("Aggiungi!");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (playerAdder != null) {
                    try {
                    String nickname = addNickname.getText();
                    String avatar = selectAvatar.getSelection().getActionCommand();
                    if (!nickname.isEmpty()) {
                        JUnoPlayer player = new JUnoPlayer(nickname, avatar);
                        playerAdder.addPlayer(player);
                    } else {
                        JOptionPane.showMessageDialog(AddNewPlayerPanel.this,
                                "Devi inserire un nickname se vuoi creare un profilo! Riprova.",
                                "Errore nickname", JOptionPane.WARNING_MESSAGE);
                    }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AddNewPlayerPanel.this,
                                "Non hai scelto un avatar e/o inserito un nome! Riprova.",
                                "Errore scelta avatar", JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0.01;
        gbc.weighty = 0.01;
        gbc.anchor = GridBagConstraints.FIRST_LINE_END;
        add(addButton, gbc);

        // nickname Label
        nickname = new JLabel("Nickname: ");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nickname, gbc);
        // nickname TextField
        addNickname = new JTextField(40);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(addNickname, gbc);

        // avatar Label
        avatar = new JLabel("Avatar: ");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar, gbc);

        // avatar1 radiobutton
        JRadioButton avatar1 = new JRadioButton();
        avatar1.setActionCommand("./src/graphics/img1.png");
        avatar1.setBackground(backgroundColor);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar1, gbc);
        // avatar1 image
        JLabel avatar1label = new JLabel(new ImageIcon("./src/graphics/img1.png"));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar1label, gbc);

        // avatar2 radiobutton
        JRadioButton avatar2 = new JRadioButton();
        avatar2.setActionCommand("./src/graphics/img2.png");
        avatar2.setBackground(backgroundColor);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar2, gbc);
        // avatar2 image
        JLabel avatar2label = new JLabel(new ImageIcon("./src/graphics/img2.png"));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar2label, gbc);

        // avatar3 radiobutton
        JRadioButton avatar3 = new JRadioButton();
        avatar3.setActionCommand("./src/graphics/img3.png");
        avatar3.setBackground(backgroundColor);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar3, gbc);
        // avatar3 image
        JLabel avatar3label = new JLabel(new ImageIcon("./src/graphics/img3.png"));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar3label, gbc);

        // avatar4 radiobutton
        JRadioButton avatar4 = new JRadioButton();
        avatar4.setActionCommand("./src/graphics/img4.png");
        avatar4.setBackground(backgroundColor);
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar4, gbc);
        // avatar4 image
        JLabel avatar4label = new JLabel(new ImageIcon("./src/graphics/img4.png"));
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar4label, gbc);

        // avatar5 radiobutton
        JRadioButton avatar5 = new JRadioButton();
        avatar5.setActionCommand("./src/graphics/img5.png");
        avatar5.setBackground(backgroundColor);
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar5, gbc);
        // avatar5 image
        JLabel avatar5label = new JLabel(new ImageIcon("./src/graphics/img5.png"));
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar5label, gbc);

        // avatar6 radiobutton
        JRadioButton avatar6 = new JRadioButton();
        avatar6.setActionCommand("./src/graphics/img6.png");
        avatar6.setBackground(backgroundColor);
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.LINE_START;
        add(avatar6, gbc);
        // avatar6 image
        JLabel avatar6label = new JLabel(new ImageIcon("./src/graphics/img6.png"));
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.weightx = 0.1;
        gbc.weighty = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avatar6label, gbc);

        // aggiunta dei radiobutton al buttongroup
        selectAvatar = new ButtonGroup();
        selectAvatar.add(avatar1);
        selectAvatar.add(avatar2);
        selectAvatar.add(avatar3);
        selectAvatar.add(avatar4);
        selectAvatar.add(avatar5);
        selectAvatar.add(avatar6);
    }

    /**
     * Imposta un ButtonPusher al panel.
     * @param buttonPusher interfaccia da assegnare
     */
    public void setButtonPusher(ButtonPusher buttonPusher) { this.buttonPusher = buttonPusher; }

    /**
     * Imposta un PlayerAdder al panel.
     * @param playerAdder interfaccia da assegnare
     */
    public void setPlayerAdder(PlayerAdder playerAdder) { this.playerAdder = playerAdder; }
}
