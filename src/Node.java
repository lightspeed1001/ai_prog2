import java.util.ArrayList;
import java.util.List;

public class Node 
{
    private Node _parent;
    private List<Node> _children;
    private String _lastMove;
    private Board _board;

    public Node() {
        this(null, null, null);
    }

    public Node(Node parent) {
        this(parent, null, null);
    }
    
    public Node(Node parent, String lastMove) {
        this(parent, lastMove, null);
    }

    public Node(Node parent, String lastMove, Board board) {
        this.setBoard(board);
        this.setLastMove(lastMove);
        this.setParent(parent);
        this._children = new ArrayList<Node>();
    }

    public void AddChild(Node child) {
        if(child.getParent() == null)
            child.setParent(this);
        _children.add(child);
    }

    public Node[] GetChildren() {
        return (Node[])_children.toArray();
    }

    //#region Properties

    /**
     * @return the board
     */
    public Board getBoard() {
        return _board;
    }

    /**
     * @param _board the board to set
     */
    public void setBoard(Board board) {
        this._board = board;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return _parent;
    }

    /**
     * @param _parent the parent to set
     */
    public void setParent(Node parent) {
        this._parent = parent;
    }

    /**
     * @return the lastMove
     */
    public String getLastMove() {
        return _lastMove;
    }

    /**
     * @param _lastMove the lastMove to set
     */
    public void setLastMove(String lastMove) {
        this._lastMove = lastMove;
    }

    //#endregion
}