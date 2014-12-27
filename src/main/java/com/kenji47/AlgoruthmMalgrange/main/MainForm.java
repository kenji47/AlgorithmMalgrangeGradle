package com.kenji47.AlgoruthmMalgrange.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrey on 27.12.2014.
 */
public class MainForm extends JFrame {
    private JTabbedPane tab_pane;
    private JPanel pn1;

    public MainForm() {

        tab_pane=new JTabbedPane();
        //tab_pane.add("Поиск порожденных подграфов",);
        tab_pane.add("Метод Мальгранжа",new MalgrangeTab());

        add(tab_pane);
        setTitle("Теория графов и сетей");
        getContentPane().setLayout(new BoxLayout(this.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new MainForm();
    }


}
