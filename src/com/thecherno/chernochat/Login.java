package com.thecherno.chernochat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by usuario on 8/09/14.
 */
public class Login extends JFrame{

    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtAddress;
    private JLabel lblipAddress;
    private JTextField txtPort;
    private JLabel lblPort;
    private JLabel lblAddressDesc;
    private JLabel lblPortDesc;

    /**
     * Create the frame.
     */

    public Login() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setResizable(false);
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 380);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtName = new JTextField();
        txtName.setBounds(67, 50, 165, 28);
        contentPane.add(txtName);
        txtName.setColumns(10);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(127, 34, 45, 16);
        contentPane.add(lblName);

        txtAddress = new JTextField();
        txtAddress.setBounds(67, 116, 165, 28);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        lblipAddress = new JLabel("IP Address:");
        lblipAddress.setBounds(111, 96, 77, 16);
        contentPane.add(lblipAddress);

        txtPort = new JTextField();
        txtPort.setBounds(67, 191, 165, 28);
        txtPort.setColumns(10);
        contentPane.add(txtPort);

        lblPort = new JLabel("Port:");
        lblPort.setBounds(133, 171, 34, 16);
        contentPane.add(lblPort);

        lblAddressDesc = new JLabel("(eg. 192.168.0.2)");
        lblAddressDesc.setBounds(94, 142, 112, 16);
        contentPane.add(lblAddressDesc);

        lblPortDesc = new JLabel("(eg. 8192)");
        lblPortDesc.setBounds(116, 218, 68, 16);
        contentPane.add(lblPortDesc);

        JButton btnLogin = new JButton("Login");
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtName.getText();
                String address = txtAddress.getText();
                int port = Integer.parseInt(txtPort.getText());
                login(name, address, port);
            }
        });
        btnLogin.setBounds(91, 311, 117, 29);
        contentPane.add(btnLogin);


    }

    private void login(String name, String address, int port) {
        dispose();
        new Client(name, address, port);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try{
                    Login frame = new Login();
                    frame.setVisible(true);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
    }
}
