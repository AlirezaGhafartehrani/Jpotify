import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public Server(int port) {
        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";

            while (!line.equals("Over"))
            {
                try
                {
                    line = in.readUTF();
                    System.out.println(line);

                }
                catch(IOException i)
                {
                    System.out.println(i);
                }
            }


            Object obj = null;
            ObjectInputStream objectInputStream = new ObjectInputStream(in);
            try {
                obj = objectInputStream.readObject();
                System.out.println("received:\n" + obj);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


            System.out.println("Closing connection");



            // close connection
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

//    public static void main(String args[]) {
//        Server server = new Server(5000);
//    }
}

