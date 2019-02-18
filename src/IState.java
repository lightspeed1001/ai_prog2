import java.util.List;

/**
 * IState
 */
public interface IState {

    public String Str(); //TODO: Change to something else
    public int Score();
    public List<IState> SuccessorStates();
    public boolean GameOver();
}