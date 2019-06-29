import GUI.GUI;
import Logic.*;
import Network.NetworkManager;
import javazoom.jl.decoder.JavaLayerException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    
    
    
    public static void main(String[] args) throws IOException, JavaLayerException, InterruptedException {
        String userName = " ";
        GUI gui = new GUI();
        SongsManager songsManager;
        Serialization serialization = new Serialization();
        MP3Player mp3Player = new MP3Player();

        if ((songsManager = serialization.isThereAnyRecord()) == null) {
            songsManager = new SongsManager();
        }
     /*        Song s1 = new Song("//Users//natanil//NetBeansProjects//JPotify//src//DataBase//01.mp3", songsManager.getLibrary());
             Song s2 = new Song("//Users//natanil//NetBeansProjects//JPotify//src//DataBase//02.mp3", songsManager.getLibrary());
             Song s3 = new Song("//Users//natanil//NetBeansProjects//JPotify//src//DataBase//03.mp3", songsManager.getLibrary());
             Song s4 = new Song("//Users//natanil//NetBeansProjects//JPotify//src//DataBase//04.mp3", songsManager.getLibrary());
 
            songsManager.addSong(s1);
            songsManager.addSong(s2);
            songsManager.addSong(s3);
            songsManager.addSong(s4);

           mp3Player.setSong(s3, songsManager.getAllSongs());
        */
//       }


        ExecutorService executorService = Executors.newCachedThreadPool();
        mp3Player.setSong(songsManager.getMe(), songsManager.getAllSongs());   

//        Scanner input = new Scanner(System.in);

        NetworkManager networkManager = new NetworkManager(mp3Player.getCurrentSong());
        executorService.execute(networkManager.getReceiverThred());
        executorService.execute(networkManager.getSendThred());

        MusicPlayerThread musicPlayerThread = new MusicPlayerThread(mp3Player, songsManager);
        executorService.execute(musicPlayerThread.getPlayNextSongThread());
        SongsManager finalSongsManager = songsManager;

        executorService.execute(new Thread(new Runnable() {
            @Override
            public void run() {
                do {
                    networkManager.updateLastPlayed(mp3Player.getCurrentSong());
                    finalSongsManager.getPlayListManager().updateFriendsActivity(networkManager.getFriendsActivity());
                    finalSongsManager.updateLastPlayed(mp3Player.getCurrentSong());
                    try {
                        serialization.updateRecord(finalSongsManager);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } while (true);
            }
        }));

        do {
            gui.setAction(0);

            // get action from user
            do {
                System.out.println("wait\n");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("check for action\naction = "+gui.getAction());
                if (gui.getAction() != 0)
                    break;
            } while (true);


            switch (gui.getAction()) {
                case 0:
                    break;

                case 12: // choose and play song
//                    Song s = GUI.getPlayingSong();
//                    ArrayList<Song> a = GUI.getPlayongSongLocation();
//                    Song s0 = new Song(":|", songsManager.getLibrary());
//                    ArrayList<Song> l = new ArrayList<>();
                    mp3Player.setSong(songsManager.getMe(), songsManager.getAllSongs());
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
                    serialization.updateRecord(songsManager);
                    break;

                case 5: // play previous song
                    executorService.execute(musicPlayerThread.getStopThread());
                    songsManager.updateLastPlayed(mp3Player.getCurrentSong());
                    mp3Player.changeToPreviousSong();
                    executorService.execute(musicPlayerThread.getPlayThread());
                    serialization.updateRecord(songsManager);
                    break;

                case 6: // add song
//                    String p = GUI.addNewSong();
                    String p = "I:\\Files\\Amirkabir University of Technology\\4th semester\\Advance Programming\\Project\\src\\DataBase\\songs\\01.mp3";
                    songsManager.addSong(new Song(p, songsManager.getLibrary()));
                    serialization.updateRecord(songsManager);
                    break;

                case 7: // remove song
//                  Song s = GUI.getRemoveSong();
                    Song ss = null;//= s3;
                    if (ss == mp3Player.getCurrentSong()) {
                        executorService.execute(musicPlayerThread.getStopThread());
                        if (mp3Player.setRandomSong())
                            executorService.execute(musicPlayerThread.getPlayThread());
                    }
                    songsManager.removeSong(ss);
                    serialization.updateRecord(songsManager);
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

                case 13:
                    try {
                        songsManager = serialization.deSerialization();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    break;

                case 14: // show friends Activity
//                    GUI.showFriendsActivity(songsManager.getPlayListManager().getFriendsActivity());
//                    Iterator it = songsManager.getPlayListManager().getFriendsActivity().entrySet().iterator();
//                    while (it.hasNext()){
//                        HashMap.Entry entry  = (HashMap.Entry)it.next();
//                        System.out.println("\nsong: "+(Song)entry.getValue()+"\nhost name: "+(String) entry.getKey());
//                    }
                    break;

                case 15: // search
//                    String key = GUI.getSearchKey();
                    String key = "0";
                    ArrayList<Object> results = songsManager.search(key.toLowerCase());
                    for (int index = 0; index < results.size(); index++)
                        System.out.println(results.get(index));
                    break;

                case 16: // set username
                    userName = gui.getUserName();
                    break;



            }


        } while (true);


    }

}




//        assert (songsManager.getAllSongs().size() == 4);

