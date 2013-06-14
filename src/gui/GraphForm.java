package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 11:38
 */

import graph.Graph;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;

public class GraphForm extends Component {
    private JPanel boringPanel;
    private JTable matrixTable;
    private JLabel captionLabel;
    private JLabel numberOfLayersCaptionLabel;
    private JLabel numberOfLayersLabel;
    private JPanel graphPanel;
    private JPanel wrapperPanel;
    private JScrollPane matrixTableWrapper;
    private JButton loadButton;

    private Graph graph;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GraphForm");
        frame.setContentPane(new GraphForm().wrapperPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public GraphForm(){
        captionLabel.setText("Матрица смежности");
        numberOfLayersCaptionLabel.setText("Количество слоев");
        loadButton.setText("Загрузить");
        loadButton.doClick();
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showOpenDialog(GraphForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        int[][] arr = Graph.readFromFile(new FileReader(jfc.getSelectedFile().getPath()));
                        graph = new Graph(arr.length);
                        graph.setGraphFromMatrix(arr);

                        graph.findLayers();
                        graph.buildAdjacencyMatrix();

                        graphPanel = new GraphPanel(graph);
                        matrixTable = new JTable(graph.buildAdjacencyMatrix(), new Object[graph.buildAdjacencyMatrix().length]);
                    } catch (Exception e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                }
                else {
                    System.err.println("Open command cancelled by user.");
                }
            }
        });
    }

    public JPanel getGraphPanel() {
        return graphPanel;
    }

    public void setGraphPanel(JPanel graphPanel) {
        this.graphPanel = graphPanel;
    }

    public JPanel getWrapperPanel() {
        return wrapperPanel;
    }

    public void setWrapperPanel(JPanel wrapperPanel) {
        this.wrapperPanel = wrapperPanel;
    }


}