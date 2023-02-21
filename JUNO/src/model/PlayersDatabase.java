package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Rappresenta il database con i giocatori umani. Il database adotta il design pattern Singleton per
 * averne un'unica istanza in tutto il codice del progetto.
 */
public class PlayersDatabase {

    /**
     * Lista con i giocatori.
     */
    private List<JUnoPlayer> players;

    /**
     * Unica istanza del database (Singleton pattern).
     */
    private static PlayersDatabase pdb;

    /**
     * Costruttore privato (Singleton pattern) che inizializza la lista dei giocatori.
     */
    private PlayersDatabase() { players = new ArrayList<>(); }

    /**
     * Unico metodo per ottenere un'istanza del database (Singleton pattern).
     * @return istanza del database
     */
    public static PlayersDatabase getInstance(){
        if (pdb == null) pdb = new PlayersDatabase();
        return pdb;
    }

    /**
     * Permette di salvare i dati dei giocatori in un file.
     * @param file il file in cui salvare i dati dei giocatori
     * @throws IOException
     */
    public void savePlayersData(File file) throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);

        oos.writeObject(players.toArray(new JUnoPlayer[players.size()]));

        fos.close();
        oos.close();
    }

    /**
     * Permette di caricare nel database i dati dei giocatori.
     * @param file il file con i dati dei giocatori da caricare
     * @throws IOException
     */
    public void loadPlayersData(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);

        try {
            players.clear();
            players.addAll(Arrays.asList((JUnoPlayer[]) ois.readObject()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        fis.close();
        ois.close();
    }

    /**
     * Permette di aggiungere un giocatore al database.
     * @param player il giocatore da aggiungere al database
     */
    public void addPlayer(JUnoPlayer player) { players.add(player); }

    /**
     * Permette di rimuovere un giocatore dal database.
     * @param name il nome del giocatore da rimuovere dal database
     */
    public void removePlayer(String name) { players.remove(getPlayerByName(name)); }

    /**
     * Restituisce la lista dei giocatori presenti nel database.
     * @return la lista dei giocatori
     */
    public List<JUnoPlayer> getPlayers() { return players; }

    /**
     * Restituisce il giocatore presente nel database in base al suo nickname.
     * @param nickname il nickname del giocatore da restituire
     * @return il giocatore il cui nickname corrisponde al nickname fornito in input
     */
    public JUnoPlayer getPlayerByName(String nickname) {
        List<String> nicknames = players.stream().map(p->p.getNickname()).collect(Collectors.toList());
        int index = nicknames.indexOf(nickname);
        return players.get(index);
    }
}
