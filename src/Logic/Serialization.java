package Logic;

import sun.security.jgss.spnego.SpNegoContext;

import java.io.*;

public class Serialization {
    private final String filePath = "D:\\Codes\\AP\\JPotify\\src\\DataBase\\userData\\songsmanager";

    public void serialization(SongsManager songsManager) throws IOException {
        FileOutputStream file = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(songsManager);

        out.close();
        file.close();
    }

    public SongsManager deSerialization() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filePath);
        ObjectInputStream in = new ObjectInputStream(file);

        // Method for deserialization of object
        SongsManager sm = (SongsManager) in.readObject();

        in.close();
        file.close();

        return sm;
    }


    public SongsManager isThereAnyRecord() {
        try {
            return deSerialization();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
