package GUI;

import GUI.GUI;
import DataBase.StaticControlVariables;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;


public class Frame {
    
   
    
    private JFrame frame;
    private ArrayList<Object> centerPanelArrayList;
    private ArrayList<Object> eastPanelArrayList;
    private ArrayList<Object> northPanelArrayList;
    private ArrayList<Object> southPanelArrayList;
    private ArrayList<Object> leftPanelArrayList;


    public Frame(GUI gui) {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 556));
        frame.setLayout (new BorderLayout());
        frame.setLocation (250,100);

        //left panel
        JPanel leftPanel = new JPanel(new FlowLayout());
        Dimension leftPanelDim = new Dimension(150, 500);
        leftPanel.setPreferredSize(leftPanelDim);

        DefaultListModel listModelMenu = new DefaultListModel();
        listModelMenu.addElement(" Home");
        listModelMenu.addElement(" Browse");
        listModelMenu.addElement(" Radio");
        JList menuList = new JList(listModelMenu);

        DefaultListModel listModelLibrary = new DefaultListModel();
        listModelLibrary.addElement("  Music");
        listModelLibrary.addElement("  Album");
        listModelLibrary.addElement("  Artist");
        JList libraryList = new JList(listModelLibrary);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);


        DefaultListModel listModelplaylist = new DefaultListModel();
        JList playlistList = new JList(listModelplaylist);

        Dimension menuListDim = new Dimension(150, 70);
        Dimension libListdim = new Dimension(150, 70);
        Dimension playlisListdim = new Dimension(150, 230);
        menuList.setPreferredSize(menuListDim);
        libraryList.setPreferredSize(libListdim);
        playlistList.setPreferredSize(playlisListdim);

        JLabel Menu = new JLabel();
        JLabel library = new JLabel(" YOUR LIBRARY");
        Dimension LabelSize = new Dimension(150, 15);
        library.setPreferredSize(LabelSize);

        JLabel playlistListLabel = new JLabel(" PLAYLIST");
        playlistListLabel.setPreferredSize(LabelSize);

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
            BufferedImage fimg = new BufferedImage(150, 150, img.getType());
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


        leftPanel.setBackground(Color.LIGHT_GRAY);
        frame.add(leftPanel, BorderLayout.WEST);


        //Center panel
        JPanel centerPanel = new JPanel(new FlowLayout());
        centerPanel.setBackground(Color.BLACK);
        frame.add(centerPanel, BorderLayout.CENTER);

        //East panel
        JPanel eastPanel = new JPanel(new FlowLayout());
        eastPanel.setPreferredSize(leftPanelDim);
        eastPanel.setBackground(Color.GRAY);
        JLabel fActivity = new JLabel(" Friend Activity");
        fActivity.setPreferredSize(LabelSize);
        fActivity.setForeground(Color.WHITE);
        eastPanel.add(fActivity);
        frame.add(eastPanel, BorderLayout.EAST);

        /////////South panel

        //Player Panel in southpanel
        JPanel southPanel = new JPanel(new BorderLayout());
        JPanel playerPanel = new JPanel(new FlowLayout());
        Dimension playerPanelSize = new Dimension(150, 100);

       JButton play = new JButton();
        Dimension playerButtonSize = new Dimension(44, 44);
        play.setPreferredSize(playerButtonSize);
        play.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\pause.png"));//didnt work?!
            play.setIcon(new ImageIcon(img));
       
        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        
             play.addActionListener((new java.awt.event.ActionListener() {
            @Override      
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("salam");
               if(StaticControlVariables.hasSong==false){
                   System.out.println("No Song to play or pause");
                   return;
               } 
               if(StaticControlVariables.isPlayed){
                   StaticControlVariables.isPlayed=false;
                   gui.setAction(2);
                   
               }else{
                   StaticControlVariables.isPlayed=true;
                   gui.setAction(3);
               }
               
            }
        }));

        JButton pNext = new JButton();
        pNext.setPreferredSize(playerButtonSize);
        pNext.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Desktop\\next.png"));//didnt work?!
            pNext.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Image not found");
        }
     pNext.addActionListener((new java.awt.event.ActionListener() {
            @Override      
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                System.out.println("salam pnext");
                gui.setAction(4);
            }
        }));


        JButton privios = new JButton();
        privios.setPreferredSize(playerButtonSize);
        privios.setBackground(Color.lightGray);
        try {
            Image img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Pictures\\LifeFrame\\2.jpg"));//didnt work?!
            privios.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        privios.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("privious");
                gui.setAction(5);
            }
        }));

        playerPanel.add(privios);
        playerPanel.add(play);
        playerPanel.add(pNext);
        playerPanel.setBackground(Color.lightGray);
        southPanel.add(playerPanel, BorderLayout.WEST);

        //Song slider
        JSlider musicSlider = new JSlider();
        Dimension musicSliderDim = new Dimension(600 ,30);
        southPanel.add(musicSlider, BorderLayout.CENTER);

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
        Dimension addMtoL = new Dimension(150, 30);
        add_music_to_libraryLabel.setPreferredSize(addMtoL);
        northPanel.add(add_music_to_libraryLabel);
        JButton add = new JButton(" + ");
        Dimension addLabel = new Dimension(60, 30);
        add.setPreferredSize(addLabel);
        add.setBackground(Color.lightGray);
        add.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // get song
//                gui.setNewSongPath();
                System.out.println("add");
                gui.setAction(6);
            }
        }));
        northPanel.add(add);
        northPanel.setBackground(Color.lightGray);
        frame.add(northPanel, BorderLayout.NORTH);
        JButton signIn = new JButton("Sign in");
        signIn.setPreferredSize(addMtoL);
        signIn.setBackground(Color.lightGray);
        signIn.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                gui.setUserName();
                System.out.println("sign in");
                gui.setAction(16);
            }
        }));
        northPanel.add(signIn);
        JLabel profileIcon = new JLabel();
        try {
            BufferedImage img = ImageIO.read(new File("C:\\Users\\Alireza Tehrani\\Pictures\\LifeFrame\\3.jpg"));
            BufferedImage fimg = new BufferedImage(30, 30, img.getType());
            profileIcon.setIcon(new ImageIcon(fimg));

        } catch (Exception ex) {
            System.out.println("Image not found");
        }
        profileIcon.setPreferredSize(addLabel);
        northPanel.add(profileIcon);
        JCheckBox darkMode = new JCheckBox("Dark Mode");
        darkMode.setPreferredSize(addMtoL);
        darkMode.setBackground(Color.lightGray);
        northPanel.add(darkMode);
        
        //dark mode listener
        darkMode.addActionListener((new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    if(StaticControlVariables.isDarkMode==false){
                         System.out.println("changed to dark mode");
                         StaticControlVariables.isDarkMode=true;
                    }else{
                         System.out.println("changed to light mode");
                         StaticControlVariables.isDarkMode=false;
                    }
                   
            }
        }));
        

        ///////////


        frame.setVisible(true);

    }

//    public static void main(String[] args) {
//        Frame mainGraph = new Frame();
//    }

}