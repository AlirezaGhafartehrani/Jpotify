package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class MP3Player {
    private AdvancedPlayer player;
    private boolean isPause;
    private Song currentSong;
    private ArrayList<Song> songs;
    private int currentSongIndex;
    private boolean isSongFinished;

    public void setSong(Song song, ArrayList<Song> songs) throws JavaLayerException, FileNotFoundException {
        player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(song.getPath())));
        for (int index = 0; index < songs.size(); index++)
            if (songs.get(index) == song) {
                currentSongIndex = index;
                break;
            }
        isPause = false;
        currentSong = song;
        this.songs = songs;
        isSongFinished = false;
    }

    public void playSong() throws JavaLayerException {
        isPause = false;
        System.out.println("i want to play " + currentSong.getPath().charAt(currentSong.getPath().length() - 5));
        while (player.play(1)) {
            if (isPause) {
                synchronized (player) {
                    break;
                }
            }
        }
        if (!player.play(1))
            isSongFinished = true;
    }

    public void pauseSong() {
        isPause = true;
    }

    public void resumeSong() {
        isPause = false;
    }

    public void increaseSound() {
    }

    public void decreaseSound() {
    }

    public void seekTo(int frame) throws FileNotFoundException {
        synchronized (player) {
            player.close();
            try {
                player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
                player.play(frame, frame + 1);
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        }
    }

    public void setOutputVolume(float value) {
        String command = "set volume " + value;

        ProcessBuilder pb2 = new ProcessBuilder("set volume 0.5f");
        try {
            pb2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            ProcessBuilder pb = new ProcessBuilder("osascript", "-e", command);

            pb.directory(new File("\\usr\\bin"));
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    // ** hatman ghabl az estefadeh az in methode az update estefade kon
    public void changeToNextSong() throws FileNotFoundException, JavaLayerException {
        synchronized (player) {
            try {
                player.close();
                currentSongIndex--;
                if (currentSongIndex == -1)
                    currentSongIndex = songs.size() - 2;
                if (currentSongIndex != -1)
                    currentSong = songs.get(currentSongIndex);
                else {
                    currentSongIndex = 0;
                    currentSong = songs.get(currentSongIndex);
                }
                player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    public void changeToPreviousSong() {
        synchronized (player) {
            try {
                player.close();
                if (currentSongIndex == songs.size() - 1)
                    currentSongIndex = 0;
                currentSong = songs.get(currentSongIndex);
                player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Song getCurrentSong() {
        return currentSong;
    }

    public boolean setRandomSong() throws JavaLayerException, FileNotFoundException {
        if (songs.size() == 1) {
            synchronized (player) {
                player.close();
                currentSong = null;
                return false;
            }
        }


        synchronized (player) {
            player.close();
            Song s;
            do {
                s = songs.get((new Random()).nextInt(songs.size()));
            } while (currentSong == s);
            for (int index = 0; index < songs.size(); index++)
                if (songs.get(index) == s) {
                    currentSongIndex = index;
                    break;
                }
            currentSong = s;
            player = new AdvancedPlayer(new BufferedInputStream(new FileInputStream(currentSong.getPath())));
        }
        return true;
    }


    public ArrayList<Song> getSongs() {
        return songs;
    }

    public boolean isSongFinished() {
        return isSongFinished;
    }
}
