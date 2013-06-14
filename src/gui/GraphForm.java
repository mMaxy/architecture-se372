package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 11:38
 */

import graph.Graph;

import javax.swing.*;
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
        graphPanel = new GraphPanel();
        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showOpenDialog(GraphForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        graph.setGraphFromMatrix(graph.readFromFile(new FileReader(jfc.getSelectedFile().getPath())));
                    } catch (Exception e1) {
                        e1.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
                    }
                } else {
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