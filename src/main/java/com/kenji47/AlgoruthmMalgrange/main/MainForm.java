package com.kenji47.AlgoruthmMalgrange.main;

import com.kenji47.AlgoruthmMalgrange.algorithms.GeneratedSubgraphSearch;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Andrey on 27.12.2014.
 */
public class MainForm extends JFrame {
    private JTabbedPane tab_pane;
     JPanel pn1;

    public MainForm() {

        tab_pane=new JTabbedPane();
        tab_pane.add("Поиск порожденных подграфов",new GeneratedSubgraphSearch());
        tab_pane.add("Метод Мальгранжа",new MalgrangeTab(this));

        add(tab_pane);
        setTitle("Теория графов и сетей");
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600,750));
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainForm();
    }


}
