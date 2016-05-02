package CW4;

import java.util.Arrays;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class Controller implements IControllerFromModel, IControllerFromView {

    IModel model;
    IView view;

    public void setViewAndModel(View v, Model m) {

        model = m;
        view = v;
    }

    public void move(int i, int b) {
        System.out.println("Button pressed - " + String.valueOf(i) + String.valueOf(b));
        if (i > 3) {
            view.displayError("Button is more than 3");

        }

        model.store(i, b);

    }



    public int[][] getFirstBoard()
    {

        int [][] board = model.getFirstBoard();


        return board;
    }
}
