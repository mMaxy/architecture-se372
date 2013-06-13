package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k
 * Date: 13.06.13
 * Time: 22:48
 */

import javax.swing.*;
import java.awt.*;
import graph.Graph;

public class GraphPanel extends JPanel{
    private Graph graph;
    private Node[] nodes;
    //private Arc[] arcs;
    private Layer[] layers;

    public GraphPanel(){
        super();
        this.setBackground(Color.white);
    }

    public GraphPanel(Graph graph){

    }
}
