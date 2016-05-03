package CW4;


import java.util.Random;

/**
 * Created by Rahul Soni on 02/05/2016.
 */
public class Model implements IModel {

    IControllerFromModel c;
    public Model(IControllerFromModel c)
    {
        this.c = c;
    }


    private static Piece[][] Pieces = new Piece[8][8];

    private static int whiteScore = 0;
    private static int blackScore = 0;

    static boolean player = true;

    static boolean gameOver = false;

    static int bestMove = 0;

    Piece bestTarget = Pieces[0][0];


    public static boolean isWhitePlaying = true;


    public boolean getActivePlayer()
    {
        return isWhitePlaying;
    }


    public void create(){


        for (int i = 0; i < 8; ++i) // x value
        {
            for (int b = 0; b < 8; ++b)  // y value
            {


                Pieces[i][b] = new Piece(i, b, 0);

            }
        }

        Pieces[3][3].setIsOwned(1);
        Pieces[3][4].setIsOwned(-1);
        Pieces[4][3].setIsOwned(-1);
        Pieces[4][4].setIsOwned(1);


        updateScores();


    }

    public Piece[][] getPieces(){

        return Pieces;
    }



    public int getWhiteScore(){

        return whiteScore;
    }


    public int getBlackScore(){

        return blackScore;
    }


