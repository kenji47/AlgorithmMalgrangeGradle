package com.kenji47.AlgoruthmMalgrange.main;

import com.kenji47.AlgoruthmMalgrange.algorithms.MalgrangeAlgorithm;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrey on 28.12.2014.
 */
public class GeneratedSubgraphTab extends JPanel implements ActionListener {
    JTable table;
    DefaultTableModel dtm;

    JLabel lbDefinition;
    JLabel lbGraphName;
    JLabel lbResult;
    JLabel lbVertexQuantity;
    JLabel lbStartVertex;
    JTextField tfStartVertex;

    JButton btRandomFill;
    JButton btSolve;

    JTextField tfVertexQuantity;

    JTextArea text_area;

    int[][] matrix;
    int vertex;
    int size_matrix;

    public  GeneratedSubgraphTab(){
        matrix=new int[][]{
                {0,0,0,0,0,0,1,0,0,0,0},
                {1,1,0,0,0,0,0,1,0,0,1},
                {0,0,1,1,0,0,0,0,1,1,0},
                {0,0,0,1,1,0,0,0,0,0,0},
                {0,0,0,1,0,0,0,0,0,0,0},
                {0,0,0,0,0,1,0,1,0,0,0},
                {0,0,0,0,0,0,0,0,0,0,1},
                {0,0,0,0,0,0,0,0,0,0,0},
                {0,0,1,0,0,0,0,0,0,0,0},
                {1,0,0,1,1,0,0,0,1,0,0},
                {1,0,0,0,1,1,0,0,0,0,1}};
        //vertex=1;

        lbDefinition=new JLabel("Описание: поиск порожденных графов");
        Font font=new Font("Colibri", 0,18);
        lbDefinition.setFont(font);

        lbStartVertex=new JLabel("Начальная вершина разбиения");
        tfStartVertex=new JTextField("1");

        lbGraphName=new JLabel("Матрица смежности графа");
        lbVertexQuantity=new JLabel("Размерность графа");
        tfVertexQuantity=new JTextField();
        tfVertexQuantity.setText("5");
        size_matrix=Integer.parseInt(tfVertexQuantity.getText());
        setMatrixEmpty();

        tfVertexQuantity.addActionListener(this);
        tfVertexQuantity.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                size_matrix=Integer.parseInt(tfVertexQuantity.getText());
                setMatrixEmpty();
                fillTable();
                //table.repaint();
                System.out.println("!");
            }

            @Override
            public void removeUpdate(DocumentEvent e) {

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                size_matrix=Integer.parseInt(tfVertexQuantity.getText());
                setMatrixEmpty();
                fillTable();
                System.out.println("!");
            }
        });

        lbResult=new JLabel("Результат расчетов");

        btRandomFill=new JButton("Заполнить граф случайно");
        btRandomFill.setActionCommand("RandomFill");
        btRandomFill.addActionListener(this);

        btSolve=new JButton("Решить");
        btSolve.setActionCommand("Solve");
        btSolve.addActionListener(this);


        table = new javax.swing.JTable() {

            @Override
            public Component prepareRenderer(
                    TableCellRenderer renderer, int row, int col) {
                if (col == 0) {
                    return this.getTableHeader().getDefaultRenderer()
                            .getTableCellRendererComponent(this, this.getValueAt(
                                    row, col), false, false, row, col);
                } else {
                    return super.prepareRenderer(renderer, row, col);
                }
            }
        };

        table.setAutoCreateRowSorter(false);
        //table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));

        dtm=new DefaultTableModel();
        dtm.setDataVector(getTableData(),getTableHeader());
        table.setModel(dtm);

        //table.setPreferredSize(new Dimension(400,400));



        text_area=new JTextArea();

        JScrollPane scrollPane =new JScrollPane(text_area);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        setLayout(new BoxLayout(this,javax.swing.BoxLayout.Y_AXIS));
        scrollPane.setPreferredSize(new Dimension(400,600));

        add(Box.createRigidArea(new Dimension(0, 10)));
        add(lbDefinition);
        add(Box.createRigidArea(new Dimension(0,10)));

        JPanel panel=new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));
        panel.add(lbVertexQuantity);
        panel.add(tfVertexQuantity);
        panel.add(lbStartVertex);
        panel.add(tfStartVertex);
        panel.add(btRandomFill);
        panel.add(btSolve);

        add(panel);
        add(Box.createRigidArea(new Dimension(0,10)));

        add(lbGraphName);
        add(Box.createRigidArea(new Dimension(0,10)));


        JScrollPane scrollPane2 =new JScrollPane(table);

        add(scrollPane2);
        //add(new JScrollPane(table));
        add(Box.createRigidArea(new Dimension(0,10)));

        add(lbResult);
        add(Box.createRigidArea(new Dimension(0,10)));
        add(scrollPane);




//        MalgrangeAlgorithm alg=new MalgrangeAlgorithm(matrix,vertex,size_matrix);
//        System.out.println("OUTPUT");
//        for(StringBuilder c: alg.output){
//            System.out.print(c);
//            text_area.append(c.toString());
//            text_area.append("\n");
//            System.out.println("");
//        }

    }
    private void fillTable(){
        dtm.setDataVector(getTableData(),getTableHeader());
        table.setModel(dtm);
    }
    private void setMatrixEmpty(){
        matrix=new int[size_matrix][size_matrix];
        for(int n=0; n<size_matrix; n++){
            for(int c=0; c<size_matrix; c++)
            {
                matrix[n][c]=0;
            }
        }
    }
    private void getTableDataToMatrix(){
        for (int n=0; n<size_matrix; n++){
            for(int c=0; c<size_matrix; c++){
                matrix[n][c]=Integer.parseInt(dtm.getValueAt(n,c+1).toString());

            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Solve":
                text_area.setText("");
                getTableDataToMatrix();

                for(int n=0; n<size_matrix; n++) {
                    for (int c = 0; c < size_matrix; c++){
                        System.out.print(matrix[n][c]);
                    }
                    System.out.println();
                }
                MalgrangeAlgorithm alg=new MalgrangeAlgorithm(matrix,Integer.parseInt(tfStartVertex.getText()),size_matrix);
                System.out.println("OUTPUT");
                for(StringBuilder c: alg.output){
                    System.out.print(c);
                    text_area.append(c.toString());
                    text_area.append("\n");
                    System.out.println("");
                }

                break;
            case "RandomFill":
                double d;
                for(int n=0; n<size_matrix; n++){
                    for(int c=0; c<size_matrix; c++)
                    {

                        d=Math.random();
                        if(d<=0.5) matrix[n][c]=0;
                        else matrix[n][c]=1;
                    }
                }
                fillTable();

                break;
    }
    }
    private static class HeaderRenderer implements TableCellRenderer {

        TableCellRenderer renderer;

        public HeaderRenderer(JTable table) {
            renderer = table.getTableHeader().getDefaultRenderer();
        }

        @Override
        public Component getTableCellRendererComponent(
                JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int col) {
            return renderer.getTableCellRendererComponent(
                    table, value, isSelected, hasFocus, row, col);
        }
    }

    Object[][] getTableData(){
        Object[][] object=new Object[size_matrix][size_matrix+1];
        for(int n=0; n<size_matrix; n++){
            object[n][0]="x"+(n+1);
            for(int t=0; t<size_matrix; t++){
                object[n][t+1]=matrix[n][t];
            }
        }
        return object;
    }
    String[] getTableHeader(){
        String[] header=new String[size_matrix+1];
        header[0]="";
        for(int n=1; n<size_matrix+1; n++) header[n]="x"+n;

        return header;
    }
}
