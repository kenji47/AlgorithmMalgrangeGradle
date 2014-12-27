package com.kenji47.AlgoruthmMalgrange.main;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Created by Andrey on 25.12.2014.
 */
public class MainForm {
    JFrame jfrm;
    JTable table;
    JTextArea text_area;

    int[][] matrix;
    int vertex;
    int size_matrix=11;



    public MainForm(){

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
        vertex=1;

        jfrm=new JFrame("Malgrange Algorithm");
        jfrm.getContentPane().setLayout(new BoxLayout(jfrm.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        jfrm.setSize(800, 800);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


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
        final JTableHeader header = table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer(table));

        table.setModel(new javax.swing.table.DefaultTableModel(
                getTableData(),
                getTableHeader()));

        table.setPreferredSize(new Dimension(400,400));

        text_area=new JTextArea();
        //text_area.setPreferredSize(new Dimension(400,400));

        JScrollPane scrollPane =new JScrollPane(text_area);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        jfrm.add(new JScrollPane(table));
        jfrm.add(scrollPane);

        jfrm.setLocationRelativeTo(null);
        jfrm.setVisible(true);


        Algorithm alg=new Algorithm(matrix,vertex,size_matrix);
        System.out.println("OUTPUT");
        for(StringBuilder c: alg.output){
                System.out.print(c);
            text_area.append(c.toString());
            text_area.append("\n");
            System.out.println("");
        }

    }

    public static void main(String[] args) {
        new MainForm();

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

//        for(int n=0; n<size_matrix; n++){
//            for(int t=0; t<size_matrix; t++){
//                System.out.print(object[n][t]+" ");
//            }
//            System.out.println();
//        }

        return object;
    }
    String[] getTableHeader(){
        String[] header=new String[size_matrix+1];
        header[0]="";
        for(int n=1; n<size_matrix+1; n++) header[n]="x"+n;

        return header;
    }



}
