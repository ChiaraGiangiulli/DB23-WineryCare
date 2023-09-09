package it.unibo.winerycare.view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;
import it.unibo.winerycare.model.Features;

/**
 * Class that represents a GUI view for displaying features.
 * It extends JFrame and provides a JComboBox and a JButton for user interaction.
 */
public class FeaturesView extends JFrame{

    private static final int ROWS = 3;
    private static final int COLS = 1;
    private static final int GAP = 10;
    private JComboBox<String> featuresBox;
    private JButton nextButton;

    /**
     * Constructs a FeaturesView with the provided Features.
     *
     * @param features The Features to display.
     */
    public FeaturesView(Features features) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("WineryCare");

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
        featuresBox.addItem("13) Ottieni il fornitore più vantaggioso per un prodotto");
        featuresBox.addItem("14) Ottieni la quantità di vino venduto in un anno");
        featuresBox.addItem("15) Ottieni la quantità di uva raccolta in un anno");
        featuresBox.addItem("16) Ottieni la tipologia di vino più venduta in un anno");
        featuresBox.addItem("17) Controlla il tempo di fermentazione in una vasca");
        

        final ActionListener al = new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                final JFrame frame = new JFrame(); 
                JPanel p;
                final JButton bt;
                List<JTextField> fields = new ArrayList<>();
                final ActionListener act;
                String suppliers;
                switch(featuresBox.getSelectedIndex()+1){
                    case 1: bt = setFrame(frame, "codice cliente", "tipo cliente");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(noneEmpty(fields)){
                                        features.addClient(fields.get(0).getText(), fields.get(1).getText());
                                        JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);  
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    
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
                                    if(noneEmpty(fields)){
                                        if(fields.get(1).getText().length() == 11){
                                            features.addSupplier(fields.get(0).getText(), fields.get(1).getText());
                                            JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(p, "Partita IVA non valida", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                        
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
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
                                    if(noneEmpty(fields)){
                                        try{
                                            if(features.sellProduct(fields.get(0).getText(), fields.get(1).getText(),
                                                fields.get(2).getText(), Integer.valueOf(fields.get(3).getText())) != 0){
                                                    JOptionPane.showMessageDialog(p, "Vendita avvenuta con successo", "", JOptionPane.PLAIN_MESSAGE);
                                                }
                                            else{
                                                JOptionPane.showMessageDialog(p, "Bottiglia inesistente", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                   
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 4: bt = setFrame(frame, "nome ditta manutentrice", "partita IVA ditta");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(noneEmpty(fields)){
                                        if(fields.get(1).getText().length() == 11){
                                            features.addMaintenanceCompany(fields.get(0).getText(), fields.get(1).getText());
                                            JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(p, "Partita IVA non valida", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                                                              
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }

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
                                    if(noneEmpty(fields)){
                                        int n = 0;
                                        try{
                                            n = features.updateSalePrice(fields.get(0).getText(), Double.valueOf(fields.get(1).getText()));
                                            if(n != 0){
                                                JOptionPane.showMessageDialog(p, "Inserimento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(p, "Tipologia non presente", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                    
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }                                    
                                    
                                    
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
                                    if(noneEmpty(fields)){
                                        try{
                                            features.addWineType(fields.get(0).getText(), fields.get(1).getText(), 
                                                Double.valueOf(fields.get(2).getText()));
                                            JOptionPane.showMessageDialog(p, "Aggiornamento avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                     
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                    
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
                            suppliers = "<html> ";
                            for (Supplier supplier : features.getSuppliers()) {
                                suppliers = suppliers + supplier.getName() + ", partita IVA: " + supplier.getPIVA() + ".<br>";
                            }
                            suppliers = suppliers + "</html>";
                            showInfo(suppliers, new JFrame());
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(noneEmpty(fields)){
                                        try{
                                            if(fields.get(1).getText().length() == 11){
                                                features.buyMachinery(fields.get(0).getText(), fields.get(1).getText(), 
                                                Integer.valueOf(fields.get(2).getText()) , fields.get(3).getText());
                                            JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(p, "Partita IVA non valida", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                     
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
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
                            suppliers = "<html> ";
                            for (Supplier supplier : features.getSuppliers()) {
                                suppliers = suppliers + supplier.getName() + ", partita IVA: " + supplier.getPIVA() + ".<br>";
                            }
                            suppliers = suppliers + "</html>";
                            showInfo(suppliers, new JFrame());
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(noneEmpty(fields)){
                                        try{
                                            if(fields.get(1).getText().length() == 11){
                                                features.buyWineProduct(fields.get(0).getText(), fields.get(1).getText(), 
                                                Double.valueOf(fields.get(2).getText()) , fields.get(3).getText());
                                                JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(p, "Partita IVA non valida", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                    
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 9: bt = setFrame(frame, "nome", "codice", "quantità", "partita IVA fornitore");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            fields.add((JTextField)p.getComponent(5));
                            fields.add((JTextField)p.getComponent(7));
                            suppliers = "<html> ";
                            for (Supplier supplier : features.getSuppliers()) {
                                suppliers = suppliers + supplier.getName() + ", partita IVA: " + supplier.getPIVA() + ".<br>";
                            }
                            suppliers = suppliers + "</html>";
                            showInfo(suppliers, new JFrame());
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    if(noneEmpty(fields)){
                                        try{
                                            if(fields.get(1).getText().length() == 11){
                                                features.buyPackagingProduct(fields.get(0).getText(), fields.get(1).getText(), 
                                                    Integer.valueOf(fields.get(2).getText()) , fields.get(3).getText());
                                                JOptionPane.showMessageDialog(p, "Acquisto avvenuto con successo", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(p, "Partita IVA non valida", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                     
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
                                }
                            };
                            bt.addActionListener(act);
                            break;
                    case 10:String stocks = "<html> ";
                            for (WineBottle bottle : features.getStocks()) {
                                stocks = stocks + bottle.getName() + ", anno di produzione: " + bottle.getYear() + ", capacità: " + bottle.getCapacity()
                                    + ", numero di lotto: " + bottle.getLotNumber() + ", numero bottiglia: " + bottle.getBottleNumber()
                                    + ", gradazione alcolica: " + bottle.getAlcoholContent() + ", tipologia: " + bottle.getType() + ".<br>";
                            } 
                            stocks = stocks + "</html>";
                            showInfo(stocks, new JFrame());
                            break;
                    case 11:bt = setFrame(frame, "inizio periodo (AAAA-MM-GG)", "fine periodo (AAAA-MM-GG)");
                            p = (JPanel)frame.getContentPane().getComponent(0);
                            fields.add((JTextField)p.getComponent(1));
                            fields.add((JTextField)p.getComponent(3));
                            act = new ActionListener(){
                                @Override
                                public void actionPerformed(ActionEvent arg0) {
                                    String workers = "";
                                    if(noneEmpty(fields)){
                                        try{
                                            for (Worker worker : features.getWorkers(Date.valueOf(fields.get(0).getText()),
                                                                                    Date.valueOf(fields.get(1).getText()))) {
                                                workers = workers + worker.getName() + " " + worker.getSurname() + ", data di nascita: " + worker.getDateOfBirth()
                                                        + ", indirizzo: " + worker.getAddress() + ", id: " + worker.getId() + ".\n";
                                            }
                                            if(workers.equals("")){
                                                JOptionPane.showMessageDialog(p, "Non ci sono operai con contratto nel periodo indicato", "", JOptionPane.PLAIN_MESSAGE);
                                            }
                                            else{
                                                JOptionPane.showMessageDialog(p, workers, "Operai con contratto nel periodo indicato", JOptionPane.PLAIN_MESSAGE);
                                            }
                                        }
                                        catch(IllegalArgumentException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                    
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
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
                                    if(noneEmpty(fields)){
                                        if(features.getWinePrice(fields.get(0).getText()).isEmpty()){
                                            JOptionPane.showMessageDialog(p, "Tipologia non presente", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(p, features.getWinePrice(fields.get(0).getText()).get(), 
                                                                    "Prezzo tipologia " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                        }
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
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
                                    if(noneEmpty(fields)){
                                        if(features.getBestSupplier(fields.get(0).getText()).isEmpty()){
                                            JOptionPane.showMessageDialog(p, "Prodotto non disponibile", "", JOptionPane.PLAIN_MESSAGE);
                                        }
                                        else{
                                            Supplier supplier = features.getBestSupplier(fields.get(0).getText()).get();
                                            String s = supplier.getName() + ", partita iva: " + supplier.getPIVA();
                                            JOptionPane.showMessageDialog(p, s, "Miglior fornitore ", JOptionPane.PLAIN_MESSAGE);
                                        }                                      
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
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
                                    if(noneEmpty(fields)){
                                        try{
                                            JOptionPane.showMessageDialog(p, features.getAmountOfSoldWine(Integer.valueOf(fields.get(0).getText())), 
                                                                "Vino venduto nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                      
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
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
                                    if(noneEmpty(fields)){
                                        try{
                                            JOptionPane.showMessageDialog(p, features.getAmountOfGrapes(Integer.valueOf(fields.get(0).getText())), 
                                                                "Uva raccolta nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                      
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
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
                                    if(noneEmpty(fields)){
                                        try{
                                            JOptionPane.showMessageDialog(p, features.getBestSellingType(Integer.valueOf(fields.get(0).getText())), 
                                                                "Tipologia più venduta nell'anno " + fields.get(0).getText(), JOptionPane.PLAIN_MESSAGE);
                                        }
                                        catch(NumberFormatException exc){
                                            JOptionPane.showMessageDialog(p, "Valore non valido", "", JOptionPane.PLAIN_MESSAGE);
                                        }                                      
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(p, "I campi non possono essere vuoti", "", JOptionPane.PLAIN_MESSAGE);
                                    }
                                        
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

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    /**
     * Sets up a JFrame with a panel to insert components and a "Ok" button.
     *
     * @param frame The JFrame to set up.
     * @param arg   An array of strings specifying labels for components to insert.
     * @return The "Ok" button component.
     */
    private JButton setFrame(final JFrame frame, final String... arg){
        final JPanel insertPanel = new JPanel(new GridLayout(arg.length, arg.length, 0, GAP));
        insertPanel.setBorder(BorderFactory.createEmptyBorder(GAP, 0, 0, GAP));
        frame.getContentPane().add(insertPanel);
        final JPanel buttonPanel = new JPanel();
        frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
        JButton okButton = new JButton("Ok");
        for (String string : arg) {
            insertPanel.add(new JLabel("Inserisci " + string, SwingConstants.CENTER));
            insertPanel.add(new JTextField(20));
        }
        buttonPanel.add(okButton);
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return okButton;
    }

    /**
     * Displays information in a new JPanel within the specified JFrame.
     *
     * @param info  The information to display.
     * @param frame The JFrame to display the information in.
     */
    private void showInfo(final String info, final JFrame frame){
        final JPanel newPanel= new JPanel();
        
        frame.getContentPane().add(newPanel);
        
        newPanel.add(new JLabel(info));
        
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    /**
     * Checks if all the provided JTextFields in the list are non-empty.
     *
     * @param fields A list of JTextFields to check.
     * @return true if all fields are non-empty, false otherwise.
     */
    private boolean noneEmpty(final List<JTextField> fields){
        for (JTextField jTextField : fields) {
            if(jTextField.getText().length() == 0) return false;
        }
        return true;
    }

}
