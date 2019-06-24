import java.util.ArrayList;

public class Library {
    private ArrayList<Song> allSongs;
    private ArrayList<Album> albums;
    private Album friendsActivity;
    private Album favorites;

    Library() {
        allSongs = new ArrayList<>();
        albums = new ArrayList<>();
        friendsActivity = new Album("Friends Activity");
        favorites = new Album("Favorites");
    }

    public void updateLastPlayed(Song song) {
        Song s = song;
        allSongs.remove(song);
        allSongs.add(allSongs.size(), s);
        for (Album a : albums)
            if (a.contains(song)) {
                a.getSongs().remove(song);
                a.getSongs().add(s);
                Album aa = a;
                albums.remove(a);
                albums.add(aa);
            }
    }

    public void addSongToLibrary(Song song) {
        allSongs.add(song);
    }

    public void createAlbumInLibrary(String albumName, ArrayList<Song> songs) {
        Album album = new Album(albumName);
        for (Song s : songs)
            album.addToAlbum(s);
        albums.add(album);
    }

    public ArrayList<Song> getAllSongs() {
        return allSongs;
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
        if (name.equalsIgnoreCase("favorites") || name.equalsIgnoreCase("friends activity"))
            return true;
        return false;
    }


}
