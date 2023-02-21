package controller;

import model.*;
import model.Color;
import view.frames.JUnoGameFrame;
import view.frames.SelectColorFrame;
import view.interfaces.ButtonListener;
import view.interfaces.CardListener;
import view.interfaces.ColorListener;
import view.panels.game.EPlayerPanel;
import view.panels.game.NPlayerPanel;
import view.panels.game.OPlayerPanel;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Rappresenta il controller che fa da intermediario fra model e view nel pattern MVC. Il controller
 * adotta il design pattern Singleton per averne un'unica istanza in tutto il codice del progetto.
 */
public class Controller {

    /**
     * Unica istanza del controller (Singleton pattern).
     */
    private static Controller instance;

    /**
     * Database dei giocatori.
     */
    private final PlayersDatabase pdb;

    /**
     * Tavolo da gioco.
     */
    private JUnoTable gameTable;

    /**
     * Frame di gioco.
     */
    private JUnoGameFrame gameFrame;

    /**
     * Il giocatore selezionato per la partita.
     */
    private JUnoPlayer currentJunoPlayer;

    /**
     * Indica se il giocatore per la partita e' stato selezionato.
     */
    private boolean isCurrentPlayerSet;

    /**
     * Costruttore privato (Singleton pattern) che imposta il database.
     */
    private Controller() {
        pdb = PlayersDatabase.getInstance();
        isCurrentPlayerSet = false;
    }

    /**
     * Unico metodo per ottenere un'istanza del controller (Singleton pattern).
     * @return istanza del controller
     */
    public static Controller getInstance() {
        if (instance == null) instance = new Controller();
        return instance;
    }

    /**
     * Salva i dati dei giocatori nel database.
     * @param file il file con i dati
     * @throws IOException
     */
    public void saveData(File file) throws IOException {
        pdb.savePlayersData(file);
    }

    /**
     * Preleva i dati dei giocatori dal database.
     * @param file il file con i dati
     * @throws IOException
     */
    public void loadData(File file) throws IOException {
        pdb.loadPlayersData(file);
    }

    /**
     * Aggiunge il profilo di un nuovo giocatore al database.
     * @param player il giocatore da aggiungere al database
     */
    public void addPlayer(JUnoPlayer player) {
        pdb.addPlayer(player);
    }

    /**
     * Rimuove il profilo di un giocatore inserito precedentemente nel database.
     * @param name il nome del profilo da rimuovere
     */
    public void removePlayer(String name) {
        pdb.removePlayer(name);
    }

    /**
     * Restituisce la lista dei giocatori presenti nel database.
     * @return la lista dei giocatori
     */
    public List<JUnoPlayer> getPlayers() { return pdb.getPlayers(); }

    /**
     * Restituisce il giocatore selezionato per la partita.
     * @return il giocatore selezionato per la partita
     */
    public JUnoPlayer getCurrentJunoPlayer() { return currentJunoPlayer; }

    /**
     * Imposta il giocatore selezionato per la partita, recuperandolo dal database in base al nome.
     * @param name il nome del giocatore
     */
    public void setCurrentJunoPlayer(String name) {
        isCurrentPlayerSet = true;
        currentJunoPlayer = pdb.getPlayerByName(name);
    }