    public void updateScores()
    {

        whiteScore = 0;
        blackScore = 0;

        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++)
            {

                if (Pieces[x][y].isOwned() == 1)
                {
                    whiteScore += 1;
                }
                else if (Pieces[x][y].isOwned() == -1)
                {
                    blackScore += 1;
                }

            }

        }
    }

    public void Move(boolean p, int yax, int xax)
    {

        DoMove1(xax, yax, true, p);

        updateScores();



        c.updateView();

        boolean m = MAI(p);

        //System.out.println(isWhitePlaying);
    }


    public int getFirstWhite(){

        return 2;
    }


    public int getFirstBlack(){

        return 2;
    }

    private boolean inBounds(int y, int x)
    {
        return ! (y<0 || y > 7 || x < 0 || x > 7);
    }

    public int DoMove(int yax, int xax, boolean actuallyMove, boolean p)
    {

        int current_player;
        boolean found = false;

        if(Pieces[xax][yax].isOwned() == 1 || Pieces[xax][yax].isOwned() == -1){
            return 0;
        }

        boolean legal=false;

        if (p){
            current_player = 1;
        }
        else{
            current_player = -1;
        }


        int capturedPoints = 0;


        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y <2; y++)
            {
                Piece current;


                int posX = xax + x;
                int posY = yax + y;
                found = false;
                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                    current = Pieces[posX][posY];
                }
                else{
                    continue;
                }

                if (posX > 8 || posY >8 || current.isOwned() == current_player || current.isOwned() == 0){
                    continue;
                }

                while (!found)
                {
                    posX += x;
                    posY += y;

                    if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                        current = Pieces[posX][posY];
                    }

                    if (current.isOwned() == current_player)
                    {

                        legal = true;
                        found = true;

                        //Pieces[posX][posY].setIsOwned(current_player);

                        if (actuallyMove)
                        {

                            posX -= x;
                            posY -= y;
                            if (posX < 8 && posY < 8 && posX > 0 && posY > 0) {
                                current = Pieces[posX][posY];
                            }


                            while (current.isOwned() != 0)
                            {
                                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                                    //Pieces[posX][posY].setIsOwned(current_player);
                                    capturedPoints += 1;
                                }
                                posX -= x;
                                posY -= y;

                                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                                    current = Pieces[posX][posY];
                                }
                            }



                        }
                    }
                    else if (posX < 0 || posY < 0 ||posX > 8 || posY > 8 ||current.isOwned() == 0)
                    {
                        found = true;
                    }
                }
            }
        }



        if (legal)
        {
            //Pieces[xax][yax].setIsOwned(current_player);
            capturedPoints += 1;
            //System.out.println("Current captures - "+ capturedPoints);
            //isWhitePlaying = !isWhitePlaying;
            //System.out.println(isWhitePlaying);
        }



        //System.out.println("Current captures - "+ capturedPoints);
        return capturedPoints;
    }

    public Piece[][] DoMove1(int yax, int xax, boolean actuallyMove, boolean p)
    {

        int current_player;
        boolean found = false;

        if(Pieces[xax][yax].isOwned() == 1 || Pieces[xax][yax].isOwned() == -1){
            return Pieces;
        }

        boolean legal=false;

        if (p){
            current_player = 1;
        }
        else{
            current_player = -1;
        }





        for (int x = -1; x < 2; x++)
        {
            for (int y = -1; y <2; y++)
            {
                Piece current;

                int posX = xax + x;
                int posY = yax + y;
                found = false;
                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                    current = Pieces[posX][posY];
                }
                else{
                    continue;
                }

                if (posX > 8 || posY >8 || current.isOwned() == current_player || current.isOwned() == 0){
                    continue;
                }

                while (!found)
                {
                    posX += x;
                    posY += y;

                    if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                        current = Pieces[posX][posY];
                    }

                    if (current.isOwned() == current_player)
                    {

                        legal = true;
                        found = true;

                        //Pieces[posX][posY].setIsOwned(current_player);

                        if (actuallyMove)
                        {

                            posX -= x;
                            posY -= y;
                            if (posX < 8 && posY < 8 && posX > 0 && posY > 0) {
                                current = Pieces[posX][posY];
                            }


                            while (current.isOwned() != 0)
                            {
                                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                                    Pieces[posX][posY].setIsOwned(current_player);
                                }
                                posX -= x;
                                posY -= y;

                                if (posX < 8 && posY < 8 && posX > -1 && posY > -1) {
                                    current = Pieces[posX][posY];
                                }
                            }



                        }
                    }
                    else if (posX < 0 || posY < 0 ||posX > 8 || posY > 8 ||current.isOwned() == 0)
                    {
                        found = true;
                    }
                }
            }
        }



        if (legal)
        {
            Pieces[xax][yax].setIsOwned(current_player);
            //System.out.println(isWhitePlaying);
            isWhitePlaying = !isWhitePlaying;
        }


        return Pieces;
    }







    public boolean MAI(boolean player)
    {

        bestMove = 0;
        bestTarget = Pieces[4][4];

        int iplayer = 0;


        if (player)
        {
            iplayer = 1;
        }

        else if (!player)
        {
            iplayer = -1;
        }



        Random random = new Random();


        for (int x = 0; x <= 7; x++)
        {
            for (int y = 0; y <= 7; y++)
            {

                int temp;


                    temp = DoMove(y,x,false,player);

                    if (temp == 0){
                        continue;
                    }

                    //System.out.print(temp);

                    if(temp > bestMove)
                    {
                        //System.out.print(temp);
                        bestMove = temp;

                        //System.out.print(bestMove);
                        bestTarget = Pieces[x][y];


                    }

                    if (temp == bestMove)
                    {

                        int value = random.nextInt(2);

                        if (value == 1)
                        {

                            bestMove = temp;
                            bestTarget = Pieces[x][y];
                        }

                    }


            }

        }

        //System.out.print(bestTarget.getMyX()+"+"+bestTarget.getMyY());
        if (bestMove == 0){

            gameOver = true;

            //System.out.print(gameOver);

            c.gameIsOver();

            return false;
        }
        else
        {
            //System.out.print(gameOver);
            return true;
        }


    }

    public void setPieces(Piece[][] Pieces){

        Pieces = Pieces;

    }

    public void aimove(boolean player)
    {

        if (MAI(player)){


            Move(player,bestTarget.getMyX(),bestTarget.getMyY() );

        }

    }
}


