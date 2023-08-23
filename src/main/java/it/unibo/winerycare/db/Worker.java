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

    public void setName(final String nome) {
        this.name = nome;
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(final String surname) {
        this.surname = surname;
    }

    public java.util.Date getDateOfBirth() {
        return this.dateOfBirth;
    }

    public void setDateOfBirth(final java.util.Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public String getId() {
        return this.id;
    }

    public void setId(final String id) {
        this.id = id;
    }

}