    /**
     * Imposta il frame di gioco, il comportamento dei button del frame di gioco e richiama il metodo che
     * imposta il tavolo da gioco.
     * @param gameFrame il frame da gioco
     */
    public void setGameFrame(JUnoGameFrame gameFrame) {
        this.gameFrame = gameFrame;

        // il comportamento da adottare quando si vogliono usare le carte in mano
        gameFrame.setCardsButtonListener(new CardListener() {
            @Override
            public void listenCard(int position) {
                if (gameTable.isCurrentJunoPlayersTurn() && gameTable.isCurrentJunoPlayerAllowedToUseAnyCard()) {
                    if (position >= gameTable.getCurrentJunoPlayersHand().size()) return;
                    JUnoCard card = gameTable.getCurrentJunoPlayersHand().get(position);
                    if (gameTable.isCardValid(card)) {
                        if (card.getColor().equals(Color.JOLLY)) {
                            SelectColorFrame scf = new SelectColorFrame();
                            scf.setColorListener(new ColorListener() {
                                @Override
                                public void listenColor(Color color) {
                                    gameTable.submitCard(card, color);
                                    scf.dispose();
                                    junoPlayersTurn();
                                }
                            });
                        }
                        else {
                            gameTable.submitCard(card, null);
                            junoPlayersTurn();
                        }
                    } else {
                        displayMessage("La carta selezionata non va bene! (colore richiesto "
                                +gameTable.getValidColor()+")", 30);
                    }
                }
            }
        });

        // il comportamento da adottare quando viene premuto il button "uno" oppure si vuole pescare una carta
        gameFrame.setCenterButtonListener(new ButtonListener() {
            @Override
            public void listenButton(String instruction) {
                if (instruction.equals("uno")) {
                    if (gameTable.getCurrentJunoPlayersHand().size() == 1) {
                        gameTable.callUno();
                        displayMessage("UNO!", 50);
                    } else {
                        displayMessage("Hai piu' di una carta!", 48);
                    }
                } else if (instruction.equals("draw") && gameTable.isCurrentJunoPlayersTurn() && gameTable.isCurrentJunoPlayerAllowedToUseAnyCard()) {
                    if (gameTable.getPlayersTurnHand().size() >= 15) {
                        displayMessage("Non hai piu' spazio per le carte, salti il turno.", 48);
                        gameTable.nextPlayer();
                        updateGameFrame();
                    } else {
                        gameTable.drawCard();
                        JUnoCard drawnCard = gameTable.getCurrentJunoPlayersHand().get(gameTable.getCurrentJunoPlayersHandSize()-1);
                        if (gameTable.isCardValid(drawnCard)) {
                            Value v = drawnCard.getValue();
                            Color c = drawnCard.getColor();
                            String text = "La carta pescata e'";
                            if (!v.equals(Value.JOLLY)) text += " "+v;
                            text += " "+c+". Vuoi usarla?";
                            JLabel textMessage = new JLabel(text);
                            textMessage.setFont(new Font("Arial", Font.BOLD, 30));
                            if (JOptionPane.showConfirmDialog(gameFrame, textMessage, "Usare la carta pescata?",
                                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                                if (drawnCard.getColor().equals(Color.JOLLY)) {
                                    SelectColorFrame scf = new SelectColorFrame();
                                    scf.setColorListener(new ColorListener() {
                                        @Override
                                        public void listenColor(Color color) {
                                            gameTable.submitCard(drawnCard, color);
                                            scf.dispose();
                                            junoPlayersTurn();
                                        }
                                    });
                                } else {
                                    gameTable.submitCard(drawnCard, null);
                                    junoPlayersTurn();
                                }
                            } else {
                                gameTable.nextPlayer();
                                updateGameFrame();
                            }
                        } else {
                            gameTable.nextPlayer();
                            updateGameFrame();
                        }
                    }
                } else if (instruction.equals("next")) {
                    nextMove();
                }
            }
        });

        setGameTable();
    }

    /**
     * Imposta il tavolo da gioco.
     */
    public void setGameTable() {
        gameTable = new JUnoTable(currentJunoPlayer);
        updateGameFrame();
    }

    /**
     * Mostra a video un messaggio con informazioni.
     * @param message il messaggio da mostrare
     * @param size la grandezza del testo
     */
    public void displayMessage(String message, int size) {
        JLabel textMessage = new JLabel(message);
        textMessage.setFont(new Font("Arial", Font.BOLD, size));
        JOptionPane.showMessageDialog(gameFrame, textMessage, "Attenzione!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Controlla se e' stato selezionato un profilo con cui giocare.
     * @return indica se e' stato selezionato un profilo con cui giocare
     */
    public boolean isCurrentPlayerSet() { return isCurrentPlayerSet; }

    /**
     * Controlla se il giocatore selezionato e' ancora presente nel database.
     * @return indica se il giocatore selezionato esiste ancora all'interno del database
     */
    public boolean doesCurrentPlayerExist() { return getPlayers().contains(currentJunoPlayer); }

    /**
     * Compie le azioni necessarie dopo che il giocatore ha selezionato una carta da giocare.
     */
    public void junoPlayersTurn() {
        // se il giocatore ha finito le carte finisce il round
        if (gameTable.isCurrentJunoPlayersHandEmpty() && gameTable.getHasPushedUno()) roundsEnd();
        // se ha finito le carte ma ha dimenticato di dire "uno" deve pescare due carte
        else if ((gameTable.isCurrentJunoPlayersHandEmpty() && !gameTable.getHasPushedUno())) {
            gameTable.hasNotCalledUno();
            displayMessage("Hai dimenticato di dire UNO! Peschi due carte.", 48);
        }
        // se ha cambiato colore del gioco viene stampato a video l'avviso
        String colorMessage = gameTable.getColorMessage();
        if (colorMessage != null) displayMessage(colorMessage, 48);
        // se ha detto "uno" viene stampato a video l'avviso
        String unoMessage = gameTable.getUnoMessage();
        if (unoMessage != null) displayMessage(unoMessage, 48);
        // si prosegue con il giocatore successivo
        if (gameTable.getValidValue().equals(Value.INVERTI) && !gameTable.getIsTopCardUsed()) {
            updateGameFrame();
            gameTable.nextPlayer();
        } else {
            gameTable.nextPlayer();
            updateGameFrame();
        }
    }

    /**
     * Aiuta a proseguire nel gioco compiendo le azioni necessarie.
     */
    public void nextMove() {
        if (!gameTable.isCurrentJunoPlayersTurn()) {
            // se e' il turno di un giocatore artificiale
            if (!gameTable.playOpponentsTurn()) {
                // il giocatore non puo' usare le proprie carte e/o deve saltare il turno
                displayMessage(gameTable.getCardMessage(), 48);
            } else {
                // il giocatore puo' usare una delle sue carte
                if (gameTable.isPlayersTurnHandEmpty()) {
                    roundsEnd();
                    return;
                }

                // vengono stampati a video messaggi che notificano il giocatore umano di quello che e' successo
                String colorMessage = gameTable.getColorMessage();
                if (colorMessage != null) displayMessage(colorMessage, 48);
                String unoMessage = gameTable.getUnoMessage();
                if (unoMessage != null) displayMessage(unoMessage, 48);
                String drawCardMessage = gameTable.getDrawCardMessage();
                if (drawCardMessage != null) displayMessage(drawCardMessage, 48);

            }
            // si prosegue con il giocatore successivo
            if (gameTable.getValidValue().equals(Value.INVERTI) && !gameTable.getIsTopCardUsed()) {
                updateGameFrame();
                gameTable.nextPlayer();
            } else {
                gameTable.nextPlayer();
                updateGameFrame();
            }
        } else if (gameTable.isCurrentJunoPlayersTurn()) {
            // se e' il turno del giocatore umano
            if (!gameTable.seeTopCard()) {
                // se non puo' usare una propria carta e/o deve saltare il turno
                displayMessage(gameTable.getCardMessage(), 48);
                gameTable.nextPlayer();
                updateGameFrame();
            }
            else
                // se tocca al giocatore umano scegliere o pescare una carta
                displayMessage("Tocca a te!", 48);
        }
    }

    /**
     * Controlla chi ha vinto il turno e se e' finita la partita.
     */
    public void roundsEnd() {
        gameTable.roundsEnd();
        if (gameTable.getPoints(gameTable.getPlayersTurn()) >= 500) {
            gameEnd();
            return;
        }
        displayMessage(gameTable.getPlayersTurnName()+ " ha vinto il turno ed ha "+
                gameTable.getPoints(gameTable.getPlayersTurn())+" punti! Si continua a giocare fino a 500!", 30);
        gameTable.resetTable();
        updateGameFrame();
    }

    /**
     * Mostra a video chi ha vinto la partita e aggiorna i dati sulle partite del giocatore umano.
     */
    public void gameEnd() {
        displayMessage(gameTable.getPlayersTurnName()+" ha vinto la partita!", 48);
        if (gameTable.isCurrentJunoPlayersTurn()) {
            gameTable.getCurrentJunoPlayer().winGame();
        } else {
            gameTable.getCurrentJunoPlayer().loseGame();
        }
        gameFrame.dispose();
    }

    /**
     * Aggiorna il frame di gioco.
     */
    public void updateGameFrame() {
        // aggiorna il giocatore corrente
        gameFrame.setPlayersTurn(gameTable.getPlayersTurn());
        // aggiorna le carte del giocatore umano
        gameFrame.setJunoPlayersCards(gameTable.getCurrentJunoPlayersHand());
        // aggiorna la carta nel mazzo di scarto
        gameFrame.setDiscardDeckTopCard(gameTable.getTopCard());

        // aggiorna il numero di carte dei giocatori artificiali
        NPlayerPanel p1 = (NPlayerPanel)gameFrame.getPanel(0);
        if (gameTable.getPlayersHand(1).size() == 1) p1.setUno();
        else p1.unSetUno();
        OPlayerPanel p2 = (OPlayerPanel)gameFrame.getPanel(1);
        if (gameTable.getPlayersHand(0).size() == 1) p2.setUno();
        else p2.unSetUno();
        EPlayerPanel p3 = (EPlayerPanel)gameFrame.getPanel(3);
        if (gameTable.getPlayersHand(2).size() == 1) p3.setUno();
        else p3.unSetUno();
    }
}
