package Logic;

import com.mpatric.mp3agic.Mp3File;

import java.io.Serializable;

public class Song implements Serializable {
    private String artist;
    private String title;
    private String album;
    private String path;
    private byte[] image;
    private int frame;
    private long length;

    public Song(String path) {
        try {
            Mp3File mp3file = new Mp3File(path);
            this.path = path;

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

        } catch (Exception e) {
            System.out.println("Song Song e1:\n  cant open song");
        }


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


}
