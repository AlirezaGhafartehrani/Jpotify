package Logic;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Serialization {
    private final String filePath = "I:\\Files\\Amirkabir University of Technology\\4th semester\\Advance Programming\\Project\\src\\DataBase\\userData";

    public void serialization(SongsManager songsManager) throws IOException {
//        Files.deleteIfExists(Paths.get(filePath + "\\songsmanager"));
        FileOutputStream file = new FileOutputStream(filePath + "\\songsmanager");
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(songsManager);

        out.close();
        file.close();
    }

    public SongsManager deSerialization() throws IOException, ClassNotFoundException {
        FileInputStream file = new FileInputStream(filePath + "\\songsmanager");
        ObjectInputStream in = new ObjectInputStream(file);

        SongsManager sm = (SongsManager) in.readObject();

        in.close();
        file.close();

        return sm;
    }

    public void updateRecord(SongsManager songsManager) throws IOException {
        serialization(songsManager);
    }

    public SongsManager isThereAnyRecord() {
        try {
            return deSerialization();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }


    public void serialization(Song lastPlayedSong, String filePath) throws IOException {
        FileOutputStream file = new FileOutputStream(filePath);
        ObjectOutputStream out = new ObjectOutputStream(file);

        out.writeObject(lastPlayedSong);

        out.close();
        file.close();
    }
}
