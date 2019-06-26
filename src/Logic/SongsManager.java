package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class SongsManager implements Serializable {
    private ArrayList<Song> allSongs;
    private Library library;
    private PlayListManager playListManager;

    public SongsManager() {
        allSongs = new ArrayList<>();
        library = new Library(allSongs);
        playListManager = new PlayListManager();
    }

    public void updateLastPlayed(Song song) {
        Song s = song;

        allSongs.remove(s);
        allSongs.add(allSongs.size(), s);

        library.updateLastPlayed(s);

        playListManager.updateLastPlayed(s);
    }

    public void removeSong(Song song){
        allSongs.remove(song);
    }

    public void addSong(Song song) {
        allSongs.add(song);
    }

    public ArrayList<Song> getAllSongs() {
        return allSongs;
    }

    public Library getLibrary() {
        return library;
    }

    public PlayListManager getPlayListManager() {
        return playListManager;
    }

    public Song getNextSong() {
        return allSongs.get(allSongs.size() - 1);
    }

    public Song getNextSong(String albumName) {
        return library.getAlbum(albumName).get(library.getAlbum(albumName).size() - 1);
    }







}
