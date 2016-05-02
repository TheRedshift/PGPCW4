package CW4;


import java.util.*;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class Model implements IModel {

    IControllerFromModel c;
    public Model(IControllerFromModel c)
    {
        this.c = c;
    }



    private int[][] board;
    private int whiteScore = 0;
    private int blackScore = 0;

    public void create(){

    }

    public void store(int x, int y){

        System.out.println(y +""+""+ x);
    }

    public  int[][] getFirstBoard()
    {

        int[][] board = new int[8][8];
        board[3][3] = 1;
        board[3][4] = -1;
        board[4][3] = -1;
        board[4][4] = 1;


        return board;

    }
}

