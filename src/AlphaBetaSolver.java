import java.util.ArrayList;
import java.util.List;

public class AlphaBetaSolver 
{
    private int _maxDepth;
    private IState _root;
    private List<ScoreState> _rootStateScores;
    private long _start;
    private float _timelimit;

    public AlphaBetaSolver(int depth, IState root, float timelimit) {
        _timelimit = timelimit;
        _maxDepth = depth;
        _root = root;
        _rootStateScores = new ArrayList<ScoreState>();
    }

    private int alphabetaminimax(int alpha, int beta, int depth, boolean max, IState s) {
        if(beta <= alpha) { 
            if(max) 
                return Integer.MAX_VALUE; 
            else 
                return Integer.MIN_VALUE; 
        }
        long timeNow = System.currentTimeMillis();
        if (depth >= _maxDepth || s.GameOver() || (timeNow - _start) >= (_timelimit * 0.9)) {
            return s.Score();
        }
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        List<IState> children = s.SuccessorStates();
        if(children.size() == 0) {
            return 0;
        }
        if(depth == 0) _rootStateScores.clear();

        for (IState child : children) {
            int score = 0;
            if(max) {
                //max turn
                score = alphabetaminimax(alpha, beta, depth+1, !max, child);
                maxValue = Math.max(maxValue, score); 
                
                alpha = Math.max(score, alpha);
                if(depth == 0) {
                    _rootStateScores.add(new ScoreState(score, child, children.indexOf(child)));
                }
            }
            else {
                //min turn
                score = alphabetaminimax(alpha, beta, depth+1, !max, child); 
                minValue = Math.min(minValue, score);
                
                beta = Math.min(score, beta);
            }
            if(score == Integer.MAX_VALUE || score == Integer.MIN_VALUE) 
                break;
        }
        
        return max ? maxValue : minValue;
    }

    public void Solve() {
        _start = System.currentTimeMillis();
        alphabetaminimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, _root);
    }

    public int GetIndexofBestMove() {
        int MAX = Integer.MIN_VALUE;
        int best = -1;
        for (int i = 0; i < _rootStateScores.size(); ++i) {
            ScoreState s = _rootStateScores.get(i);

            if (MAX < _rootStateScores.get(i).score) {
                MAX = s.score;
                best = i;
            }
        }
        return _rootStateScores.get(best).childIndex;
    }
}