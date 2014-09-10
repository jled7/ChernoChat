package com.thecherno.chernochat;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.net.*;

/**
 * Created by usuario on 8/09/14.
 */
public class Client extends JFrame{
    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private String name, address;
    private int port;
    private JTextField txtMessage;
    private JTextArea txtrHistory;
    private DefaultCaret defaultCaret;

    private DatagramSocket socket;
    private InetAddress ip;

    private Thread send;

    public Client(String name, String address, int port) {
        setTitle("Cherno Chat Client");
        this.name = name;
        this.address = address;
        this.port = port;
        createWindow();
        boolean connect = openConnection(address, port);
        if(!connect) {
            System.err.println("Connection failed!");
            console("Connection failed!");
        }
        console("Attempting a connection to " + address + ":" + port + ", user: " +name);
        console(receive());
    }

    private boolean openConnection(String address, int port) {
        try {
            socket = new DatagramSocket(port);
            ip = InetAddress.getByName(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return false;
        } catch (SocketException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    private String receive() {
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data, data.length);
        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String message = new String(packet.getData());
        return message;
    }

    private void send(final byte[] data){
        send = new Thread(){
            public void run() {
                DatagramPacket packet = new DatagramPacket(data, data.length, ip, port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        send.start();
    }

    private void createWindow(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(880, 550);
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths = new int[]{28, 815, 30, 7}; // SUM = 880
        gbl_contentPane.rowHeights = new int[]{35, 475, 40};  // SUM = 550
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0};
        gbl_contentPane.rowWeights = new double[]{1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        txtrHistory = new JTextArea();
        txtrHistory.setEditable(false);
        JScrollPane scroll = new JScrollPane(txtrHistory);
        defaultCaret = (DefaultCaret) txtrHistory.getCaret();
        defaultCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        GridBagConstraints gbc_txtrHistory = new GridBagConstraints();
        gbc_txtrHistory.insets = new Insets(0, 0, 5, 5);
        gbc_txtrHistory.fill = GridBagConstraints.BOTH;
        gbc_txtrHistory.gridx = 0;
        gbc_txtrHistory.gridy = 0;
        gbc_txtrHistory.gridwidth = 3;
        gbc_txtrHistory.gridheight = 2;
        gbc_txtrHistory.insets = new Insets(0, 5, 0, 0);
        contentPane.add(scroll, gbc_txtrHistory);

        txtMessage = new JTextField();
        txtMessage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send(txtMessage.getText());
                }


            }
        });
        GridBagConstraints gbc_txtMessage = new GridBagConstraints();
        gbc_txtrHistory.insets = new Insets(0, 0, 0, 5);
        gbc_txtMessage.fill = GridBagConstraints.HORIZONTAL;
        gbc_txtMessage.gridx = 1;
        gbc_txtMessage.gridy = 2;
        contentPane.add(txtMessage, gbc_txtMessage);
        txtMessage.setColumns(10);

        JButton btnSend = new JButton("Send");
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send(txtMessage.getText());
            }
        });
        GridBagConstraints gbc_btnSend = new GridBagConstraints();
        gbc_btnSend.insets = new Insets(0, 0, 0, 5);
        gbc_btnSend.gridx = 2;
        gbc_btnSend.gridy = 2;
        contentPane.add(btnSend,gbc_btnSend);


        setVisible(true);
        txtMessage.requestFocusInWindow();
    }
    private void send(String message) {
        if(message.equals("")) return;
        message = name +": " + message;
        console(message);
        txtMessage.setText("");
    }

    public void console(String message) {
        txtrHistory.append(message + "\n\r");
        txtrHistory.setCaretPosition(txtrHistory.getDocument().getLength());
    }
}
