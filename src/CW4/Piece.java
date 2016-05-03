package CW4;

import javax.swing.*;


/**
 * Created by Rahul Soni on 03/05/2016.
 */
public class Piece extends JButton {

    private int currentlyOwned = 0;
    private int x;
    private int y;

    public int isOwned() {
        return currentlyOwned;
    }

    public synchronized void setIsOwned(int x) {
        currentlyOwned = x;

    }

    Piece(int x, int y, int owner){

        this.setX(x);
        this.setY(y);
        setIsOwned(owner);
    }


    public int getMyX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }


    public int getMyY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }
}
