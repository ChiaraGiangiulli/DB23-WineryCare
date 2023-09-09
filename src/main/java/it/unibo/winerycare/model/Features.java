package it.unibo.winerycare.model;

import java.util.List;
import java.util.Optional;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;

/**
 * Interface that defines the features required by the application.
 */
public interface Features {
    
    /**
     * Adds a new client in the database.
     * 
     * @param code The client's code.
     * @param type The client's type.
     */
    void addClient(String code, String type);

    /**
     * Adds a new supplier in the database.
     * 
     * @param name The supplier's name.
     * @param pIva The supplier's Partita IVA.
     */
    void addSupplier(String name, String pIva);

    /**
     * Retrieves a list of all suppliers in the database.
     * 
     * @return A list of Suppliers.
     */
    List<Supplier> getSuppliers();

    /**
     * Buys machinery and adds it in the database.
     * 
     * @param name           The name of the machinery.
     * @param code           The machinery's code.
     * @param productionYear The year the machinery was produced.
     * @param pIva           The P.IVA of the supplier for this machinery.
     */
    void buyMachinery(String name, String code, int productionYear, String pIva);

    /**
     * Buys wine products and adds them in the database.
     * 
     * @param name    The name of the wine product.
     * @param code    The wine product's code.
     * @param weight  The weight of the wine product.
     * @param pIva    The P.IVA of the supplier for this wine product.
     */
    void buyWineProduct(String name, String code, Double weight, String pIva);

    /**
     * Buys packaging products and adds them in the database.
     * 
     * @param name     The name of the packaging product.
     * @param code     The packaging product's code.
     * @param quantity The quantity of packaging products purchased.
     * @param pIva     The P.IVA of the supplier for these packaging products.
     */
    void buyPackagingProduct(String name, String code, int quantity, String pIva);

    /**
     * Sells a product to a client.
     * 
     * @param clientCode   The code of the client buying the product.
     * @param clientType   The type of the client.
     * @param lot          The lot of the product to be sold.
     * @param bottleNum    The number of bottles to be sold.
     * @return             The number of updated rows.
     */
    int sellProduct(String clientCode, String clientType, String lot, int bottleNum);

    /**
     * Adds a maintenance company in the database.
     * 
     * @param name The name of the maintenance company.
     * @param pIva The P.IVA of the maintenance company.
     */
    void addMaintenanceCompany(String name, String pIva);

    /**
     * Updates the sale price of a product.
     * 
     * @param name     The name of the product.
     * @param newPrice The new sale price of the product.
     * @return         The number of updated rows.
     */
    int updateSalePrice(String name, Double newPrice);

    /**
     * Adds a new wine type in the database.
     * 
     * @param name   The name of the wine type.
     * @param origin The origin of the wine type.
     * @param price  The price of the wine type.
     */
    void addWineType(String name, String origin, Double price);

    /**
     * Retrieves a list of wine bottles stocks in the winery.
     * 
     * @return A list of WineBottle objects representing the stocks.
     */
    List<WineBottle> getStocks();

    /**
     * Retrieves a list of workers who worked between the specified start and end dates.
     * 
     * @param start The start date.
     * @param end   The end date.
     * @return      A list of Workers.
     */
    List<Worker> getWorkers(java.util.Date start, java.util.Date end);

    /**
     * Retrieves the price of a wine type.
     * 
     * @param type The name of the wine type.
     * @return     An Optional containing the price of the wine type, or empty if not found.
     */
    Optional<Double> getWinePrice(String type);

    /**
     * Retrieves the best supplier for a given product.
     * 
     * @param product The name of the product.
     * @return        An Optional containing the best Supplier for the product, or empty if not found.
     */
    Optional<Supplier> getBestSupplier(String product);

    /**
     * Retrieves the total amount of wine sold in a specific year.
     * 
     * @param year The year for which the amount of sold wine is to be retrieved.
     * @return     The total amount of wine sold in the specified year.
     */
    Double getAmountOfSoldWine(int year);

    /**
     * Retrieves the total amount of grapes harvested in a specific year.
     * 
     * @param year The year for which the amount of harvested grapes is to be retrieved.
     * @return     The total amount of grapes harvested in the specified year.
     */
    Double getAmountOfGrapes(int year);

    /**
     * Retrieves the best-selling wine type in a specific year.
     * 
     * @param year The year for which the best-selling wine type is to be retrieved.
     * @return     The name of the best-selling wine type in the specified year.
     */
    String getBestSellingType(int year);

    /**
     * If a fermentation phase is underway retrieves the number of days left.
     * 
     * @return An Optional containing the number of days left, or empty if it's not underway.
     */
    Optional<Integer> getDaysLeft();
}

