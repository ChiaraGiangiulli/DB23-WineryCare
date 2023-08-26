package it.unibo.winerycare.db;

public class Worker {
    
    private String name;
	private String surname;
    private java.util.Date dateOfBirth;
	private String address;
    private String id;


    public Worker(final String nome, final String surname, final java.util.Date dateOfBirth, final String address, final String id) {
        this.name = nome;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.id = id;
    }


    public String getName() {
        return this.name;
    }


    public String getSurname() {
        return this.surname;
    }


    public java.util.Date getDateOfBirth() {
        return this.dateOfBirth;
    }


    public String getAddress() {
        return this.address;
    }


    public String getId() {
        return this.id;
    }

}
