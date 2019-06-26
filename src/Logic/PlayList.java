package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class PlayList extends SongsList implements Serializable {
    PlayList(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }



}
