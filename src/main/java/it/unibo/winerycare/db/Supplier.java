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

    public void setName(final String name) {
        this.name = name;
    }

    public String getPIVA() {
        return this.pIVA;
    }

    public void setPIVA(final String pIVA) {
        this.pIVA = pIVA;
    }


}
