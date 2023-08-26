package it.unibo.winerycare.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;
import it.unibo.winerycare.model.Features;

public class FeaturesView extends JFrame{

    private static final int ROWS = 3;
    private static final int COLS = 1;
    private static final int GAP = 10;
    private JComboBox<String> featuresBox;
    private JButton nextButton;

    public FeaturesView(Features features) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("WineryCare");
        setLocationRelativeTo(null); 

        final JPanel panel = new JPanel(new GridLayout(ROWS, COLS, 0, GAP));
        this.getContentPane().add(panel);

        featuresBox = new JComboBox<>();
        nextButton = new JButton("Avanti");

        panel.add(new JLabel("Operazioni:"));
        panel.add(featuresBox);
        panel.add(nextButton);

        featuresBox.addItem("1) Registra nuovi clienti");
        featuresBox.addItem("2) Registra nuovi fornitori");
        featuresBox.addItem("3) Registra la vendita di un prodotto finito ad un cliente");
        featuresBox.addItem("4) Registrare nuove ditte manutentrici");
        featuresBox.addItem("5) Aggiorna prezzi di vendita");
        featuresBox.addItem("6) Aggiungi tipologia di uva e vino venduta");
        featuresBox.addItem("7) Registrare l’acquisto di un macchinario da un dato fornitore");
        featuresBox.addItem("8) Registrare l’acquisto di un nuovo prodotto enologico da un dato fornitore");
        featuresBox.addItem("9) Registrare l’acquisto di un nuovo prodotto per l'imballaggio da un dato fornitore");
        featuresBox.addItem("10) Visualizza giacenze in magazzino");
        featuresBox.addItem("11) Visualizza la lista di operai che hanno un contratto in un dato periodo");
        featuresBox.addItem("12) Visualizza il prezzo di una data tipologia di vino");
        featuresBox.addItem("13) Ottieni il fornitore più vantaggioso per prodotto");
        featuresBox.addItem("14) Ottieni la quantità di vino venduto per anno");
        featuresBox.addItem("15) Ottieni la quantità di uva raccolta per anno");
        featuresBox.addItem("16) Ottieni la tipologia di vino più venduta per anno");
        featuresBox.addItem("17) Controlla il tempo di fermentazione in una vasca");
        

        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame = new JFrame(); 
                JPanel p;
                final JButton bt;
                List<JTextField> fields = new ArrayList<>();
                final ActionListener act;
                switch(featuresBox.getSelectedIndex()+1){
                    case 1: bt = setFrame(frame, "codice cliente", "tipo cliente");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.addClient(fields.get(0).getText(), fields.get(1).getText());
                                    JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 2: bt = setFrame(frame, "nome fornitore", "partita IVA fornitore");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.addSupplier(fields.get(0).getText(), fields.get(1).getText());
                                    JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 3: bt = setFrame(frame, "codice cliente", "tipologia cliente", "numero di lotto bottiglia", "numero di bottiglia");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            fields.add((JTextField)p.getComponent(7));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.sellProduct(fields.get(0).getText(), fields.get(1).getText(),
                                        fields.get(2).getText(), Integer.valueOf(fields.get(3).getText()));
                                        JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 4: bt = setFrame(frame, "nome ditta manutentrice", "partita IVA ditta");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            System.out.println(fields.size());
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    System.out.println(fields.size());
                                    features.addMaintenanceCompany(fields.get(0).getText(), fields.get(1).getText());
                                    JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 5: bt = setFrame(frame, "nome tipo", "nuovo prezzo");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.updateSalePrice(fields.get(0).getText(), Double.valueOf(fields.get(1).getText()));
                                    JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 6: bt = setFrame(frame,"nome tipologia", "origine", "prezzo");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.addWineType(fields.get(0).getText(), fields.get(1).getText(), 
                                        Double.valueOf(fields.get(2).getText()));
                                        JOptionPane.showMessageDialog(p, "Aggiornamento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 7: bt = setFrame(frame, "nome", "codice", "anno di produzione", "partita IVA fornitore");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            fields.add((JTextField)p.getComponent(7));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.buyMachinery(fields.get(0).getText(), fields.get(1).getText(), 
                                        Integer.valueOf(fields.get(2).getText()) , fields.get(3).getText());
                                        JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 8: bt = setFrame(frame, "nome", "codice", "peso", "partita IVA fornitore");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            fields.add((JTextField)p.getComponent(7));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.buyWineProduct(fields.get(0).getText(), fields.get(1).getText(), 
                                        Double.valueOf(fields.get(2).getText()) , fields.get(3).getText());
                                        JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 9: bt = setFrame(frame, "nome", "codice", "tipologia", "partita IVA fornitore");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            fields.add((JTextField)p.getComponent(7));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    features.buyPackagingProduct(fields.get(0).getText(), fields.get(1).getText(), 
                                        fields.get(2).getText() , fields.get(3).getText());
                                        JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 10:String stocks = " ";
                            for (WineBottle bottle : features.getStocks()) {
                                stocks = stocks + bottle.getName() + ", anno di produzione: " + bottle.getYear() + ", capacità: " + bottle.getCapacity()
                                    + ", numero di lotto: " + bottle.getLotNumber() + ", numero bottiglia: " + bottle.getBottleNumber()
                                    + ", gradazione alcolica: " + bottle.getAlcoholContent() + ", tipologia: " + bottle.getType() + ".\n";
                            } 
                            JOptionPane.showMessageDialog(panel, stocks, "Giacenze", JOptionPane.PLAIN_MESSAGE);
                            break;
                    case 11:bt = setFrame(frame, "inizio periodo (AAAA-MM-GG)", "fine periodo (AAAA-MM-GG)");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    String workers = "";
                                    for (Worker worker : features.getWorkers(Date.valueOf(fields.get(0).getText()),
                                                                             Date.valueOf(fields.get(1).getText()))) {
                                        workers = workers + worker.getName() + " " + worker.getSurname() + ", data di nascita: " + worker.getDateOfBirth()
                                                + ", indirizzo: " + worker.getAddress() + ", id: " + worker.getId() + ".\n";
                                    }
                                    JOptionPane.showMessageDialog(p, workers, "Operai con contratto nel periodo indicato", JOptionPane.PLAIN_MESSAGE);
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 12:bt = setFrame(frame, "tipologia");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(features.getWinePrice(fields.get(0).getText()).isEmpty()){
                                        JOptionPane.showMessageDialog(p, "Tipologia non presente", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, features.getWinePrice(fields.get(0).getText()).get(), 
                                                                "Prezzo tipologia " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                    } 
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 13:bt = setFrame(frame, "nome prodotto");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(features.getBestSupplier(fields.get(0).getText()).isEmpty()){
                                        JOptionPane.showMessageDialog(p, "Prodotto non disponibile", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    else{
                                        Supplier supplier = features.getBestSupplier(fields.get(0).getText()).get();
                                        String s = supplier.getName() + ", partita iva: " + supplier.getPIVA();
                                        JOptionPane.showMessageDialog(p, s, "Miglior fornitore ", JOptionPane.PLAIN_MESSAGE);
                                    }
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 14:bt = setFrame(frame, "anno");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    JOptionPane.showMessageDialog(p, features.getAmountOfSoldWine(Integer.valueOf(fields.get(0).getText())), 
                                                            "Vino venduto nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 15:bt = setFrame(frame, "anno");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    JOptionPane.showMessageDialog(p, features.getAmountOfGrapes(Integer.valueOf(fields.get(0).getText())), 
                                                            "Uva raccolta nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 16:bt = setFrame(frame, "anno");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    JOptionPane.showMessageDialog(p, features.getBestSellingType(Integer.valueOf(fields.get(0).getText())), 
                                                            "Tipologia più venduta nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                } 
                            };
                            bt.addActionListener(act);
                            break;
                    case 17:if(features.getDaysLeft().isEmpty()){
                                JOptionPane.showMessageDialog(panel, "Non ci sono fermentazioni in corso", "", JOptionPane.PLAIN_MESSAGE);
                            }
                            else{
                                JOptionPane.showMessageDialog(panel, features.getDaysLeft(), "Giorni rimasti fermentazione", JOptionPane.PLAIN_MESSAGE);
                            }
                            break;
                }
            }

        };
        nextButton.addActionListener(al);

        pack();
        this.setVisible(true);
    }


    private JButton setFrame(final JFrame frame, final String... arg){
        final JPanel insertPanel = new JPanel(new GridLayout(arg.length, arg.length, 0, GAP));
        frame.getContentPane().add(insertPanel);
        final JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okButton = new JButton("Ok");
        for (String string : arg) {
            insertPanel.add(new JLabel("Inserisci " + string));
            insertPanel.add(new JTextField(20));
        }
        buttonPanel.add(okButton);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        return okButton;
    }

}
