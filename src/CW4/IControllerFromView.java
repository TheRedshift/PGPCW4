package CW4;

import java.io.IOException;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public interface IControllerFromView {

    void move (boolean p, int i, int b);

    int getFirstBlack();

    int getFirstWhite();

    Piece[][] getPieces();

    void setPieces(Piece[][] pieces);

    int getWhiteScore();

    int getBlackScore();

    boolean getActivePlayer();
}
