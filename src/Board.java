import java.util.ArrayList;

public class Board
{
    //variables
    private int width, height;
    private CellState[][] board;
    private ArrayList<LegalState> LegalMovesWhite;
    private ArrayList<LegalState> LegalMovesBlack;

    //constructor
    public Board(int w, int h)
    {
        width = w;
        height = h;
        board = new CellState[width][height];

        for (int i = 0; i < width; i++) 
        {
            board[i][0] = CellState.White;
            board[i][1] = CellState.White;
            board[i][height-1] = CellState.Black;
            board[i][height-2] = CellState.Black;
            for(int j = 2; j < height-2; j++)
            {
                board[i][j] = CellState.Empty;
            }
        }
    }
    //Copy constructor
    public Board(Board other)
    {
        this.width = other.width;
        this.height = other.height;
        
        this.board = new CellSta.te[width][height];
        for (int i = 0; i < width; i++) 
        {
            for (int j = 0; j < height; j++) 
            {
                this.board[i][j] = other.board[i][j];
            }    
        }
    }

    public void MovePawn(int x1, int y1, int x2, int y2)
    {
        x1 -= 1; x2 -= 1; y1 -= 1; y2 -= 1;

        CellState b = board[x1][y1];
        board[x1][y1] = CellState.Empty;
        board[x2][y2] = b;
    }
    public LegalState getLegalStates()
    {
        CellState cell;
        int x1, y1, x2, y2;
        LegalState temp;
        LegalMovesWhite = new ArrayList<LegalState>();
        LegalMovesBlack = new ArrayList<LegalState>();
        for(int i = 0; i < width; i++)
        {
            for(int j = 0; j < height; j++)
            {
                cell = board[i][j];

                if(i == 0){
                    //only check i+1 for diagonal
                    switch(cell){
                        case White:
                            if(board[i+1][j+1] == CellState.Black) {
                                temp = new LegalState(i, j, i+1, j+1);
                                LegalMovesWhite.add(temp);
                            }
                            if(board[i][j+1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j+1);
                                LegalMovesWhite.add(temp);
                            }    
                            break;
                        case Black:
                            if(board[i+1][j-1] == CellState.White) {
                                temp = new LegalState(i, j, i+1, j-1);
                                LegalMovesBlack.add(temp);
                            }
                            if(board[i][j-1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j-1);
                                LegalMovesBlack.add(temp);
                            }    
                            break;
                                break;
                        default:                 
                            //do nothing
                    }
                }
                else if(i == width-1)
                {
                    //only check i-1 for diagonal
                    switch(cell){
                        case White:
                            if(board[i-1][j+1] == CellState.Black) {
                                temp = new LegalState(i, j, i-1, j+1);
                                LegalMovesWhite.add(temp);
                            }
                            if(board[i][j+1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j+1);
                                LegalMovesWhite.add(temp);
                            }    
                            break;
                        case Black:
                            if(board[i-1][j-1] == CellState.White) {
                                temp = new LegalState(i, j, i-1, j-1);
                                LegalMovesBlack.add(temp);
                            }
                            if(board[i][j-1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j-1);
                                LegalMovesBlack.add(temp);
                            }    
                            break;
                                break;
                        default:                 
                            //do nothing
                    }
                }
                else
                {
                    //check both for diagonal
                    switch(cell){
                        case White:
                            if(board[i-1][j+1] == CellState.Black) {
                                temp = new LegalState(i, j, i-1, j+1);
                                LegalMovesWhite.add(temp);
                            }
                            if(board[i+1][j+1] == CellState.Black) {
                                temp = new LegalState(i, j, i+1, j+1);
                                LegalMovesWhite.add(temp);
                            }
                            if(board[i][j+1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j+1);
                                LegalMovesWhite.add(temp);
                            }    
                            break;
                        case Black:
                            if(board[i-1][j-1] == CellState.White) {
                                temp = new LegalState(i, j, i-1, j-1);
                                LegalMovesBlack.add(temp);
                            }
                            if(board[i+1][j-1] == CellState.White) {
                                temp = new LegalState(i, j, i+1, j-1);
                                LegalMovesBlack.add(temp);
                            }
                            if(board[i][j-1] == CellState.White) {
                                temp = new LegalState(i, j, i, j-1);
                                LegalMovesBlack.add(temp);
                            }    
                            break;
                                break;
                        default:                 
                            //do nothing
                    }
                }

            }
        }
    }
    public int calcScore(int x1, int y1, int x2, int y2)
    {
        // or check the whole board to see if the game has been won or a draw
        int score = 0;
        CellState cell, cell2;
        cell = board[x1][y1];
        cell2 = board[x2][y2];
        if(cell == CellState.Black){
            if (y2 == 0) {
                return 100;
            }
            if (cell2 == CellState.White){
                score += 30;
            }

            score += (height-y2)*10;
        }
        else{
            if (y2 == height -1) {
                return 100;
            }
            if (cell2 == CellState.Black){
                score += 30;
            }

            score += (y2*10);
        }

        return score;
    }
    public boolean LegalMove(boolean whiteTurn, int x1, int y1, int x2, int y2)
    {
        
        if(x1 >= width || x2 >= width || y1 >= height || y2 >= height){
            return false;
        }

        CellState cell = board[x1][y1];
        CellState cell2 = board[x2][y2];
        if(cell == CellState.Empty){
            return false;
        }
        switch(cell){
            case White:
                if(whiteTurn) {
                    if(y2 != y1){
                        if(cell2 == CellState.Black){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }   
                break;
            case Black:
                if(!whiteTurn){
                    if(y2 != y1){
                        if(cell2 == CellState.White){
                            return true;
                        }
                        else{
                            return false;
                        }
                    }
                }
                break;
            default:                 
                if(cell2 == CellState.Empty){
                    return true;
                }
                else{
                    return false;
                }
        }

    }
    public void print()
    {
        System.out.println("Printing board: ");
        for(int j = 0; j < height; j++)
        {
            System.out.print("[ ");
            for(int i = 0; i < width; i++)
            {
                CellState c = board[i][j];
                switch (c) {
                    case White:
                        System.out.print("W ");
                        break;
                    case Black:
                        System.out.print("B ");
                        break;
                    default: System.out.print(". ");
                }
            }
            System.out.println("]");
        }
    }
}