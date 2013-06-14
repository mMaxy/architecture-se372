package graph;

/**
 * Created on: 14.06.13
 */
public class Connection {

    private int from;
    private int to;

    public Connection(int from, int to) {
        this.from = from;
        this.to = to;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }
}
