package Network;

import Logic.Song;

import java.net.*;
import java.io.*;

public class Client {

    public Client(String address, int port, Song lastPlayedSong) throws IOException {
        System.out.println("                    im in client");

        Socket socket = new Socket(address, port);

        DataOutputStream out = new DataOutputStream(socket.getOutputStream());

        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(out);
            lastPlayedSong = NetworkManager.getLastPlayedSong();
            objectOutputStream.writeObject(lastPlayedSong);
        } catch (IOException e) {
            e.printStackTrace();
        }

        socket.close();
        try {
            out.close();
        } catch (IOException ii) {
            System.out.println(ii);
        }
    }

}
//    public static void main(String[] args) throws IOException {
//        IPAddressName ip = new IPAddressName("127.0.0.1");
//        new Client("127.0.0.1", 12345, new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\01.mp3"));
////        new Client(ip.toString(), 123456, new Song("D:\\Codes\\AP\\JPotify\\src\\DataBase\\02.mp3"));
//
//    }


