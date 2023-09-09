package it.unibo.winerycare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.swing.JOptionPane;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;
import it.unibo.winerycare.utils.Utils;

/**
 * Implementation of the Features interface.
 */
public class FeaturesImpl implements Features {

    private final Connection connection;

    /**
     * Constructs a FeaturesImpl instance with a database connection.
     *
     * @param connection The database connection.
     */
    public FeaturesImpl(final Connection connection) {
        this.connection = connection;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void addClient(final String code, final String type) {
        final String query = "INSERT INTO clienti (Codice, Tipologia)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, code);
            statement.setString(2, type);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addSupplier(final String name, final String pIva) {
        final String query = "INSERT INTO fornitori (Nome, Partita_IVA)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, pIva);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * Retrieves the price of a product with a specific name from a supplier with a given P.IVA.
     *
     * @param pIva The Partita IVA of the supplier.
     * @param name The name of the product.
     * @return The price of the product.
     * @throws IllegalStateException If a database error occurs.
     */
    private Double getProductPrice(String pIva, String name) {
        final String query = "SELECT l.Prezzo AS p\n" + //
                            "FROM prodotti_di_listino l\n" + //
                            "WHERE l.Partita_IVA_fornitore = ? AND l.Nome = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(2, name);
            statement.setString(1, pIva);
            final ResultSet result = statement.executeQuery();
            return result.getDouble("l.Prezzo");
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * Retrieves a list of all suppliers in the WineryCare system.
     *
     * @return A list of Supplier objects representing the suppliers.
     * @throws IllegalStateException If a database error occurs.
     */
    public List<Supplier> getSuppliers(){
        final String query = "SELECT *\n" + //
                            "FROM fornitori";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            List<Supplier> suppliers = new ArrayList<>();
            while(result.next()){
                suppliers.add(new Supplier(result.getString("Nome"),result.getString("Partita_IVA")));
            }
            return suppliers;
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyMachinery(final String name, final String code ,final int productionYear, final String pIva) {
        final Double price = this.getProductPrice(pIva, name);
        final String query = "INSERT INTO macchinari(Nome, Data_acquisto, Prezzo, Codice, Anno_di_produzione, \n" + //
                                                    "Partita_IVA_fornitore)\n" + //
                            "VALUES(?, CURRENT_DATE(), p, ?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, code);
            statement.setInt(4, productionYear);
            statement.setString(5, pIva);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyWineProduct(final String name, final String code, final Double weight, final String pIva) {
        final Double price = this.getProductPrice(pIva, name);
        final String query = "INSERT INTO prodotti_enologici(Nome, Data_acquisto, Prezzo, Codice, Peso, Partita_IVA_fornitore,"  + //
                                                            "Nome_fase, Inizio_fase, Fine_fase)\n" + //
                            "VALUES(?, CURRENT_DATE(), p, ?, ?, ?, NULL, NULL, NULL)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, code);
            statement.setDouble(4, weight);
            statement.setString(5, pIva);
            statement.setString(6, null);
            statement.setDate(7, null);
            statement.setDate(8, null);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void buyPackagingProduct(final String name, final String code ,final int quantity, final String pIva) {
        final Double price = this.getProductPrice(pIva, name);
        final String query = "INSERT INTO prodotti_per_imballaggio(Nome, Data_acquisto, Prezzo, Codice, Quantita, Partita_IVA_fornitore, \n" + //
                                                                    "Nome_fase, Inizio_fase, Fine_fase)\n" + //
                            "VALUES(?, CURRENT_DATE(), p, ?, ?, ?, NULL, NULL, NULL)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, code);
            statement.setInt(4, quantity);
            statement.setString(5, pIva);
            statement.setString(6, null);
            statement.setDate(7, null);
            statement.setDate(8, null);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int sellProduct(final String clientCode, final String clientType, final String lot, final int bottleNum) {
        Optional<Double> price = Optional.empty();
        final String firstQuery = "INSERT IGNORE INTO clienti(Codice, Tipologia) VALUES (?,?);\n";
        try (PreparedStatement statement = this.connection.prepareStatement(firstQuery)) {
            statement.setString(1, clientCode);
            statement.setString(2, clientType);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
        final String secondQuery = "SELECT t.Prezzo_di_vendita as prezzo\n" + //
                                    "FROM tipologie t, bottiglie_di_vino b\n" + //
                                    "WHERE  b.Numero_di_lotto = ? AND b.Numero_bottiglia = ? AND t.Nome = b.Nome_tipologia\n";
        try (PreparedStatement statement = this.connection.prepareStatement(secondQuery)) {
            statement.setString(1, lot);
            statement.setInt(2, bottleNum);
            final ResultSet result = statement.executeQuery();
            if(result.next()){
                price = Optional.of(result.getDouble("prezzo"));
            }
            else{
                price = Optional.empty();
                return 0;
            }
            
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }

        final String thirdQuery = "UPDATE bottiglie_di_vino AS bott\n" + //
                                    "SET Giacenza = FALSE, Codice_cliente = ?, Data_acquisto = CURRENT_DATE(), Importo_acquisto = ?* bott.Capacita\n" + //
                                    "WHERE bott.Numero_di_lotto = ? AND bott.Numero_bottiglia = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(thirdQuery)) {
            statement.setString(1, clientCode);
            statement.setDouble(2, price.get());
            statement.setString(3, lot);
            statement.setInt(4, bottleNum);
            int updatedRows = statement.executeUpdate();
            return updatedRows;
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addMaintenanceCompany(final String name, final String pIva) {
        final String query = "INSERT INTO ditte (Nome, Partita_IVA)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, pIva);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int updateSalePrice(String name, Double newPrice) {
        final String query = "UPDATE tipologie AS t\n" + //
                            "SET t.Prezzo_di_vendita = ?\n" + //
                            "WHERE t.Nome = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(2, name);
            statement.setDouble(1, newPrice);
            int updatedRows = statement.executeUpdate();
            return updatedRows;
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addWineType(String name, String origin, Double price) {
        final String query = "INSERT INTO tipologie (Nome, Zona_origine, Prezzo_di_vendita)\n" + //
                            "VALUES (?, ?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, origin);
            statement.setDouble(3, price);
            statement.executeUpdate();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<WineBottle> getStocks() {
        final String query = "SELECT *\n" + //
                            "FROM bottiglie_di_vino AS b\n" + //
                            "WHERE b.Giacenza = TRUE";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            List<WineBottle> stocks = new ArrayList<>();
            while(result.next()){
                stocks.add(new WineBottle(result.getString("Nome"), 
                                            result.getInt("Anno"),
                                            result.getDouble("Capacita"),
                                            result.getString("Numero_di_lotto"),
                                            result.getInt("Numero_bottiglia"),
                                            result.getBoolean("Giacenza"),
                                            result.getDouble("Gradazione_alcolica"),
                                            result.getString("Nome_tipologia")

                ));
            }
            return stocks;
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Worker> getWorkers(java.util.Date start, java.util.Date end) {
        final String query = "SELECT o.*\n" + //
                            "FROM operai o, contratti c\n" + //
                            "WHERE ? >= c.Data_inizio AND  ? <= c.Data_fine AND o.Id_operaio = c.Id_operaio";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setDate(1, Utils.dateToSqlDate(start));
            statement.setDate(2, Utils.dateToSqlDate(end));
            final ResultSet result = statement.executeQuery();
            List<Worker> workers = new ArrayList<>();
            while(result.next()){
                workers.add(new Worker(result.getString("Nome"), 
                                            result.getString("Cognome"),
                                            Utils.sqlDateToDate(result.getDate("Data_di_nascita")),
                                            result.getString("Indirizzo"),
                                            result.getString("Id_operaio")
                ));
            }
            return workers;
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Double> getWinePrice(String type) {
        final String query = "SELECT Prezzo_di_vendita\n" + //
                            "FROM tipologie\n" + //
                            "WHERE Nome = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, type);
            final ResultSet result = statement.executeQuery();
            boolean flag = result.next();
            if(flag){
                return Optional.of(result.getDouble("Prezzo_di_vendita"));
            }
            return Optional.empty();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Supplier> getBestSupplier(String product) {
        final String query = "SELECT *\n" + //
                            "FROM fornitori f\n" + //
                            "WHERE f.Partita_IVA = (SELECT l.Partita_IVA_fornitore\n" + //
                                                    "FROM prodotti_di_listino l\n" + //
                                                    "WHERE l.Prezzo = (SELECT MIN(l.Prezzo) \n" + //
                                                                        "FROM prodotti_di_listino l \n" + //
                                                                        "WHERE l.Nome = ?))";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, product);
            final ResultSet result = statement.executeQuery();
            boolean flag = result.next();
            if(flag){
                return Optional.of(new Supplier(result.getString("Nome"), result.getString("Partita_IVA")));
            }
            return Optional.empty();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getAmountOfSoldWine(int year) {
        final String query = "SELECT SUM(Capacita)\n" + //
                            "FROM bottiglie_di_vino b\n" + //
                            "WHERE b.Giacenza = FALSE AND YEAR(b.Data_acquisto) = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, year);
            final ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("SUM(Capacita)");
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Double getAmountOfGrapes(int year) {
        final String query = "SELECT SUM(Peso)\n" + //
                            "FROM uva_raccolta u\n" + //
                            "WHERE u.Anno = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, year);
            final ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("SUM(Peso)");
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getBestSellingType(int year) {
        final String query = "WITH bottiglie_vendute as ( SELECT b.Nome_tipologia AS tipologia, COUNT(*) AS numero\n" + //
                            "FROM bottiglie_di_vino b\n" + //
                            "WHERE b.Anno = '2021' AND Giacenza = FALSE\n" + //
                            "GROUP BY b.Nome_tipologia)\n " + //
                            "SELECT tipologia\n" + //
                            "FROM bottiglie_vendute\n" + //
                            "WHERE numero = (SELECT MAX(numero) FROM bottiglie_vendute)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, year);
            final ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("tipologia");
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Integer> getDaysLeft() {
        final String query = "SELECT DATEDIFF(f.Fine,CURRENT_DATE())\n" + //
                    "FROM fasi_di_produzione f\n" + //
                    "WHERE f.Nome = 'fermentazione' AND f.Inizio < CURRENT_DATE() \n" + //
                    "AND f.Fine > CURRENT_DATE()";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            final ResultSet result = statement.executeQuery();
            boolean flag = result.next();
            if(flag){
                return Optional.of(result.getInt("DATEDIFF(f.Fine,CURRENT_DATE())"));
            }
            return Optional.empty();
        } catch (final SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            throw new IllegalStateException(e);
        }
    }
    
}
