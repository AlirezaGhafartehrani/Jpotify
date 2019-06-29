package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class SongsManager implements Serializable {
    private ArrayList<Song> allSongs;
    private ArrayList<Song> recentlyPlayedSongs;
    private Library library;
    private PlayListManager playListManager;

    public SongsManager() {
        allSongs = new ArrayList<>();
        library = new Library(allSongs);
        playListManager = new PlayListManager();
        recentlyPlayedSongs = new ArrayList<>();
    }

    public void updateLastPlayed(Song song) {
        Song s = song;

        recentlyPlayedSongs.remove(s);
        recentlyPlayedSongs.add(s);

        library.updateLastPlayed(s);
    }

    public void removeSong(Song song) {
        allSongs.remove(song);
        recentlyPlayedSongs.remove(song);
    }

    public void addSong(Song song) {
        allSongs.add(song);
        recentlyPlayedSongs.add(0, song);
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


    public ArrayList<Object> search(String key) {
        ArrayList<Object> searchResults = new ArrayList<>();

        // search in songs
        for (Song s : allSongs)
            if (s.search(key))
                searchResults.add(s);

        Album albumResult = library.search(key);
        if (albumResult != null)
            searchResults.add(albumResult);

        PlayList playListResult = playListManager.search(key);
        if (playListResult != null)
            searchResults.add(playListResult);

        return searchResults;
    }

    public Song getMe() {
        return allSongs.get(0);
    }
}
