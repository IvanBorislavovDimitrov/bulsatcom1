import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class homePage {
    private JButton channelsButton;
    private JPanel homePanel;
    private static JFrame homeFrame;



    public homePage() {
        homeFrame = new JFrame();
        channelsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                channels channelFrame  = new channels();
            }

        });
    }

    public static void main(String[] args) {
        JFrame homeFrame = new JFrame("Home Page");
        homeFrame.setContentPane(new homePage().homePanel);
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        homeFrame.pack();
        homeFrame.setVisible(true);
    }
}
