package it.unibo.winerycare.view;

import javax.swing.BorderFactory;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import it.unibo.winerycare.db.ConnectionProvider;
import it.unibo.winerycare.model.Features;
import it.unibo.winerycare.model.FeaturesImpl;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;


public class StartView extends JFrame{

    private static final int DIM = 700;
    private static final int ROWS = 3;
    private static final int COLS = 2;
    private static final int TOPBORDER = 250;
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
        
        /*ImageIcon backgroundImage = new ImageIcon("src\\resources\\background.jpg");
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, backgroundImage.getIconWidth(), backgroundImage.getIconHeight());
        this.getContentPane().add(backgroundLabel);*/

        final JPanel panel = new JPanel(new GridLayout(ROWS, COLS, 0, GAP));
        panel.setBorder(BorderFactory.createEmptyBorder(TOPBORDER, GAP, BOTTOMBORDER, GAP));
        this.getContentPane().add(panel, BorderLayout.CENTER);

        final JPanel loginPanel = new JPanel();
        this.getContentPane().add(loginPanel, BorderLayout.SOUTH);

        final JPanel welcomePanel = new JPanel();
        welcomePanel.setBorder(BorderFactory.createEmptyBorder(GAP, 0, 0, 0));
        this.getContentPane().add(welcomePanel, BorderLayout.NORTH);

        username = new JTextField(20);
        password = new JPasswordField(20);
        loginButton = new JButton("Login");

        panel.add(new JLabel("Inserisci il tuo username: "));
        panel.add(username);
        panel.add(new JLabel("Inserisci la tua password: "));
        panel.add(password);
        loginPanel.add(loginButton, BorderLayout.CENTER);
        welcomePanel.add(new JLabel("Benvenuto su WineryCare!"));

        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                try {
                    final ConnectionProvider prov = new ConnectionProvider(username.getText(), String.valueOf(password.getPassword()), DB_NAME);
                    final Connection connection = prov.getMySQLConnection();
                    features = new FeaturesImpl(connection);
                    new FeaturesView(features);
                    dispose();
                } catch (IllegalStateException exception) {
                    throw new IllegalStateException("Error", exception);
                }
            }

        };
        loginButton.addActionListener(al);
        
        this.setVisible(true);
        
    }
}
