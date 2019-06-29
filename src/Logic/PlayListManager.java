package Logic;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PlayListManager implements Serializable {
    private ArrayList<PlayList> playLists;
    private PlayList favorites;
    private HashMap<String, Song> friendsActivity;

    PlayListManager() {
        playLists = new ArrayList<>();
        favorites = new PlayList("Favorites");
        friendsActivity = new HashMap<>();
    }

    public void createPlayList(String playListName, ArrayList<Song> songs) {
        PlayList p = new PlayList(playListName);
        for (Song s : songs)
            p.add(s);

        playLists.add(p);
    }

    public HashMap getFriendsActivity() {
        return friendsActivity;
    }

    public void updateFriendsActivity(HashMap<String, Song> friendsActivity) {
        this.friendsActivity = friendsActivity;
    }

    public PlayList search(String key) {
        for (PlayList playList : playLists)
            if (playList.getName().toLowerCase().contains(key))
                return playList;
        return null;
    }

}
