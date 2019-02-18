import java.util.List;

public interface IState {
    public String Str();
    public int Score();
    public List<IState> SuccessorStates();
    public boolean GameOver();
}