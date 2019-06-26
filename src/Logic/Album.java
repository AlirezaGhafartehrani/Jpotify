package Logic;

import java.io.Serializable;
import java.util.ArrayList;

public class Album extends SongsList implements Serializable {
    Album(String name) {
        songs = new ArrayList<>();
        this.name = name;
    }



}
