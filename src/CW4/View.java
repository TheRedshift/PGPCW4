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

        JButton greedyWhite = new JButton("Greedy AI white");
        whitepane.add(greedyWhite, BorderLayout.PAGE_END);

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

        JButton greedyBlack = new JButton("Greedy AI black");
        blackpane.add(greedyBlack, BorderLayout.PAGE_END);

        JPanel blackSpace = new JPanel();
        blackSpace.setLayout(new GridBagLayout());

        blackSpace = makeBlack();

        blackSpace.revalidate();
        blackSpace.setVisible(true);
        blackpane.add(blackSpace, BorderLayout.CENTER);


        JLabel scoreLabel = new JLabel("The game is currently tied 0-0", SwingConstants.CENTER);

        JLabel turnLabel = new JLabel("It is currently white's turn", SwingConstants.CENTER);


        desktop.add(whitePlayer);
        desktop.add(blackPlayer);


        frame.add(scoreLabel, BorderLayout.PAGE_START);
        frame.add(desktop, BorderLayout.CENTER);
        frame.add(turnLabel, BorderLayout.PAGE_END);
        frame.setSize(1375, 750);


        frame.setVisible(true);
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
                    trialPiece.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            c.move(true, trialPiece.getMyX(),trialPiece.getMyY());
                        }
                    });
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
                    tempPiece.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            c.move(false, 7 - tempPiece.getMyX(),7 -tempPiece.getMyY());
                        }
                    });
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

        Piece[][] Pieces = c.getPieces();

        JPanel whiteSpace = makeWhite();

        whiteSpace.setVisible(true);

        JPanel blackSpace = makeBlack();

        blackSpace.setVisible(true);


        whitepane.remove(1);
        whitepane.add(whiteSpace);

        blackpane.remove(1);
        blackpane.add(blackSpace);

        whitepane.getParent().revalidate();
        whitepane.getParent().repaint();

        blackpane.getParent().revalidate();
        blackpane.getParent().repaint();






    }


}





