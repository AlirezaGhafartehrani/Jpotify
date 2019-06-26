package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayListManager implements Serializable {
    private ArrayList<PlayList> playLists;
    private PlayList favorites;
    private PlayList friendsActivity;

    PlayListManager() {
        playLists = new ArrayList<>();
        favorites = new PlayList("Favorites");
        friendsActivity = new PlayList("Friends Activity");
    }

    public void updateLastPlayed(Song song) {
        for (PlayList p : playLists)
            if (p.contains(song)) {
                Song s = song;
                p.remove(s);
                p.add(s);
            }
        if (favorites.contains(song)) {
            Song s = song;
            favorites.remove(s);
            favorites.add(s);
        }
        if (friendsActivity.contains(song)) {
            Song s = song;
            friendsActivity.remove(s);
            friendsActivity.add(s);
        }
    }

    public void createPlayList(String playListName, ArrayList<Song> songs) {
        PlayList p = new PlayList(playListName);
        for (Song s : songs)
            p.add(s);

        playLists.add(p);
    }


}
