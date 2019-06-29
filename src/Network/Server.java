package Network;
import Logic.MP3Player;
import Logic.PlayList;
import Logic.Song;
import Logic.SongsManager;
import javazoom.jl.decoder.JavaLayerException;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class Server {
    private Song friendSong;
    private InetAddress inetAddress;

    public Server(int port) throws IOException {
        System.out.println("                    im in server");

        ServerSocket server = new ServerSocket(port);

        System.out.println("waiting for client");
        Socket socket = server.accept();
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));

        ObjectInputStream objectInputStream = new ObjectInputStream(in);

        try {
            friendSong = (Song) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("song received: ");

        inetAddress = server.getInetAddress();

        server.close();
        socket.close();
        in.close();

    }

    public Song getFriendSong() {
        return friendSong;
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }
}


//    public static void main(String[] args) {
//        Server server = null;
//        try {
//            server = new Server(12345);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//
//        MP3Player mp3Player = new MP3Player();
//        ArrayList<Song> songs = new ArrayList<>();
//        songs.add(server.getFriendSong());
//
//        try {
//            mp3Player.setSong(server.getFriendSong(),songs);
//            mp3Player.playSong();
//        } catch (JavaLayerException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//
//    }
