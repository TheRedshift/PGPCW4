package CW4;

import javax.swing.*;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public interface IModel {
    void Move(int yax, int xax, boolean actuallyMove);


    int getFirstBlack();

    int getFirstWhite();

    Piece[][] getPieces();

    void setPieces(Piece[][] pieces);

    void updateScores();

    int getWhiteScore();

    int getBlackScore();
}
