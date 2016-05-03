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


        model.Move(b,i,p);

    }

    public Piece[][] getPieces()
    {

        return model.getPieces();
    }


    public int getFirstWhite(){

        return model.getFirstWhite();
    }


    public int getFirstBlack(){

        return model.getFirstBlack();
    }

    public void setPieces(Piece[][] pieces){

        model.setPieces(pieces);
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

}
