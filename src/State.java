import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//TODO DELETE THIS
//Shitty demo of how to use the interface IState along with the AlphaBetaSolver
public class State implements IState
{
    public int val;
    public static Random r = new Random(2279);
    public State(int x)
    {
        val = x;
    }

    public int Score()
    {
        return val;
    }

    public List<IState> SuccessorStates()
    {
        List<IState> states = new ArrayList<IState>();
        
        states.add(new State(r.nextInt(100) - 50));
        states.add(new State(r.nextInt(100) - 50));

        return states;
    }

    public String Str() 
    {
        return Integer.toString(val);
    }

    @Override
    public boolean GameOver() {
        return false;
    }
}