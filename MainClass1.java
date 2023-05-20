import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.text.Position;
import javax.swing.text.AbstractDocument.Content;

import java.awt.*;
import java.awt.event.*;

public class MainClass1 extends JFrame {
    private JButton startButton;
    private JLabel titleLabel;

    public MainClass1() {
        super("Tetris Start Menu");
        setLayout(new SpringLayout());

        // Create the title label and add it to the top of the frame
        titleLabel = new JLabel("Tetris");
        titleLabel.setFont(new Font("Courier", Font.BOLD, 28));
        titleLabel.setForeground(Color.blue);
        titleLabel.setBackground(Color.CYAN);
        add(titleLabel);

        // Create the start button and add it to the center of the frame
        startButton = new JButton("Start Game");
        
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tetris.main(null);
            }
        });

        add(startButton,SpringLayout.HORIZONTAL_CENTER);
        setSize(400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        JFrame startmenu = new JFrame();
        startmenu.setBackground(Color.BLACK);
        
        startmenu.setSize(600, 300);
        startmenu.setLayout(new BorderLayout(ALLBITS, ABORT));
        JLabel tetrisLabel = new JLabel("Tetris Game");
        tetrisLabel.setPreferredSize(new Dimension(200,160));
        tetrisLabel.setHorizontalAlignment(JLabel.CENTER);
        tetrisLabel.setFont(new Font("Courier", Font.ROMAN_BASELINE, 40));
        tetrisLabel.setForeground(Color.white);
        tetrisLabel.setBackground(Color.black);
        tetrisLabel.setOpaque(true);
        JButton startTetrisgameButton=new JButton("Start Game");
        startTetrisgameButton.setBorderPainted(false);
        startTetrisgameButton.setPreferredSize(new Dimension(400,100));
        startTetrisgameButton.setForeground(Color.white );
        startTetrisgameButton.setBackground(Color.black);
        startTetrisgameButton.setBorder(null);
        startTetrisgameButton.setFocusPainted(false);
        startTetrisgameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        startmenu.add(tetrisLabel, BorderLayout.NORTH);
        startmenu.add(startTetrisgameButton, BorderLayout.SOUTH);

        startTetrisgameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                tetris.main(null);
            }
        }); 

        startmenu.setVisible(true);
    }
}
