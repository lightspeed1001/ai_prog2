import java.util.ArrayList;
import java.util.List;

/**
 * AlphaBetaSolver
 */
public class AlphaBetaSolver 
{
    private int _maxDepth;
    private IState _root;
    private List<ScoreState> _rootStateScores;

    public AlphaBetaSolver(int depth, IState root)
    {
        _maxDepth = depth;
        _root = root;
        _rootStateScores = new ArrayList<ScoreState>();
    }

    private int alphabetaminimax(int alpha, int beta, int depth, boolean max, IState s)
    {
        if(beta <= alpha)
        { 
            if(max) 
                return Integer.MAX_VALUE; 
            else 
                return Integer.MIN_VALUE; 
        }

        if (depth >= _maxDepth) 
        {
            // System.out.println(s.Str());
            return s.Score();
        }
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        List<IState> children = s.SuccessorStates();

        if(depth == 0) _rootStateScores.clear();

        for (IState child : children) 
        {
            int score = 0;
            if(max)
            {
                //max turn
                score = alphabetaminimax(alpha, beta, depth+1, !max, child);
                maxValue = Math.max(maxValue, score); 
                
                alpha = Math.max(score, alpha);
                if(depth == 0)
                    _rootStateScores.add(new ScoreState(score, child));
            }
            else
            {
                //min turn
                score = alphabetaminimax(alpha, beta, depth+1, !max, child); 
                minValue = Math.min(minValue, score);
                
                //Set beta
                beta = Math.min(score, beta);
            }
            if(score == Integer.MAX_VALUE || score == Integer.MIN_VALUE) 
                break;
        }
        
        return max ? maxValue : minValue;
    }

    //TODO make better; Rename into "GetOptimalState" ?
    public IState getBest()
    {
        alphabetaminimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, _root);

        int MAX = Integer.MIN_VALUE;
        int best = -1;

        for (int i = 0; i < _rootStateScores.size(); ++i) 
        {
            if (MAX < _rootStateScores.get(i).score) 
            {
                MAX = _rootStateScores.get(i).score;
                best = i;
            }
        }
        // System.out.println(_rootStateScores.get(best).score);
        return _rootStateScores.get(best).state;
    }
}