package it.unibo.winerycare.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;
import it.unibo.winerycare.utils.Utils;

public class FeaturesImpl implements Features {

    private final Connection connection;

    public FeaturesImpl(final Connection connection) {
        this.connection = connection;
    }
    

    @Override
    public void addClient(final String code, final String type) {
        final String query = "INSERT INTO clienti (Codice, Tipologia)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, code);
            statement.setString(2, type);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addSupplier(final String name, final String pIva) {
        final String query = "INSERT INTO fornitori (Nome, Partita_IVA)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, pIva);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }


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
            throw new IllegalStateException(e);
        }
    }


    @Override
    public void buyPackagingProduct(final String name, final String code ,final String type, final String pIva) {
        final Double price = this.getProductPrice(pIva, name);
        final String query = "INSERT INTO prodotti_per_imballaggio(Nome, Data_acquisto, Prezzo, Codice, Tipologia, Partita_IVA_fornitore, \n" + //
                                                                    "Nome_fase, Inizio_fase, Fine_fase)\n" + //
                            "VALUES(?, CURRENT_DATE(), p, ?, ?, ?, NULL, NULL, NULL)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setDouble(2, price);
            statement.setString(3, code);
            statement.setString(4, type);
            statement.setString(5, pIva);
            statement.setString(6, null);
            statement.setDate(7, null);
            statement.setDate(8, null);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    

    @Override
    public void sellProduct(final String clientCode, final String clientType, final String lot, final int bottleNum) {
        final String query = "SELECT t.Prezzo_di_vendita AS prezzo\n" + //
                            "FROM tipologie t\n" + //
                            "WHERE t.Nome = bottiglie_di_vino.Nome_tipologia;\n" + //
                            "\n" + //
                            "INSERT IGNORE INTO clienti (Codice, Tipologia)\n" + //
                            "VALUES (?,?);\n" + //
                            "\n" + //
                            "UPDATE bottiglie_di_vino AS bott\n" + //
                            "SET Giacenza = FALSE, Codice_cliente = ?, Data_acquisto = CURRENT_DATE(), Importo_acquisto = prezzo * bott.Capacita\n" + //
                            "WHERE bott.Numero_di_lotto = ? AND bott.Numero_bottiglia = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, clientCode);
            statement.setString(2, clientType);
            statement.setString(3, clientCode);
            statement.setString(4, lot);
            statement.setInt(5, bottleNum);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void addMaintenanceCompany(final String name, final String pIva) {
        final String query = "INSERT INTO ditte (Nome, Partita_IVA)\n" + //
                            "VALUES (?, ?)";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, name);
            statement.setString(2, pIva);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void updateSalePrice(String name, Double newPrice) {
        final String query = "UPDATE tipologie AS t\n" + //
                            "SET t.Prezzo_di_vendita = ?\n" + //
                            "WHERE t.Nome = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(2, name);
            statement.setDouble(1, newPrice);
            statement.executeUpdate();
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Double getWinePrice(String type) {
        final String query = "SELECT Prezzo_di_vendita\n" + //
                            "FROM tipologie\n" + //
                            "WHERE Nome = ?";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setString(1, type);
            final ResultSet result = statement.executeQuery();
            result.next();
            return result.getDouble("Prezzo_di_vendita");
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public Supplier getBestSupplier(String product) {
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
            result.next();
            return new Supplier(result.getString("Nome"), result.getString("Partita_IVA"));
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }

    @Override
    public String getBestSellingType(int year) {
        final String query = "SELECT tipologia\n" + //
                "FROM (SELECT b.Nome_tipologia AS tipologia, COUNT(*) AS numero\n" + //
                    "FROM bottiglie_di_vino b\n" + //
                    "WHERE b.Anno = ? AND Giacenza = FALSE\n" + //
                    "GROUP BY b.Nome_tipologia\n" + //
                    "ORDER BY numero DESC\n" + //
                    "LIMIT 1) AS tipo";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            statement.setInt(1, year);
            final ResultSet result = statement.executeQuery();
            result.next();
            return result.getString("tipologia");
        } catch (final SQLException e) {
            throw new IllegalStateException(e);
        }
    }

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
            throw new IllegalStateException(e);
        }
    }
    
}
