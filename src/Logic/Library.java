package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Library implements Serializable {
    private ArrayList<Album> albums;
    private Album miscellaneous;
    private ArrayList<Song> allSongs;

    public Library(ArrayList<Song> allSongs) {
        this.allSongs = allSongs;
        albums = new ArrayList<>();
        miscellaneous = new Album("Miscellaneous");
    }

    public void updateLastPlayed(Song song) {
        for (Album a : albums)
            if (a.contains(song)) {
                Song s = song;
                a.remove(song);
                a.add(s);
                Album album = a;
                albums.remove(a);
                albums.add(a);
                return;
            }
        if (miscellaneous.contains(song)) {
            Song s = song;
            miscellaneous.remove(song);
            miscellaneous.add(s);
        }
    }

    public void createAlbum(String albumName, ArrayList<Song> songs) {
        Album album = new Album(albumName);
        for (Song s : songs)
            album.add(s);
        albums.add(album);
    }

    public ArrayList<Song> getAlbum(String name) {
        for (Album a : albums)
            if (a.getName().equalsIgnoreCase(name))
                return a.getSongs();
        return null;
    }

    public boolean doesAlbumExist(String name) {
        for (Album a : albums)
            if (a.getName().equalsIgnoreCase(name))
                return true;
        if (name.equalsIgnoreCase("miscellaneous"))
            return true;
        return false;
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void addToAlbum(String albumName, Song song) {
        boolean add = false;
        for (Album album : albums)
            if (album.getName().equalsIgnoreCase(albumName)) {
                album.add(song);
                add = true;
                break;
            }
        if (!add)
            miscellaneous.add(song);
    }

    public Album getMiscellaneous() {
        return miscellaneous;
    }

    public Album search (String key){
        for (Album album:albums)
            if (album.getName().toLowerCase().contains(key))
                return album;
        return null;
    }

}
