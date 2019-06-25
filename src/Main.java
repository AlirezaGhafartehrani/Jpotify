import Logic.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JavaLayerException, InterruptedException {
        String userName = " ";

//        Song s = new Song("D:\\Codes\\AP\\JPotify\\src\\1.mp3");

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
    }
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

}



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
