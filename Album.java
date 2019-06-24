import java.util.ArrayList;

public class Album {
    private ArrayList<Song> songs;
    private String name;

    Album(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }

    public void addToAlbum(Song song) {
        songs.add(song);
    }

    public void removeFromAlbum(Song song) {
        songs.remove(song);
    }

    public byte[] getAlbumImage() {
        if (songs.isEmpty())
            return null;
        return songs.get(0).getImage();
    }

    public String getName() {
        return name;
    }

    public boolean contains(Song song) {
        return songs.contains(song);

    }

    public ArrayList<Song> getSongs() {
        return songs;
    }
}
