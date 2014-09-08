package com.thecherno.chernochat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * Created by usuario on 8/09/14.
 */
public class Client extends JFrame{
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private String name, address;
    private int port;

    public Client(String name, String address, int port) {
        setTitle("Cherno Chat Client");
        this.name = name;
        this.address = address;
        this.port = port;
        createWindow();

    }

    private void createWindow(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{20, 855, 5}; // SUM = 880
        gbl_contentPane.rowHeights = new int[]{30, 480, 40};  // SUM = 550
        gbl_contentPane.columnWeights = new double[]{1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        JTextArea txtrHistory = new JTextArea();
        GridBagConstraints gbc_txtrHistory = new GridBagConstraints();
        gbc_txtrHistory.fill = GridBagConstraints.BOTH;
        gbc_txtrHistory.gridx = 1;
        gbc_txtrHistory.gridy = 1;
        contentPane.add(txtrHistory, gbc_txtrHistory);

        setVisible(true);
    }
}
