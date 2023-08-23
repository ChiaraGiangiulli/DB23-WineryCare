package it.unibo.winerycare.db;

public class WineType {
    private String name;
	private String originArea;
	private Double sellingPrice;


    public WineType(final String name, final String originArea, final Double sellingPrice) {
        this.name = name;
        this.originArea = originArea;
        this.sellingPrice = sellingPrice;
    }


    public String getName() {
        return this.name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getOriginArea() {
        return this.originArea;
    }

    public void setOriginArea(final String originArea) {
        this.originArea = originArea;
    }

    public Double getSellingPrice() {
        return this.sellingPrice;
    }

    public void setSellingPrice(final Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

}
