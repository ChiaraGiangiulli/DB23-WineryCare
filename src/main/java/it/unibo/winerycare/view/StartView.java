package it.unibo.winerycare.view;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.winerycare.db.ConnectionProvider;
import it.unibo.winerycare.model.Features;
import it.unibo.winerycare.model.FeaturesImpl;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class StartView extends JFrame{

    private static final int DIM = 700;
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final int TOPBORDER = 300;
    private static final int BOTTOMBORDER = 200;
    private static final int GAP = 20;
    protected static final String DB_NAME = "winerycare";
    private JPasswordField password;
    private JTextField username;
    private JButton loginButton;
    protected Features features;

    
    public StartView(){

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("WineryCare");
        this.setSize(DIM, DIM);
        setLocationRelativeTo(null); 

        final Image image = new ImageIcon("src\\resources\\background.png").getImage();
        final JPanel panel = new JPanel(new GridLayout(ROWS, COLS, 0, GAP)){
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(TOPBORDER, GAP, BOTTOMBORDER, GAP*4));
        this.getContentPane().add(panel, BorderLayout.CENTER);
        final JPanel loginPanel = new JPanel();
        this.getContentPane().add(loginPanel, BorderLayout.SOUTH);

        username = new JTextField(20);
        password = new JPasswordField(20);

        loginButton = new JButton("Login");

        panel.add(new JLabel("Inserisci il tuo username:   ", SwingConstants.RIGHT));
        panel.add(username);
        panel.add(new JLabel("Inserisci la tua password:   ", SwingConstants.RIGHT));
        panel.add(password);
        loginPanel.add(loginButton, BorderLayout.CENTER);

        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final ConnectionProvider prov = new ConnectionProvider(username.getText(), String.valueOf(password.getPassword()), DB_NAME);
                    final Connection connection = prov.getMySQLConnection();
                    features = new FeaturesImpl(connection);
                    new FeaturesView(features);
                } catch (IllegalStateException exception) {
                    throw new IllegalStateException("Error", exception);
                }
            }

        };
        loginButton.addActionListener(al);
        this.setVisible(true);
        
    }
}
