import java.util.Random;

public class NewAgent implements Agent
{
    private Random random = new Random();

	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
    private int width, height; // dimensions of the board
    private Board board;
	
	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
    public void init(String role, int width, int height, int playclock) {
		this.role = role;
		this.playclock = playclock;
        myTurn = !role.equals("white");
        //TODO check if we are actually using width and height now
		this.width = width;
		this.height = height;
        
        board = new Board(width, height);
		if(role.equals("white"))
		{
			board.setWhiteAsMax(true);
		}
		else
		{
			board.setWhiteAsMax(false);
		}
    }

	// lastMove is null the first time nextAction gets called (in the initial state)
    // otherwise it contains the coordinates x1,y1,x2,y2 of the move that the last player did
    public String nextAction(int[] lastMove) {
    	if (lastMove != null) {
    		int x1 = lastMove[0], y1 = lastMove[1], x2 = lastMove[2], y2 = lastMove[3];
    		String roleOfLastPlayer;
    		if (myTurn && role.equals("white") || !myTurn && role.equals("black")) {
    			roleOfLastPlayer = "white";
    		} else {
    			roleOfLastPlayer = "black";
    		}
            System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
            
			// 1. update your internal world model according to the action that was just executed
			System.out.println("====================");
            board.MovePawn(x1, y1, x2, y2);
			// System.out.println("Board score: " + board.Score());
			board.PrintDetailedScore();
            board.print();
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			AlphaBetaSolver solver = new AlphaBetaSolver(15, board);
			solver.Solve();
			int bestNextState = solver.GetIndexofBestMove();
			// System.out.println("The best next state is: ");
			// System.out.println(bestNextState.Str());
            // 2. run alpha-beta search to determine the best move
			
			LegalState bestMove = board.GetLegalMoves().get(bestNextState);
			// System.out.println("(move " + (bestMove.x1+1) + " " + (bestMove.y1+1) + " " + (bestMove.x2+1) + " " + (bestMove.y2+1) + ")");
			
			//Kóðinn fyrir ofan færir peðið alltaf, óþarfi að gera hér
			// board.MovePawn(bestMove.x1+1, bestMove.y1+1, bestMove.x2+1, bestMove.y2+1);
			// board.print();
			//DO NOT FORGET THE BRACKETS PLZ			vvvvvvvvvvv
			return "(move " + (bestMove.x1+1) + " " + (bestMove.y1+1) + " " + (bestMove.x2+1) + " " + (bestMove.y2+1) + ")";

		} else {
			return "noop";
		}
	}

	// is called when the game is over or the match is aborted
	@Override
	public void cleanup() {
		// TODO: cleanup so that the agent is ready for the next match
	}
}