import Logic.*;
import javazoom.jl.decoder.JavaLayerException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws FileNotFoundException, JavaLayerException, InterruptedException {
        String userName = " ";

        Song s1 = new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\Songs\\01.mp3");
        AudioPlayer audioPlayer = new AudioPlayer();

        audioPlayer.setSong(s1);
        audioPlayer.playSong();

    }
