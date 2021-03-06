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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileReader;

public class GraphForm extends Component {
    private JPanel boringPanel;
    private JLabel captionLabel;
    private JLabel numberOfLayersCaptionLabel;
    private JLabel numberOfLayersLabel;
    private JPanel graphPanel;
    private JPanel wrapperPanel;
    private JButton loadButton;
    private JTextArea matrixTextArea;
    private JButton commitButton;
    private JButton buttonAnalyze;
    private JButton buttonLoops;

    private boolean dragging = false;

    private Graph graph;
    private static JFrame mainFrame;

    public static void main(String[] args) {
        JFrame frame = new JFrame("GraphForm");
        frame.setContentPane(new GraphForm().wrapperPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        mainFrame = frame;
    }

    public GraphForm() {
        $$$setupUI$$$();
        captionLabel.setText("Матрица смежности");
        numberOfLayersCaptionLabel.setText("Количество слоев:");
        loadButton.setText("Загрузить");
        commitButton.setText("Пересчитать");

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfc = new JFileChooser();
                int returnVal = jfc.showOpenDialog(GraphForm.this);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        int[][] arr = Graph.readFromFile(new FileReader(jfc.getSelectedFile().getPath()));
                        loadGraph(arr);

                    } catch (Exception e1) {
                        e1.printStackTrace();
                        String message = e1.getMessage() == null || e1.getMessage().isEmpty()
                                ? "Неизвестная ошибка (" + e1.toString() + ")"
                                : e1.getMessage();
                        JOptionPane.showMessageDialog(graphPanel, message, "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    System.err.println("Open command cancelled by user.");
                }
            }
        });
        commitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String[] pMatrix = matrixTextArea.getText().split("\n");
                int[][] matrix = new int[pMatrix.length][];
                String[][] p2Matrix = new String[pMatrix.length][];

                for (int i = 0; i < pMatrix.length; i++) {
                    p2Matrix[i] = pMatrix[i].split(" ");
                    matrix[i] = new int[p2Matrix[i].length];
                    for (int j = 0; j < p2Matrix[i].length; j++)
                        matrix[i][j] = Integer.parseInt(p2Matrix[i][j]);
                }
                loadGraph(matrix);
            }
        });
        /*graphPanel.addMouseListener(new MouseAdapter() {
            boolean dragging = false;
            Node draggedNode;

            @Override
            public void mousePressed(MouseEvent e) {
                for(Node n : ((GraphPanel)graphPanel).getNodes())
                    if (n.getView().getBounds().contains(e.getPoint())){
                        setCursor(new Cursor(Cursor.MOVE_CURSOR));
                        dragging = true;
                        draggedNode = n;
                        draggedNode.setState(State.DRAGGED);
                        return;
                    }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                for (Layer l : ((GraphPanel)graphPanel).getLayers())
                    if (l.contains(e.getPoint())){
                        draggedNode.getLayer().getNodes().remove(draggedNode);
                        draggedNode.setLayer(l);
                        draggedNode.setState(State.NORMAL);
                        l.getNodes().add(draggedNode);
                        dragging = false;
                        setCursor(Cursor.getDefaultCursor());
                        repaint();
                        return;
                    }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);    //To change body of overridden methods use File | Settings | File Templates.
            }
        });*/

        buttonAnalyze.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            public void actionPerformed(ActionEvent e) {
                ((GraphPanel)graphPanel).clearAllStates();
                ((GraphPanel)graphPanel).analyzeGraph();
                mainFrame.repaint();
            }
        });
        buttonLoops.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             */
            public void actionPerformed(ActionEvent e) {
                ((GraphPanel)graphPanel).clearAllStates();
                ((GraphPanel)graphPanel).analyzeLoops();
                mainFrame.repaint();
            }
        });
    }

    public void loadGraph(java.util.List<Node> nodesList){
        int[][] arr = new int[nodesList.size()][];
        for (Node n : nodesList) {
            arr[n.getNodeID()] = new int[n.getOutgoingArcs().size()];
            for (Arc a : n.getOutgoingArcs())
                arr[n.getNodeID()][a.getTarget().getNodeID()] = 1;
        }
        loadGraph(arr);
    }

    public void loadGraph(int[][] arr) {
        graph = new Graph(arr.length);
        graph.setGraphFromMatrix(arr);

        loadGraph(graph);
    }

    public void loadGraph(Graph graph) {

        int[][] matrix = graph.buildAdjacencyMatrix();
        String matrixToText = "";
        for (int[] r : matrix) {
            for (int c : r)
                matrixToText += c + " ";
            matrixToText += "\n";
        }

        matrixTextArea.setText(matrixToText);

        graph.findLayers();
        numberOfLayersLabel.setText(String.valueOf(graph.getLayers()));
        ((GraphPanel) graphPanel).getLayers().clear();
        ((GraphPanel) graphPanel).getNodes().clear();
        ((GraphPanel) graphPanel).setGraph(graph);
        mainFrame.repaint();
    }

    public void pleaseRepaint() {
        mainFrame.repaint();
    }

    private void createUIComponents() {
        graphPanel = new GraphPanel(this);
        graphPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        graphPanel.setBackground(new Color(-1));
        graphPanel.setMinimumSize(new Dimension(530, 24));
        graphPanel.setOpaque(true);
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 2, new Insets(0, 0, 0, 0), -1, -1));
        wrapperPanel.setMinimumSize(new Dimension(640, 480));
        wrapperPanel.setPreferredSize(new Dimension(800, 600));
        boringPanel = new JPanel();
        boringPanel.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        boringPanel.setInheritsPopupMenu(false);
        boringPanel.setMaximumSize(new Dimension(200, 2147483647));
        boringPanel.setMinimumSize(new Dimension(100, 174));
        boringPanel.setOpaque(false);
        boringPanel.setPreferredSize(new Dimension(150, 228));
        boringPanel.setRequestFocusEnabled(true);
        wrapperPanel.add(boringPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(100, -1), new Dimension(150, -1), new Dimension(200, -1), 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        boringPanel.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(4, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        captionLabel = new JLabel();
        captionLabel.setText("Label");
        boringPanel.add(captionLabel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        numberOfLayersCaptionLabel = new JLabel();
        numberOfLayersCaptionLabel.setText("Label");
        boringPanel.add(numberOfLayersCaptionLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(82, 14), null, 0, false));
        numberOfLayersLabel = new JLabel();
        numberOfLayersLabel.setText("");
        boringPanel.add(numberOfLayersLabel, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_EAST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        matrixTextArea = new JTextArea();
        boringPanel.add(matrixTextArea, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(82, 50), null, 0, false));
        commitButton = new JButton();
        commitButton.setText("Button");
        boringPanel.add(commitButton, new com.intellij.uiDesigner.core.GridConstraints(3, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        graphPanel.setBackground(new Color(-1));
        graphPanel.setMinimumSize(new Dimension(530, 24));
        graphPanel.setOpaque(true);
        wrapperPanel.add(graphPanel, new com.intellij.uiDesigner.core.GridConstraints(1, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JToolBar toolBar1 = new JToolBar();
        toolBar1.setFloatable(false);
        wrapperPanel.add(toolBar1, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 2, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 20), null, 0, false));
        loadButton = new JButton();
        loadButton.setText("Button");
        toolBar1.add(loadButton);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return wrapperPanel;
    }
}