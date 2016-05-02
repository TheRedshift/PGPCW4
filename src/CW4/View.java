package CW4;

import CW4.IControllerFromView;
import CW4.IView;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class View implements IView {

    IControllerFromView c;
    public View(IControllerFromView c)
    {
        this.c = c;
    }

    int [][] board;
    JFrame frame;

    public void create() throws IOException {

        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(frame.getSize());
        frame.setResizable(false);

        Container pane = frame.getContentPane();

        pane.setLayout(new BorderLayout());





        JLabel Score = new JLabel("The current score is White:0 Black:0", JLabel.CENTER);
        JLabel turnNotify = new JLabel("It is currently White's turn.", JLabel.CENTER);

        pane.add(Score, BorderLayout.PAGE_START);

        pane.add(turnNotify, BorderLayout.PAGE_END);

        JDesktopPane desktop = new JDesktopPane();
        JInternalFrame whitePlayer = new JInternalFrame("White Player", true, false, false, true);
        JInternalFrame blackPlayer = new JInternalFrame("Black Player", true, false, false, true);


        Container whitePane = whitePlayer.getContentPane();
        Container blackPane = blackPlayer.getContentPane();



        whitePane.setLayout(new BorderLayout());
        blackPane.setLayout(new BorderLayout());


        JPanel whiteBoard = new JPanel(new GridBagLayout());
        whiteBoard.setSize(600,600);


        JPanel blackBoard = new JPanel(new GridBagLayout());
        blackBoard.setSize(600,600);


        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;


        BufferedImage white = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("CW4/blackCircle.png"));

        BufferedImage blank = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("CW4/noCircle.png"));

        BufferedImage black = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("CW4/whiteCircle.jpg"));

        //BufferedImage white = ImageIO.read(new File("\\Users\\Rahul Soni\\IdeaProjects\\MVC\\src\\CW4\\whiteCircle.jpg"));

        //BufferedImage black = ImageIO.read(new File("\\Users\\Rahul Soni\\IdeaProjects\\MVC\\src\\CW4\\blackCircle.png"));

        white.getScaledInstance(1, 1, Image.SCALE_DEFAULT);
        black.getScaledInstance(1, 1, Image.SCALE_DEFAULT);


        gBC.gridx = 0;
        gBC.gridy = 0;

        Color newGreen = new Color(0x5CFF49);
        gBC.weightx = gBC.weighty = 1.0;


        int [][] board = c.getFirstBoard();

        /*
        for (int[] row : board)
        {
            System.out.println(Arrays.toString(row));
        }

        */


        for (int i = 0; i < 8; ++i) // x value
        {
            gBC.gridx = i;
            for (int b = 0; b < 8; ++b)  // y value
            {
                gBC.gridy = b;

                if (board[i][b] == 0) {


                    JButton y = new JButton(new ImageIcon(blank));
                    int finalB = b;
                    int finalI = i;
                    y.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            c.move(finalI, finalB);
                        }
                    });
                    y.setBackground(newGreen);

                    whiteBoard.add(y, gBC);

                } else if (board[i][b] == 1) {

                    JButton y = new JButton(new ImageIcon(black));

                    y.setBackground(newGreen);

                    whiteBoard.add(y, gBC);

                } else if (board[i][b] == -1) {

                    JButton y = new JButton(new ImageIcon(white));

                    y.setBackground(newGreen);

                    whiteBoard.add(y, gBC);
                }
            }
        }

        /*

        for (int i = 0; i < 8; ++i) // x value
        {
            gBC.gridx = i;
            for (int b = 0; b < 8; ++b)  // y value
            {
                gBC.gridy = b;

                JButton y = new JButton(new ImageIcon(white));
                int finalB = b;
                int finalI = i;
                y.addActionListener(new ActionListener()
                {
                    public void actionPerformed(ActionEvent e)
                    {
                        c.move(finalI, finalB);
                    }
                });
                y.setBackground(Color.GREEN);

                whiteBoard.add(y, gBC);

            }
        }

        */


        JLabel whiteScore = new JLabel("Current score - 0");
        whitePane.add(whiteScore, BorderLayout.PAGE_START);

        whitePane.add(whiteBoard, BorderLayout.CENTER);
        JButton greedyButtonWhite = new JButton("Greedy play white");

        whitePane.add(greedyButtonWhite,BorderLayout.PAGE_END);



        for (int i = 0; i < 8; ++i) // x value
        {
            gBC.gridx = i;
            for (int b = 0; b < 8; ++b)  // y value
            {
                gBC.gridy = b;

                if ( board[7 -i][b] == 0) {


                    JButton y = new JButton(new ImageIcon(blank));
                    int finalB = b;
                    int finalI = i;
                    y.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            c.move(finalI, finalB);
                        }
                    });
                    y.setBackground(newGreen);

                    blackBoard.add(y, gBC);

                } else if (board[7 -i][b] == 1) {

                    JButton y = new JButton(new ImageIcon(black));

                    y.setBackground(newGreen);

                    blackBoard.add(y, gBC);

                } else if (board[7 -i][b] == -1) {

                    JButton y = new JButton(new ImageIcon(white));

                    y.setBackground(newGreen);

                    blackBoard.add(y, gBC);
                }
            }
        }


        JLabel blackScore = new JLabel("Current score - 0");
        blackPane.add(blackScore, BorderLayout.PAGE_START);


        blackPane.add(blackBoard, BorderLayout.CENTER);
        JButton greedyButtonBlack = new JButton("Greedy play black");

        blackPane.add(greedyButtonBlack,BorderLayout.PAGE_END);

        desktop.add(whitePlayer);
        desktop.add(blackPlayer);



        whitePlayer.setBounds(25, 25, 200, 100);
        blackPlayer.setBounds(725, 25, 200, 100);

        whitePlayer.setSize(600,600);
        blackPlayer.setSize(600,600);



        whitePlayer.setVisible(true);
        blackPlayer.setVisible(true);



        pane.add(desktop);
        frame.setSize(1375, 700);
        frame.setVisible(true);

    }

    public void displayError(String strError){

        JOptionPane.showMessageDialog(frame, strError);
    }
}
