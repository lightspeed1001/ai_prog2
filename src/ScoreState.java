/**
 * ScoreState
 */
public class ScoreState 
{
    public IState state;
    public int score;
    
    public ScoreState(int score, IState state)
    {
        this.score = score;
        this.state = state;
    }

    @Override
    public String toString() {
        return "Score: " + this.score + "; Value: " + this.state.Str() + ";";
    }
}