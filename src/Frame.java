
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Frame {
    private JFrame frame;

    public Frame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 556));
        frame.setLayout (new BorderLayout());
        frame.setLocation (250,100);

        //left panel
        JPanel leftPanel = new JPanel(new FlowLayout());
        Dimension leftPanelDim = new Dimension(150, 500);
        leftPanel.setPreferredSize(leftPanelDim);

        Font narrowFont = new Font ("arial",Font.BOLD,8);
        DefaultListModel listModelMenu = new DefaultListModel();
        listModelMenu.addElement(" Home");
        listModelMenu.addElement(" Browse");
        listModelMenu.addElement(" Radio");
        JList menuList = new JList(listModelMenu);
        menuList.setBackground(Color.DARK_GRAY);
        menuList.setForeground(Color.WHITE);
        menuList.setFont(narrowFont);

        DefaultListModel listModelLibrary = new DefaultListModel();
        listModelLibrary.addElement("  Music");
        listModelLibrary.addElement("  Album");
        listModelLibrary.addElement("  Artist");
        JList libraryList = new JList(listModelLibrary);
        libraryList.setBackground(Color.DARK_GRAY);
        libraryList.setForeground(Color.WHITE);
        libraryList.setFont(narrowFont);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        DefaultListModel listModelplaylist = new DefaultListModel();
        JList playlistList = new JList(listModelplaylist);
        playlistList.setBackground(Color.DARK_GRAY);

        Dimension menuListDim = new Dimension(150, 70);
        Dimension libListdim = new Dimension(150, 70);
        Dimension playlisListdim = new Dimension(150, 100);
        menuList.setPreferredSize(menuListDim);
        libraryList.setPreferredSize(libListdim);
        playlistList.setPreferredSize(playlisListdim);

        Font boldFont = new Font ("arial",Font.BOLD,10);
        JLabel Menu = new JLabel();
        JLabel library = new JLabel(" YOUR LIBRARY");
        Dimension LabelSize = new Dimension(150, 15);
        library.setPreferredSize(LabelSize);
        library.setFont (boldFont);
        library.setForeground(Color.WHITE);

        JLabel playlistListLabel = new JLabel(" PLAYLIST");
        playlistListLabel.setPreferredSize(LabelSize);
        playlistListLabel.setFont (boldFont);
        playlistListLabel.setForeground(Color.WHITE);


        leftPanel.add(Menu);
        leftPanel.add(menuList);
        leftPanel.add(library);
        leftPanel.add(libraryList);
        leftPanel.add(scrollPane);
        leftPanel.add(playlistListLabel);
        leftPanel.add(playlistList);

        JButton artworkP = new JButton();
        artworkP.setBackground(Color.lightGray);
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Pictures\\LifeFrame\\1.jpg"));
            BufferedImage fimg = new BufferedImage(50,50, img.getType());
            artworkP.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        Dimension artworkMaxDim = new Dimension(150, 150);
        artworkP.setPreferredSize(artworkMaxDim);
        leftPanel.add(artworkP);

        JLabel musicName = new JLabel(" Music Name");
        JLabel artist = new JLabel(" Artist Name");
        musicName.setPreferredSize(LabelSize);
        artist.setPreferredSize(LabelSize);
        leftPanel.add(musicName);
        leftPanel.add(artist);
        musicName.setForeground(Color.WHITE);
        musicName.setFont(boldFont);
        artist.setForeground(Color.WHITE);
        artist.setFont(boldFont);


        leftPanel.setBackground(Color.BLACK);
        frame.add(leftPanel, BorderLayout.WEST);


        //Center panel
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.BLACK);
        frame.add(centerPanel, BorderLayout.CENTER);

        //East panel
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanel.setPreferredSize(leftPanelDim);
        eastPanel.setBackground(Color.DARK_GRAY);
        JLabel fActivity = new JLabel(" Friend Activity");
        fActivity.setForeground(Color.WHITE);
        fActivity.setFont(boldFont);
        eastPanel.add(fActivity);
        frame.add(eastPanel, BorderLayout.EAST);

        //South panel

        //Player Panel in southpanel
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new FlowLayout());
        Dimension playerPanelSize = new Dimension(150, 40);
        southPanel.setPreferredSize(playerPanelSize);

        JButton play = new JButton();
        Dimension playerButtonSize = new Dimension(30, 30);
        play.setPreferredSize(playerButtonSize);
        play.setBackground(Color.BLACK);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\pause.png"));
            play.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton pNext = new JButton();
        pNext.setPreferredSize(playerButtonSize);
        pNext.setBackground(Color.BLACK);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\next.png"));
            pNext.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        JButton previous = new JButton();
        previous.setPreferredSize(playerButtonSize);
        previous.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\previous.png"));
            previous.setIcon(new ImageIcon(img));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }

        playerPanel.add(previous);
        playerPanel.add(play);
        playerPanel.add(pNext);
        playerPanel.setBackground(Color.lightGray);
        southPanel.add(playerPanel, BorderLayout.WEST);

        //Song slider
        JSlider musicSlider = new JSlider();
        Dimension musicSliderDim = new Dimension(900 ,30);
        southPanel.add(musicSlider, BorderLayout.CENTER);
        musicSlider.setPreferredSize(musicSliderDim);

        //Volume slider
        //JPanel speakers = new JPanel(new FlowLayout());
        //speakers.setPreferredSize(playerPanelSize);
        //JLabel volume = new JLabel("Volume");
        //Dimension voloumLabelDim = new Dimension(50,30);
        //volume.setPreferredSize(voloumLabelDim);
        JSlider voloum = new JSlider();
        Dimension voloumDim = new Dimension(150,30);
        voloum.setPreferredSize(voloumDim);
