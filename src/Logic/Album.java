package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
    private ArrayList<Song> songs;
    private String name;

    Album(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }

    public boolean contains(Song song) {
        return songs.contains(song);
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void add(Song song) {
        songs.add(song);
    }

    public void remove(Song song) {
        songs.remove(song);
    }

    public byte[] getImage() {
        if (songs.isEmpty())
            return null;
        return songs.get(0).getImage();
    }

    public String getName() {
        return name;
    }



}
