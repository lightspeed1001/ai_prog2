public class Board
{
    //variables
    private int width, height;
    private CellState[][] board;
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
        
        this.board = new CellState[width][height];
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
        return false;

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