package Network;

import Logic.Song;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;

public class NetworkManager extends Thread {
    private ArrayList<InetAddress> ipAddresses;
    private Thread sendThread;
    private Thread receiverThread;
    private HashMap<String, Song> friendsActivity;
    private static Song lastPlayedSong;

    public NetworkManager(Song song) throws UnknownHostException {
        InetAddress inetAddress = InetAddress.getLocalHost();
        lastPlayedSong = song;
        ipAddresses = new ArrayList<>();
        friendsActivity = new HashMap<>();


        // todo delete
//        try {
//            ipAddresses.add(new IPAddressName("127.0.0.1"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        receiverThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Server server;
                    do {
                        server = new Server(12345);
                        if (friendsActivity.containsKey(server.getInetAddress().getHostName()))
                            friendsActivity.replace(server.getInetAddress().getHostName(), server.getFriendSong());
                        else
                            friendsActivity.put(server.getInetAddress().getHostName(), server.getFriendSong());
                    } while (true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sendThread = new Thread(new Runnable() {
            @Override
            public void run() {
//                for ()
                try {
                    do {
                        Thread.sleep(10000);
                        // todo for
                        new Client("127.0.0.1", 12345, lastPlayedSong);
                    } while (true);
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public static Song getLastPlayedSong() {
        return lastPlayedSong;
    }

    public Thread getSendThred() {
        return sendThread;
    }

    public Thread getReceiverThred() {
        return receiverThread;
    }

    public HashMap<String, Song> getFriendsActivity() {
        return friendsActivity;
    }

    public void updateLastPlayed(Song song) {
        lastPlayedSong = song;
    }

    public void addIPAddress(InetAddress inetAddress) {
        ipAddresses.add(inetAddress);
    }

}
