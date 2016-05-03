package CW4;

import javax.swing.*;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public interface IModel {
    void Move(boolean player, int yax, int xax);


    Piece[][] getPieces();


    void updateScores();

    int getWhiteScore();

    int getBlackScore();

    boolean getActivePlayer();

    void aimove(boolean player);
}
