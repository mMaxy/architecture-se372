package graph;

import  graph.Graph.Node;
/**
 * Created on: 14.06.13
 */
public class Connection {

    private Node from;
    private Node to;

    public Connection(Node from, Node to) {
        this.from = from;
        this.to = to;
    }

    public Node getFrom() {
        return from;
    }

    public Node getTo() {
        return to;
    }
}
