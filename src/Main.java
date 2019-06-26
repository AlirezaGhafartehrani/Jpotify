import Logic.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) throws IOException, JavaLayerException, InterruptedException {
        String userName = " ";


        Song s1 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\01.mp3");
        Song s2 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\02.mp3");
        Song s3 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\03.mp3");
        Song s4 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\04.mp3");

        MP3Player mp3Player = new MP3Player();
        MusicPlayerThread musicPlayerThread = new MusicPlayerThread(mp3Player);

        SongsManager songsManager;

        Serialization serialization = new Serialization();

        if ((songsManager = serialization.isThereAnyRecord()) == null)
            songsManager = new SongsManager();


        songsManager.addSong(s1);
        songsManager.addSong(s2);
        songsManager.addSong(s3);
        songsManager.addSong(s4);

        mp3Player.setSong(s3, songsManager.getAllSongs());

        ExecutorService executorService = Executors.newCachedThreadPool();

        Scanner input = new Scanner(System.in);

        do {
            int s = input.nextInt();

            if (mp3Player.isSongFinished())
                s = 4;
//            else s = GUI.getAction();

            if (s == 0)
                break;

            switch (s) {
                case 12: // choose and play song
//                    Song s = GUI.getPlayingSong();
//                    ArrayList<Song> a = GUI.getPlayongSongLocation();
                    Song s0 = new Song(":|");
                    ArrayList<Song> l = new ArrayList<>();
                    mp3Player.setSong(s0, l);
                    break;

                case 1: // seek song
//                    int frame = GUI.getSeekFrame();
                    int frame = 0;
                    executorService.execute(musicPlayerThread.getStopThread());
                    musicPlayerThread.getAudioPlayer().seekTo(frame);
                    executorService.execute(musicPlayerThread.getPlayThread());
                    break;

                case 2: // pause
                    executorService.execute(musicPlayerThread.getStopThread());
                    break;

                case 3: // resume
                    executorService.execute(musicPlayerThread.getResumeThread());
                    break;

                case 4: // play next song
                    executorService.execute(musicPlayerThread.getStopThread());
                    songsManager.updateLastPlayed(mp3Player.getCurrentSong());
                    mp3Player.changeToNextSong();
                    executorService.execute(musicPlayerThread.getPlayThread());
                    break;

                case 5: // play previous song
                    executorService.execute(musicPlayerThread.getStopThread());
                    songsManager.updateLastPlayed(mp3Player.getCurrentSong());
                    mp3Player.changeToPreviousSong();
                    executorService.execute(musicPlayerThread.getPlayThread());
                    break;

                case 6: // add song
//                    String p = GUI.addNewSong();
                    String p = "D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\03.mp3";
                    songsManager.addSong(new Song(p));
                    break;

                case 7: // remove song
//                  Song s = GUI.getRemoveSong();
                    Song ss = s3;
                    if (ss == mp3Player.getCurrentSong()) {
                        executorService.execute(musicPlayerThread.getStopThread());
                        if (mp3Player.setRandomSong())
                            executorService.execute(musicPlayerThread.getPlayThread());
                        songsManager.removeSong(ss);
                    } else
                        songsManager.removeSong(ss);

                    break;

                case 8: // add playList
                    // GUI.addPlayList(songsManager.getPlayListManager());


                    break;

                case 9:
                    mp3Player.setOutputVolume(0.1f);
                    break;

                case 10:
                    mp3Player.setOutputVolume(6.9f);

                    break;

                case 11: // save actions
                    serialization.serialization(songsManager);
                    break;


            }
        } while (true);


    }

}


