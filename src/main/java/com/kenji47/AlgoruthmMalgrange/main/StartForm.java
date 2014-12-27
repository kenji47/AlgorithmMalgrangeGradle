package com.kenji47.AlgoruthmMalgrange.main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andrey on 27.12.2014.
 */
public class StartForm extends JFrame implements ActionListener{

    JTextField tfSize;
    JLabel lbSize;

    JButton btOK;
    JButton btDefault;

    public StartForm(){

        lbSize=new JLabel("Matrix size:");
        tfSize=new JTextField();

        btOK =new JButton("OK");
        btOK.setActionCommand("OK");
        btOK.addActionListener(this);



        getContentPane().setLayout(new BoxLayout(this.getContentPane(), javax.swing.BoxLayout.Y_AXIS));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        pack();
        setVisible(true);
    }
    public static void main(String[] args) {
        new StartForm();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
