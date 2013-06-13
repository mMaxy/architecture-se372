package gui;

/**
 * Created with IntelliJ IDEA.
 * User: i.k.
 * Date: 13.06.13
 * Time: 11:38
 */

import javax.swing.*;

public class GraphForm {
    private JPanel boringPanel;
    private JTable matrixTable;
    private JLabel captionLabel;
    private JLabel numberOfLayersCaptionLabel;
    private JLabel numberOfLayersLabel;
    private JPanel graphPanel;
    private JPanel wrapperPanel;
    private JScrollPane matrixTableWrapper;

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
        graphPanel = new GraphPanel();
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