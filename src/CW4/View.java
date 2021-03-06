package CW4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class View implements IView {


    IControllerFromView c;

    public View(IControllerFromView c) {
        this.c = c;
    }


    static JLabel turnLabel;

    static boolean isWhitePlaying;

    static Container blackpane;

    static Container whitepane;

    private static Color myGreen = new Color(0x5CFF49);

    private static BufferedImage noCircle;

    private static BufferedImage whiteCircle;

    private static BufferedImage blackCircle;

    static {
        try {
            noCircle = ImageIO.read(View.class.getResource("/CW4/noCircle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            whiteCircle = ImageIO.read(View.class.getResource("/CW4/whiteCircle.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        try {
            blackCircle = ImageIO.read(View.class.getResource("/CW4/blackCircle.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static JFrame frame;


    public void create() {

        isWhitePlaying = c.getActivePlayer();

        frame = new JFrame("Reversii");
        frame.setLayout(new BorderLayout());
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        JDesktopPane desktop = new JDesktopPane();


        JInternalFrame whitePlayer = new JInternalFrame("White");
        whitePlayer.setBounds(25, 50, 200, 100);
        whitePlayer.setSize(600, 600);
        whitePlayer.setVisible(true);

        whitepane = whitePlayer.getContentPane();
        whitepane.setLayout(new BorderLayout());


        JButton whitegreedy = makeWhiteGreedy();
        whitepane.add(whitegreedy, BorderLayout.PAGE_END);

        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;

        JPanel whiteSpace = new JPanel();
        whiteSpace.setLayout(new GridBagLayout());




        whiteSpace = makeWhite();
        whiteSpace.setVisible(true);
        whitepane.add(whiteSpace, BorderLayout.CENTER);




        JInternalFrame blackPlayer = new JInternalFrame("Black");
        blackPlayer.setBounds(725, 50, 200, 100);
        blackPlayer.setSize(600, 600);
        blackPlayer.setVisible(true);


        blackpane = blackPlayer.getContentPane();
        blackpane.setLayout(new BorderLayout());





        JPanel blackSpace = new JPanel();
        blackSpace.setLayout(new GridBagLayout());

        blackSpace = makeBlack();

        blackSpace.revalidate();
        blackSpace.setVisible(true);

        JButton greedyblack = makeBlackGreedy();
        blackpane.add(greedyblack, BorderLayout.PAGE_END);
        blackpane.add(blackSpace, BorderLayout.CENTER);



        int whiteScore = c.getWhiteScore();
        int blackScore = c.getBlackScore();



        String player = "";

        if (isWhitePlaying)
        {
            player = "White";
        }

        else if (!isWhitePlaying)
        {
            player = "Black";
        }



        JLabel scoreLabel = new JLabel("The score is currently "+whiteScore +" - "+blackScore, SwingConstants.CENTER);

        turnLabel = new JLabel("It is currently" +player+"'s turn", SwingConstants.CENTER);


        desktop.add(whitePlayer);
        desktop.add(blackPlayer);


        frame.add(scoreLabel, BorderLayout.PAGE_START);
        frame.add(desktop, BorderLayout.CENTER);
        frame.add(turnLabel, BorderLayout.PAGE_END);
        frame.setSize(1375, 750);


        frame.setVisible(true);
    }

    public JButton makeWhiteGreedy() {

        JButton greedyWhite = new JButton("Greedy AI white");
        if (isWhitePlaying){
            greedyWhite.addActionListener(new ActionListener()
            {

                public void actionPerformed(ActionEvent e)
                {

                    c.aimove(true);
                    System.out.println("Greedy white pressed");
                }
            }
            );
        }

        return greedyWhite;
    }


    public JButton makeBlackGreedy(){

        JButton greedyBlack = new JButton("Greedy AI black");
        if (!isWhitePlaying){
            greedyBlack.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {

                    c.aimove(false);
                    System.out.println("Greedy black pressed");
                }
            }
            );
        }

        return greedyBlack;

    }


    public JPanel makeWhite() {


        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;

        Piece[][] Pieces = c.getPieces();
        gBC.gridy = gBC.gridx = 0;
        gBC.weightx = gBC.weighty = 1.0;

        for (Piece[] f : Pieces) {
            gBC.gridx = f[0].getMyX();

            for (Piece l : f) {
                gBC.gridy = l.getMyY();
                Piece trialPiece = new Piece(gBC.gridx, gBC.gridy, l.isOwned());
                trialPiece.setBackground(myGreen);
                if (l.isOwned() == 0) {
                    trialPiece.setIcon(new ImageIcon(noCircle));
                    if (isWhitePlaying) {
                        trialPiece.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                c.move(true, trialPiece.getMyX(), trialPiece.getMyY());
                            }
                        });
                    }
                    tempPanel.add(trialPiece, gBC);

                } else if (l.isOwned() == 1) {
                    trialPiece.setIcon(new ImageIcon(whiteCircle));
                    tempPanel.add(trialPiece, gBC);

                } else if (l.isOwned() == -1) {
                    trialPiece.setIcon(new ImageIcon(blackCircle));
                    tempPanel.add(trialPiece, gBC);
                }

            }


        }
        return tempPanel;
    }

    public JPanel makeBlack() {

        JPanel tempPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gBC = new GridBagConstraints();
        gBC.fill = GridBagConstraints.BOTH;

        Piece[][] Pieces = c.getPieces();
        gBC.gridy = gBC.gridx = 0;
        gBC.weightx = gBC.weighty = 1.0;

        for (Piece[] f : Pieces) {
            gBC.gridx = 7 - f[0].getMyX();

            for (Piece l : f) {
                gBC.gridy = 7 -l.getMyY();
                Piece tempPiece = new Piece(gBC.gridx, gBC.gridy, l.isOwned());
                tempPiece.setBackground(myGreen);
                if (l.isOwned() == 0) {
                    tempPiece.setIcon(new ImageIcon(noCircle));
                    if (!isWhitePlaying) {
                        tempPiece.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                c.move(false, 7 - tempPiece.getMyX(), 7 - tempPiece.getMyY());
                            }
                        });
                    }
                    tempPanel.add(tempPiece, gBC);

                } else if (l.isOwned() == 1) {
                    tempPiece.setIcon(new ImageIcon(whiteCircle));
                    tempPanel.add(tempPiece, gBC);

                } else if (l.isOwned() == -1) {
                    tempPiece.setIcon(new ImageIcon(blackCircle));
                    tempPanel.add(tempPiece, gBC);
                }

            }


        }

        return tempPanel;

    }


    public void updateView() {



        int whiteScore = c.getWhiteScore();
        int blackScore = c.getBlackScore();

        String player = "";

        isWhitePlaying = c.getActivePlayer();

        //System.out.println(isWhitePlaying);
        if (isWhitePlaying)
        {
            player = "White";

        }

        else if (!isWhitePlaying)
        {
            player = "Black";
        }

        //System.out.println(player);

        if (frame.getContentPane().getComponent(0) instanceof JLabel){

            ((JLabel) frame.getContentPane().getComponent(0)).setText("The score is currently " + whiteScore +" - "+blackScore);
        }

        turnLabel.setText("It is currently  " + player+"'s turn");






        JPanel whiteSpace = makeWhite();

        whiteSpace.setVisible(true);

        JPanel blackSpace = makeBlack();

        blackSpace.setVisible(true);


        JButton aiWhite = makeWhiteGreedy();

        JButton aiBlack = makeBlackGreedy();

        whitepane.remove(1);
        whitepane.remove(0);

        whitepane.add(aiWhite, BorderLayout.PAGE_END);
        whitepane.add(whiteSpace, BorderLayout.CENTER);


        blackpane.remove(1);
        blackpane.remove(0);

        blackpane.add(aiBlack, BorderLayout.PAGE_END);
        blackpane.add(blackSpace, BorderLayout.CENTER);

        whitepane.getParent().revalidate();
        whitepane.getParent().repaint();

        blackpane.getParent().revalidate();
        blackpane.getParent().repaint();


        System.out.println(isWhitePlaying);





    }


    public void gameIsOver()

    {

        int whiteScore = c.getWhiteScore();
        int blackScore = c.getBlackScore();
        int diff = 0;
        String winner ="Error";

        if (whiteScore > blackScore){

            winner = "White";
            diff = whiteScore - blackScore;
        }
        else if (whiteScore < blackScore){

            winner = "Black";
            diff = blackScore - whiteScore;
        }


        updateView();


        JOptionPane.showMessageDialog(whitepane, "You scored "+whiteScore+"points.", "EASY GAME : " + "Game over", JOptionPane.INFORMATION_MESSAGE);

        JOptionPane.showMessageDialog(blackpane, "You scored "+blackScore+"points.", "EASY LIFE: " + "Game over", JOptionPane.INFORMATION_MESSAGE);


        JOptionPane.showMessageDialog(null, "The game is over - " + winner +"wins by "+ diff, "Not even trying: " + "Game over!", JOptionPane.INFORMATION_MESSAGE);





    }


}





