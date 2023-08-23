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

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getYear() {
        return this.year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public Double getCapacity() {
        return this.capacity;
    }

    public void setCapacity(final Double capacity) {
        this.capacity = capacity;
    }

    public String getLotNumber() {
        return this.lotNumber;
    }

    public void setLotNumber(final String lotNumber) {
        this.lotNumber = lotNumber;
    }

    public Integer getBottleNumber() {
        return this.bottleNumber;
    }

    public void setBottleNumber(final Integer bottleNumber) {
        this.bottleNumber = bottleNumber;
    }

    public boolean isStock() {
        return this.stock;
    }

    public boolean getStock() {
        return this.stock;
    }

    public void setStock(final boolean stock) {
        this.stock = stock;
    }

    public Double getAlcoholContent() {
        return this.alcoholContent;
    }

    public void setAlcoholContent(final Double alcoholContent) {
        this.alcoholContent = alcoholContent;
    }

    public String getType() {
        return this.type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public Optional<String> getClientCode() {
        return this.clientCode;
    }

    public void setClientCode(final Optional<String> clientCode) {
        this.clientCode = clientCode;
    }

    public Optional<java.util.Date> getPurchaseDate() {
        return this.purchaseDate;
    }

    public void setPurchaseDate(final Optional<java.util.Date> purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public Optional<Double> getPurchaseAmount() {
        return this.purchaseAmount;
    }

    public void setPurchaseAmount(final Optional<Double> purchaseAmount) {
        this.purchaseAmount = purchaseAmount;
    }

}
