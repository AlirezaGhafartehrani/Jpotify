package Logic;

import java.util.ArrayList;

public class SongsList {
    protected ArrayList<Song> songs;
    protected String name;

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
