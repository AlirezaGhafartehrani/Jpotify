package Logic;

import javazoom.jl.decoder.JavaLayerException;

public class MusicPlayerThread extends Thread {
    private MP3Player mp3Player;
    private Thread playThread;
    private Thread stopThread;
    private Thread resumeThread;

    public MusicPlayerThread(MP3Player mp3Player) {
        this.mp3Player = mp3Player;
        playThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mp3Player.playSong();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });

        stopThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mp3Player.pauseSong();
            }
        });

        resumeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                mp3Player.resumeSong();
                try {
                    mp3Player.playSong();
                } catch (JavaLayerException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public MP3Player getAudioPlayer() {
        return mp3Player;
    }

    public Thread getPlayThread() {
        return playThread;
    }

    public Thread getStopThread() {
        return stopThread;
    }

    public Thread getResumeThread() {
        return resumeThread;
    }



}
