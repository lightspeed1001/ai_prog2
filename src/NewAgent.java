public class NewAgent implements Agent
{
	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
    private Board board;
	private float maxTime; // timeout setting from the gameplayer

	private int maxDepth = 9; //Change this to make the search tree go deeper/shallower	

	//Statistics
	private long start;
	private int numMoves;

	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
    public void init(String role, int width, int height, int playclock) {
		this.role = role;
		this.playclock = playclock;
		this.maxTime = playclock * 1000;
		myTurn = !role.equals("white");
		start = System.currentTimeMillis();  
		board = new Board(width, height);
		numMoves = 0;
		if(role.equals("white")) {
			board.setWhiteTurn(true);
			board.setWhiteAsMax(true);
		}
		else {
			//So that when playing as black, we don't lose on purpose
			//Happened in testing a few times. Was rather amusing.
			board.setWhiteAsMax(false);
			board.setWhiteTurn(false);
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
            board.MovePawn(x1, y1, x2, y2);
            numMoves++;
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			AlphaBetaSolver solver = new AlphaBetaSolver(maxDepth, board, maxTime);
			solver.Solve();
			int bestNextState = solver.GetIndexofBestMove();
			
			LegalState bestMove = board.GetLegalMoves().get(bestNextState);

			return "(move " + (bestMove.x1+1) + " " + (bestMove.y1+1) + " " + (bestMove.x2+1) + " " + (bestMove.y2+1) + ")";
		} else {
			return "noop";
		}
	}

	// is called when the game is over or the match is aborted
	@Override
	public void cleanup() {
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Time taken: " + elapsedTime + "ms");
		System.out.println("Number of turns: " + numMoves);
		System.out.println("Time limit: " + this.playclock);
	}
}