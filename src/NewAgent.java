import java.util.ArrayList;
import java.util.Random;

public class NewAgent implements Agent
{
    private Random random = new Random();

	private String role; // the name of this agent's role (white or black)
	private int playclock; // this is how much time (in seconds) we have before nextAction needs to return a move
	private boolean myTurn; // whether it is this agent's turn or not
    private Board board;
	private long start;

	/*
		init(String role, int playclock) is called once before you have to select the first action. Use it to initialize the agent. role is either "white" or "black" and playclock is the number of seconds after which nextAction must return.
	*/
    public void init(String role, int width, int height, int playclock) {
		this.role = role;
		this.playclock = playclock;
		myTurn = !role.equals("white");
		start = System.currentTimeMillis();  
        board = new Board(width, height);
		if(role.equals("white")) {
			board.setWhiteTurn(true);
			board.setWhiteAsMax(true);
		}
		else {
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
            
			System.out.println("====================");
            System.out.println(roleOfLastPlayer + " moved from " + x1 + "," + y1 + " to " + x2 + "," + y2);
            board.MovePawn(x1, y1, x2, y2);
			System.out.println("Board score: " + board.Score());
			board.PrintDetailedScore();
            // board.print();
    	}
		
    	// update turn (above that line it myTurn is still for the previous state)
		myTurn = !myTurn;
		if (myTurn) {
			// ArrayList<LegalState> s = board.GetLegalMoves();
			// for (LegalState var : s) {
			// 	System.out.println(var.x1 + " " + var.y2 + " " + var.x2 + " " + var.y2);
			// }
			// System.exit(0);
			AlphaBetaSolver solver = new AlphaBetaSolver(10, board);
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
		//Not really needed? Init function takes care of this.
		long elapsedTime = System.currentTimeMillis() - start;
		System.out.println("Time taken: " + elapsedTime + "ms");
	}
}