/*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(10000);
                    System.out.println("lets run ");
                    executorService.execute(musicPlayerThread.getStopThread());
                    System.out.println("hooooooooop");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


        System.out.println("++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n                                Entering new phase");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.currentThread().sleep(11000);
                    System.out.println("lets run --------------------------");
                    executorService.execute(musicPlayerThread.getResumeThread());
//                    executorService.execute(musicPlayerThread.getPlayThread());
                    System.out.println("hooooooooop -----------------------");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


//        musicPlayerThread.runPlayThread();
//
//        System.out.println("                    aaaaaaaaaaaaaaaaaaa");
//
//        musicPlayerThread.runStopThread();
//
//        musicPlayerThread.getPlayThread().run();
//        musicPlayerThread.getStopThread().run();

    }


//
////        audioPlayer.playSong();
//
//
//
//        System.out.println("thread: 1 start");
//        thread.start();
//        System.out.println("thread: 2 start");
//
//
//        System.out.println("pausing");
//
////        try {
////            thread.join(10000);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////
////        if (thread.isAlive()) {
////            System.out.println("isAlive");
////            thread.stop();
////        } else System.out.println("dead");
//
//
//
//
//        Thread t2 =new Thread(() -> {
//            System.out.println("                try to stop thread");
//            audioPlayer.pauseSong();
//            System.out.println("                tried");
//
//        }
//        );
//
//        System.out.println("                waiting:");
//        t2.sleep(5000);
//        System.out.println("                finish wating:");
//
//        System.out.println("                before start");
//        t2.start();
//        System.out.println("                after start:");
//
//
//        System.out.println("thread: 2 run");
//
//        Thread t3 = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                        audioPlayer.resumeSong();
//            }
//        });
//
//        System.out.println("\n-----------------------------------------------------------------------------------\n\n\n                waiting:");
//        t3.sleep(7000);
//        System.out.println("                finish wating:");
//
//        System.out.println("                before start");
//        t3.start();
//        System.out.println("                after start:");
//
//
//
//
//
//
////        audioPlayer.playSong();
//
//    }

//        Song s = new Song("D:\\Codes\\AP\\JPotify\\src\\1.mp3");
/*
        Serialization serializator = new Serialization();

        Library library;
        AudioPlayer audioPlayer = new AudioPlayer();

        try {
            library = serializator.deSerialization();

            System.out.println("finished try");
        } catch (Exception e) {
            library = new Library();


            Song s1 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\01.mp3");
            Song s2 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\02.mp3");
            Song s3 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\03.mp3");

            library.addSongToLibrary(s1);
            library.addSongToLibrary(s2);
            library.addSongToLibrary(s3);

            ArrayList<Song> arrayList = new ArrayList<>();
            arrayList.add(s2);
            arrayList.add(s3);

            library.createAlbum("test", arrayList);


            try {
                serializator.serialization(library);
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            System.out.println("finished catch");
        }

        audioPlayer.setSong(library.getAllSongs().get(0));
        audioPlayer.playSong();

        audioPlayer.setSong(library.getAlbum("test").get(0));
        audioPlayer.playSong();

        audioPlayer.setSong(library.getAlbum("test").get(1));
        audioPlayer.playSong();


        System.out.println("\nallSongs:\n");
        for (int index = 0; index < library.getAllSongs().size(); index++) {
            System.out.println("\nstart playing song: " + library.getAllSongs().get(index));
        }

        System.out.println("\nalbums:\n");
        for (int index = 0; index < library.getAlbums().size(); index++) {
            System.out.println("\nals: " + library.getAlbums().get(index).getName() + "\n  Songs:");
            for (int i = 0; i < library.getAlbums().get(index).getSongs().size(); i++)
                System.out.println(library.getAlbums().get(index).getSongs().get(i));
        }


//            audioPlayer.setSong(library.getAllSongs().get(0));
//            library.updateLastPlayed(library.getAllSongs().get(0));
//            audioPlayer.playSong();
//            System.out.println("finish playing song: " + library.getAllSongs().get(0).getPath());

//*/
/*
        for (int i = 0; i < library.getAlbum("test").size(); i++)
            System.out.println(library.getAlbum("test").get(i));


        for (int index = library.getAlbum("test").size() - 1; index > -1; index--) {
            System.out.println("before: ");
            for (int i = 0; i < library.getAlbum("test").size(); i++)
                System.out.println(library.getAlbum("test").get(i));


            System.out.println("\nstart playing song: " + library.getAlbum("test").get(index));
            audioPlayer.setSong(library.getAlbum("test").get(index));
            library.updateLastPlayed(library.getAlbum("test").get(index));
            audioPlayer.playSong();
            System.out.println("finish playing song: " + library.getAlbum("test").get(index));

            System.out.println("after: ");
            for (int i = 0; i < library.getAlbum("test").size(); i++)
                System.out.println(library.getAlbum("test").get(i));



        }

        for (int i = 0; i < library.getAlbum("test").size(); i++)
            System.out.println(library.getAlbum("test").get(i));
*/


//    GUI gui = new GUI();
//        while (gui.open()) {
//            switch (gui.action()) {
//                case 1: // add song
//                    String p = gui.openAddSongMenu();
//                    libreary.add(new Song(p));
//                    gui.confirmAdd();
//                    // ...
//
//                case 2:
//                    // ... play song
//                    String p = gui.getSong();
//                    player.changeSong(p);
//                    player.playSong();
//                    gui.changeToPlaySong();
//
//            }
//        }
