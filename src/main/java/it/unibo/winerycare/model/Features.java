package it.unibo.winerycare.model;

import java.util.List;

import it.unibo.winerycare.db.Supplier;
import it.unibo.winerycare.db.WineBottle;
import it.unibo.winerycare.db.Worker;

public interface Features {
    
    void addClient(String code, String type);
    void addSupplier(String name, String pIva);
    void buyMachinery(String name, String code , int productionYear, String pIva);
    void buyWineProduct(String name, String code , Double weight, String pIva);
    void buyPackagingProduct(String name, String code, String type, String pIva);
    void sellProduct(String clientCode, String clientType, String lot, int bottleNum);
    void addMaintenanceCompany(String name, String pIva);
    void updateSalePrice(String name, Double nerPrice);
    void addWineType(String name, String origin, Double price);
    List<WineBottle> getStocks();
    List<Worker> getWorkers(java.util.Date start, java.util.Date end);
    Double getWinePrice(String type);
    Supplier getBestSupplier(String product);
    Double getAmountOfSoldWine(int year);
    Double getAmountOfGrapes(int year);
    String getBestSellingType(int year);
    Integer getDaysLeft();
}
