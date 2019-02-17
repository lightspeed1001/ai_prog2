import java.util.ArrayList;
import java.util.List;

//TODO DELETE THIS
public class App 
{
    public static int uptoDepth = 5;
    public static State root;
    public static List<ScoreState> rootChildrenScoreAndState;
    public static void main(String[] args) throws Exception 
    {
        root = new State(1);
        rootChildrenScoreAndState = new ArrayList<ScoreState>();
        AlphaBetaSolver abs = new AlphaBetaSolver(5, root);
        System.out.println(abs.getBest().Str());
        // alphabetaminimax(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, true, root);
        // for (ScoreState ss : rootChildrenScoreAndState) 
        // {
        //     System.out.println(ss.toString());
        // }
        
        // System.out.println(getBest().Str());
    }
}