package Logic;

import com.mpatric.mp3agic.Mp3File;

import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class Song implements Serializable {
    private String artist;
    private String title;
    private String album;
    private String name = "";
    private String path;
    private byte[] image;
    private int frame;
    private long length;

    public Song(String path, Library library) {
        try {
            Mp3File mp3file = new Mp3File(path);

            String temp = "";
            for (int i = path.length() - 5; path.charAt(i) != '\\'; i--)
                temp += path.charAt(i);
            for (int i = temp.length() - 1; i > -1; i--)
                name += temp.charAt(i);

            this.path = "D:\\Codes\\AP\\JPotify\\src\\DataBase\\songs\\" + name + ".mp3";

            // todo 128 byte akharo bekhon
            if (mp3file.hasId3v1Tag()) {
                artist = mp3file.getId3v1Tag().getArtist();
                title = mp3file.getId3v1Tag().getTitle();
                album = mp3file.getId3v1Tag().getAlbum();
            }

            if (mp3file.hasId3v2Tag()) {
                image = mp3file.getId3v2Tag().getAlbumImage();
            }

            length = mp3file.getLength();
            frame = mp3file.getFrameCount();

            System.out.println(frame);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Song Song e1:\n  cant open song");
        }

        Path source = Paths.get(path);
        Path target = Paths.get(this.path);

        try {
            Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }


        if (album != null) {
            if (library.doesAlbumExist(album))
                for (Album a : library.getAlbums()) {
                    if (a.getName().equalsIgnoreCase(album)) {
                        a.add(this);
                        break;
                    }
                }
            else {
                ArrayList<Song> sa = new ArrayList<>();
                sa.add(this);
                library.createAlbum(album, sa);
            }
        } else
            library.getMiscellaneous().add(this);

    }

    public byte[] getImage() {
        return image;
    }

    private void show() {

    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getFrame() {
        return frame;
    }

    public String getName() {
        return name;
    }

    public boolean search(String key) {
        boolean ar = false;
        boolean na = false;
        boolean ti = false;

        if (artist != null)
            ar = artist.toLowerCase().contains(key);

        if (name != null)
            na = name.toLowerCase().contains(key);

        if (title != null)
            ti = title.toLowerCase().contains(key);


        return ar || na || ti;
    }
}
