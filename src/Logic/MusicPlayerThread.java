package Logic;

import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MusicPlayerThread extends Thread {
    private MP3Player mp3Player;
    private Thread playThread;
    private Thread stopThread;
    private Thread resumeThread;
    private Thread playNextSongThread;

    public MusicPlayerThread(MP3Player mp3Player, SongsManager songsManager) {
        this.mp3Player = mp3Player;
        playThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mp3Player.playSong();
                } catch (JavaLayerException | FileNotFoundException e) {
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
                } catch (JavaLayerException | FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

        playNextSongThread = new Thread(new Runnable() {
            @Override
            public void run() {
                ExecutorService executorService = Executors.newCachedThreadPool();
                SongsManager sm = songsManager;
                Serialization serialization = new Serialization();
                do {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    if (mp3Player.isSongFinished()){
                        executorService.execute(getStopThread());
                        executorService.execute(getPlayThread());
                    }
                } while (true);
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

    public Thread getPlayNextSongThread() {
        return playNextSongThread;
    }
}
