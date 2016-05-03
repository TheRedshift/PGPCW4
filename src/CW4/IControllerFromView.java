package CW4;

import java.io.IOException;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public interface IControllerFromView {

    void move (boolean p, int i, int b);

    Piece[][] getPieces();


    int getWhiteScore();

    int getBlackScore();

    boolean getActivePlayer();

    void aimove(boolean player);
}
