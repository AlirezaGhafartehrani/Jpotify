package GUI;

public class GUI {
    private Frame frame;
    private int action;
    private String newSongPath;
    private String userName;

    public GUI() {
        frame = new Frame(this);
    }

    public void setAction(int action) {
        this.action = action;
//System.out.println("action set: "+action);
    }

    public String getNewSongPath() {
        return newSongPath;
    }

    public void setNewSongPath(String newSongPath) {
        this.newSongPath = newSongPath;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public int getAction() {
        return action;
    }

}


