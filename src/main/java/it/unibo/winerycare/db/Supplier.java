package it.unibo.winerycare.db;

public class Supplier {
    
    private String name;
    private String pIVA;


    public Supplier(final String name, String pIva) {
        this.name = name;
        this.pIVA = pIva;
    }


    public String getName() {
        return this.name;
    }

    public String getPIVA() {
        return this.pIVA;
    }
    
}
