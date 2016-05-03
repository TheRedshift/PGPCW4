package CW4;

import javax.swing.*;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public interface IModel {
    void Move(boolean player, int yax, int xax);


    int getFirstBlack();

    int getFirstWhite();

    Piece[][] getPieces();

    void setPieces(Piece[][] pieces);

    void updateScores();

    int getWhiteScore();

    int getBlackScore();

    boolean getActivePlayer();

    void aimove(boolean player);
}
