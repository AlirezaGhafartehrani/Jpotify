package Logic;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.AudioDevice;
import javazoom.jl.player.advanced.AdvancedPlayer;

import java.io.*;

public class AudioPlayer implements Runnable {
    private AdvancedPlayer player;
    private boolean isPause;
    private Song song;
    private int frame;

    public void setSong(Song song) throws JavaLayerException, FileNotFoundException {
        player = new AdvancedPlayer(new FileInputStream(song.getPath()));
        frame = 0;
        isPause = false;
        this.song = song;
    }

    public void playSong() throws JavaLayerException, FileNotFoundException {
//        player.play();

        player.play(50);
        System.out.println("50");

        player.play(200);
        System.out.println("200");

        player.play(500);
        System.out.println("500");

//        while (player.play(1)) {
//            System.out.println("played");
//            if (false) {
//                synchronized (player) {
//                    player.wait();
//                }
//            }
//        }

    }

    public void pauseSong() {
        isPause = true;
    }

    public void resumeSong() {
        isPause = false;
        synchronized (player) {
            player.notifyAll();
        }
    }

    public void increaseSound() {
    }

    public void decreaseSound() {
    }

    public void seekTo(int frame) {
        synchronized (player) {
            try {
                player.close();
                player = new AdvancedPlayer(new FileInputStream(song.getPath()));
                this.frame = frame;
                this.playSong();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    @Override
    public void run() {
        try {
            this.playSong();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void setOutputVolume(float value) {
        String command = "set volume " + value;
        try {
            ProcessBuilder pb = new ProcessBuilder("osascript", "-e", command);
            pb.directory(new File("/usr/bin"));
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            System.out.println(output);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
