public class ScoreState 
{
    public IState state;
    public int score;
    public int childIndex;
    
    public ScoreState(int score, IState state, int child) {
        this.score = score;
        this.state = state;
        this.childIndex = child;
    }

    @Override
    public String toString() {
        return "Score: " + this.score + "; Value: " + this.state.Str() + ";";
    }
}