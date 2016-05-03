package CW4;


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

    public void Move(int yax, int xax, boolean p){

        DoMove1(Pieces, yax, xax, true, p);

        updateScores();

        c.updateView();
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

    public Piece[][] DoMove(Piece[][] board, int yax, int xax, boolean actuallyMove, boolean p)
    {

        int current_player;

        if(board[yax][xax].isOwned() == 1 || board[yax][xax].isOwned() == -1){
            return board;
        }

        boolean legal=false;


        for(int x = xax-1; x <= xax+1; x++){
            for(int y = yax-1; y<= yax+1; y++){

                if (p){
                    current_player = 1;
                }
                else{
                    current_player = -1;
                }

                if(! inBounds(x, y))
                    continue;

                if(board[x][y].isOwned() == -current_player){
                    int dx = x - xax;
                    int dy = y - yax;

                    int ty = y;
                    int tx = x;

                    while(inBounds(tx,ty) && board[tx][ty].isOwned() == -current_player){
                        ty += dy;
                        tx += dx;
                    }
                    if(inBounds(tx,ty) && board[tx][ty].isOwned() == current_player){
                        legal = true;

                        if(actuallyMove){
                            while(! (ty == yax && tx == xax)){
                                ty -= dy;
                                tx -= dx;
                                board[tx][ty].setIsOwned(current_player);
                            }
                            board[xax][yax].setIsOwned(current_player);
                        }

                    }
                }
            }
        }
        return board;
    }

    public Piece[][] DoMove1(Piece[][] board, int yax, int xax, boolean actuallyMove, boolean p)
    {

        int current_player;
        boolean found = false;

        if(board[xax][yax].isOwned() == 1 || board[xax][yax].isOwned() == -1){
            return board;
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
        }
        return Pieces;
    }



    public void setPieces(Piece[][] Pieces){

        Pieces = Pieces;

    }
}


