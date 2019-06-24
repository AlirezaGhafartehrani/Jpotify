import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.*;
import javazoom.jl.player.Player;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javax.sound.sampled.*;


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

        Player p = new Player(new FileInputStream(song.getPath()));


        FloatControl volume= (FloatControl) line.getControl(FloatControl.Type.MASTER_GAIN);

//        Player.getGainControl();
    }

    public void playSong() throws JavaLayerException {
        player.play();


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


    public void setOutputVolume(float value)
    {
        String command = "set volume " + value;
        try
        {
            ProcessBuilder pb = new ProcessBuilder("osascript","-e",command);
            pb.directory(new File("/usr/bin"));
            StringBuffer output = new StringBuffer();
            Process p = pb.start();
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine())!= null)
            {
                output.append(line + "\n");
            }
            System.out.println(output);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

}