//        speakers.add(volume);
//        speakers.add(voloum);
        southPanel.add(voloum, BorderLayout.EAST);

        Dimension southPanelDim = new Dimension(900 , 100);
        southPanel.setBackground(Color.lightGray);
        frame.add(southPanel, BorderLayout.SOUTH);


        // North
        JPanel northPanel = new JPanel(new FlowLayout());
        Dimension northPanelDim = new Dimension(900 , 30);
        northPanel.setPreferredSize(northPanelDim);
        JLabel add_music_to_libraryLabel = new JLabel("Add Music To Library");
        add_music_to_libraryLabel.setForeground(Color.WHITE);
        add_music_to_libraryLabel.setFont(boldFont);
        Dimension addMtoL = new Dimension(150, 30);
        add_music_to_libraryLabel.setPreferredSize(addMtoL);
        northPanel.add(add_music_to_libraryLabel);
        JButton add = new JButton(" + ");
        Dimension addLabel = new Dimension(50, 30);
        add.setPreferredSize(addLabel);
        add.setBackground(Color.DARK_GRAY);
        northPanel.add(add);
        northPanel.setBackground(Color.DARK_GRAY);
        frame.add(northPanel, BorderLayout.NORTH);
        JButton signIn = new JButton("Sign in");
        signIn.setForeground(Color.WHITE);
        signIn.setFont(boldFont);
        signIn.setPreferredSize(addMtoL);
        signIn.setBackground(Color.DARK_GRAY);
        northPanel.add(signIn);
        JLabel profileIcon = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\pause.png"));
            BufferedImage fimg = new BufferedImage(30, 30, img.getType());
            profileIcon.setIcon(new ImageIcon(fimg));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        profileIcon.setPreferredSize(addLabel);
        northPanel.add(profileIcon);
        JCheckBox darkMode = new JCheckBox("Dark Mode");
        darkMode.setPreferredSize(addMtoL);
        darkMode.setBackground(Color.DARK_GRAY);
        northPanel.add(darkMode);


        ///////////


        frame.setVisible(true);

    }

    public static void main(String[] args) {
        Frame mainGraph = new Frame();
    }
}