package it.unibo.winerycare.db;

import java.util.Optional;

public class WineBottle {
    
    private String name;
	private Integer year;
	private Double capacity;
	private String lotNumber;
	private Integer bottleNumber;
	private boolean stock;
	private Double alcoholContent;
	private String type;
	private Optional<String> clientCode;
    private Optional<java.util.Date> purchaseDate;
	private Optional<Double> purchaseAmount;



    public WineBottle(final String name, final Integer year, final Double capacity, final String lotNumber, 
                        final Integer bottleNumber, final boolean stock, final Double alcoholContent, final String type) {
        this.name = name;
        this.year = year;
        this.capacity = capacity;
        this.lotNumber = lotNumber;
        this.bottleNumber = bottleNumber;
        this.stock = stock;
        this.alcoholContent = alcoholContent;
        this.type = type;
        this.clientCode = Optional.empty();
        this.purchaseDate = Optional.empty();
        this.purchaseAmount = Optional.empty();
    }
    

    public String getName() {
        return this.name;
    }

    public Integer getYear() {
        return this.year;
    }


    public Double getCapacity() {
        return this.capacity;
    }


    public String getLotNumber() {
        return this.lotNumber;
    }


    public Integer getBottleNumber() {
        return this.bottleNumber;
    }


    public boolean getStock() {
        return this.stock;
    }


    public Double getAlcoholContent() {
        return this.alcoholContent;
    }


    public String getType() {
        return this.type;
    }


    public Optional<String> getClientCode() {
        return this.clientCode;
    }


    public Optional<java.util.Date> getPurchaseDate() {
        return this.purchaseDate;
    }


    public Optional<Double> getPurchaseAmount() {
        return this.purchaseAmount;
    }

}
