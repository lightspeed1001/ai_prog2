import java.util.ArrayList;
import java.util.List;

public class Board implements IState {
    // variables
    private int width, height;
    private CellState[][] board;
    private ArrayList<LegalState> LegalMovesWhite;
    private ArrayList<LegalState> LegalMovesBlack;
    private boolean whiteTurn;  //Whose turn is it?
    private boolean maxIsWhite; //Which color am I? (max player)

    // constructor
    public Board(int w, int h) {
        width = w;
        height = h;
        board = new CellState[width][height];
        whiteTurn = true;
        maxIsWhite = true;
        for (int i = 0; i < width; i++) {
            board[i][0] = CellState.White;
            board[i][1] = CellState.White;
            board[i][height - 1] = CellState.Black;
            board[i][height - 2] = CellState.Black;
            for (int j = 2; j < height - 2; j++) {
                board[i][j] = CellState.Empty;
            }
        }
    }

    // Copy constructor
    public Board(Board other) {
        this.width = other.width;
        this.height = other.height;
        this.board = new CellState[width][height];
        this.whiteTurn = !other.whiteTurn; //remember to flip! Maybe just do it here?
        this.maxIsWhite = other.maxIsWhite;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.board[i][j] = other.board[i][j];
            }
        }
    }

    public void MovePawn(int x1, int y1, int x2, int y2) {
        x1 -= 1;
        x2 -= 1;
        y1 -= 1;
        y2 -= 1;

        CellState b = board[x1][y1];
        board[x1][y1] = CellState.Empty;
        board[x2][y2] = b;
    }

    public ArrayList<LegalState> GetLegalMoves() {
        CellState cell;
        int x1, y1, x2, y2;
        LegalState temp;
        LegalMovesWhite = new ArrayList<LegalState>();
        LegalMovesBlack = new ArrayList<LegalState>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cell = board[i][j];

                if (i == 0) {
                    // only check i+1 for diagonal
                    switch (cell) {
                    case White:
                        if (board[i + 1][j + 1] == CellState.Black) {
                            temp = new LegalState(i, j, i + 1, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        if (board[i][j + 1] == CellState.Empty) {
                            temp = new LegalState(i, j, i, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        break;
                    case Black:
                        if (board[i + 1][j - 1] == CellState.White) {
                            temp = new LegalState(i, j, i + 1, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        if (board[i][j - 1] == CellState.Empty) {
                            temp = new LegalState(i, j, i, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        break;
                    default:
                        // do nothing
                    }
                } else if (i == width - 1) {
                    // only check i-1 for diagonal
                    switch (cell) {
                    case White:
                        if (board[i - 1][j + 1] == CellState.Black) {
                            temp = new LegalState(i, j, i - 1, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        if (board[i][j + 1] == CellState.Empty) {
                            temp = new LegalState(i, j, i, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        break;
                    case Black:
                        if (board[i - 1][j - 1] == CellState.White) {
                            temp = new LegalState(i, j, i - 1, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        if (board[i][j - 1] == CellState.Empty) {
                            temp = new LegalState(i, j, i, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        break;
                    default:
                        // do nothing
                    }
                } else if (i == 0) {
                    //only check i + 1 for diag
                    switch (cell) {
                        case White:
                            if (board[i + 1][j + 1] == CellState.Black) {
                                temp = new LegalState(i, j, i + 1, j + 1);
                                LegalMovesWhite.add(temp);
                            }
                            if (board[i][j + 1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j + 1);
                                LegalMovesWhite.add(temp);
                            }
                            break;
                        case Black:
                            if (board[i + 1][j - 1] == CellState.White) {
                                temp = new LegalState(i, j, i + 1, j - 1);
                                LegalMovesBlack.add(temp);
                            }
                            if (board[i][j - 1] == CellState.Empty) {
                                temp = new LegalState(i, j, i, j - 1);
                                LegalMovesBlack.add(temp);
                            }
                            break;
                        default:
                            break;
                    }
                } else {
                    // check both for diagonal
                    switch (cell) {
                    case White:
                        if (board[i - 1][j + 1] == CellState.Black) {
                            temp = new LegalState(i, j, i - 1, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        if (board[i + 1][j + 1] == CellState.Black) {
                            temp = new LegalState(i, j, i + 1, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        if (board[i][j + 1] == CellState.Empty) {
                            temp = new LegalState(i, j, i, j + 1);
                            LegalMovesWhite.add(temp);
                        }
                        break;
                    case Black:
                        if (board[i - 1][j - 1] == CellState.White) {
                            temp = new LegalState(i, j, i - 1, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        if (board[i + 1][j - 1] == CellState.White) {
                            temp = new LegalState(i, j, i + 1, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        if (board[i][j - 1] == CellState.White) {
                            temp = new LegalState(i, j, i, j - 1);
                            LegalMovesBlack.add(temp);
                        }
                        break;
                    default:
                        // do nothing
                    }
                }

            }
        }
        if(whiteTurn) return LegalMovesWhite;
        else return LegalMovesBlack;
    }

    public int calcScore(int x1, int y1, int x2, int y2) 
    {
        int score = 0;
        CellState cell, cell2;
        cell = board[x1][y1];
        cell2 = board[x2][y2]; 
        if (cell == CellState.Black) {
            if(y1 == 1 && y2 == 0){
                return 99;
            }
            if (cell2 == CellState.White) {
                score += 30;
            }

            score += (height - y2) * 10;
        } else {
            if (y2 == height - 1) {
                return 100;
            }
            if (cell2 == CellState.Black) {
                score += 30;
            }

            score += (y2 * 10);
        }

        return score;
    }

    public boolean LegalMove(boolean whiteTurn, int x1, int y1, int x2, int y2) 
    {

        CellState cell = board[x1][y1];
        CellState cell2 = board[x2][y2];
        if (cell == CellState.Empty) {
            return false;
        }
        switch (cell) {
        case White:
            if (whiteTurn) {
                if (y2 != y1) {
                    if (cell2 == CellState.Black) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            break;
        case Black:
            if (!whiteTurn) {
                if (y2 != y1) {
                    if (cell2 == CellState.White) {
                        return true;
                    } else {
                        return false;
                    }
                }
            }
            break;
        default:
            if (cell2 == CellState.Empty) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public void print() {
        System.out.println("Printing board: ");
        for (int j = 0; j < height; j++) {
            System.out.print("[ ");
            for (int i = 0; i < width; i++) {
                CellState c = board[i][j];
                switch (c) {
                case White:
                    System.out.print("W ");
                    break;
                case Black:
                    System.out.print("B ");
                    break;
                default:
                    System.out.print(". ");
                }
            }
            System.out.println("]");
        }
    }

    //Returns -100 if max lost, 100 if max wins, 0 otherwise
    public int IsGameOver()
    {
        for (int i = 0; i < width; i++) 
        {
            if(board[i][0] == CellState.Black) return maxIsWhite ? -100 : 100; //White loses
            else if(board[i][height-1] == CellState.White) return maxIsWhite ? 100 : -100; //Black loses
        }
        return 0;
    }

    public int FurthestWhitePawn()
    {
        int indexofFurthest = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                CellState c = board[i][j];
                if(c == CellState.White && j > indexofFurthest)
                    indexofFurthest = j;
            }
        }
        return (indexofFurthest * 20) + 1;
    }

    public int FurthestBlackPawn()
    {
        int indexofFurthest = height;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                CellState c = board[i][j];
                if(c == CellState.Black && j < indexofFurthest)
                {
                    indexofFurthest = j;
                }
            }
        }
        // System.out.println("Indexof furthest black pawn: " + indexofFurthest);
        return (height - indexofFurthest) * 10;
    }

    public void setWhiteAsMax(boolean white)
    {
        this.maxIsWhite = white;
    }

    public boolean isMaxWhite()
    {
        return this.maxIsWhite;
    }

    
    public void setWhiteTurn(boolean whiteTurn) {
        this.whiteTurn = whiteTurn;
    }

    public boolean getWhiteTurn()
    {
        return this.whiteTurn;
    }

    public void PrintDetailedScore()
    {
        int whiteScore = FurthestWhitePawn(), blackScore = FurthestBlackPawn(), winner = IsGameOver();
        System.out.println("State and score:");
        System.out.println(Str());
        System.out.println(whiteScore + " - " + blackScore);
        if(winner != 0) 
        System.out.println("Winneroo: " + (winner == 100 ? "white" : "black"));
        else
        {
            if(maxIsWhite)
                System.out.println("white-black: " + (whiteScore - blackScore));
            else
                System.out.println("black-white: " + (blackScore - whiteScore));
        }
    }
    //IState implementation
    @Override
    public boolean GameOver() {
        return (IsGameOver() != 0);
    }

    @Override
    public String Str() {
        //Return a string representing the state.
        String str = "Max: " + (maxIsWhite ? "white; " : "black; ") + "Turn player: " + (whiteTurn ? "white;" : "black;") + "\n";
        for (int j = 0; j < height; j++) {
            str += "[ ";
            for (int i = 0; i < width; i++) {
                CellState c = board[i][j];
                switch (c) {
                case White:
                    str += "W ";
                    break;
                case Black:
                    str += "B ";
                    break;
                default:
                    str += ". ";
                }
            }
            str += "]\n";
        }
        return str; 
    }

    @Override
    public int Score() 
    {
        //Return/calculate the score for this particular state.
        int whiteScore = FurthestWhitePawn(), blackScore = FurthestBlackPawn(), winner = IsGameOver();
        // System.out.println("State and score:");
        // System.out.println(Str());
        // System.out.println(whiteScore + " - " + blackScore);

        if(winner != 0) 
            return winner;
        else
        {
            if(maxIsWhite)
                return whiteScore - blackScore;
            else
                return blackScore - whiteScore;
        }
    }

    @Override
    public List<IState> SuccessorStates() {
        //Find and return all successor states.
        List<LegalState> moves = GetLegalMoves();
        List<IState> successors = new ArrayList<IState>();
        for (LegalState move : moves) 
        {
            Board other = new Board(this);
            other.MovePawn(move.x1 + 1, move.y1 + 1, move.x2 + 1, move.y2 + 1); //the moves are already 0 based in here
            successors.add(other);
        }
        return successors;
    }
}