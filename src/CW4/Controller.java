package CW4;

import java.io.IOException;
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

    public void move(boolean p, int i, int b){


        model.Move(p,i,b);

    }

    public Piece[][] getPieces()
    {

        return model.getPieces();
    }




    public void updateView(){

        view.updateView();
    }


    public int getWhiteScore()
    {
        return model.getWhiteScore();
    }

    public int getBlackScore()
    {
        return model.getBlackScore();
        }


    public boolean getActivePlayer(){

        return model.getActivePlayer();
    }


    public void gameIsOver(){

        view.gameIsOver();

    }

    public void aimove(boolean player){

        model.aimove(player);
    }
}
