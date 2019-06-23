import javax.swing.*;
import java.awt.*;

public class Frame {
    JFrame frame;
    JTextField textField;
    JButton button;

    public Frame() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(900, 556));
        frame.setVisible(true);
        frame.getContentPane().setBackground(Color.BLACK);
    }

    public static void main(String[] args) {
        new Frame();
    }

}

