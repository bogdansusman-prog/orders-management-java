
package org.example.model;
/**
 * Clasa model care reprezinta un client din baza de date.
 * Contine informatiile de identificare si contact ale clientului.
 */
public class Client {
    private int id;
    private String name;
    private String email;
    private String address;

    public Client(){}

    /**
     * Creeaza un obiect de tip Client cu informatiile specificate.
     *
     * @param id idul clientului
     * @param name numele clientului
     * @param email email ul clientului
     * @param address adresa clientului
     */
    public Client(int id, String name, String email, String address) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
    }

    /**
     * Returneaza id-ul clientului.
     *
     * @return id-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * Seteaza id-ul clientului.
     *
     * @param id id-ul clientului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Returneaza numele clientului.
     *
     * @return name-ul clientului
     */
    public String getName() {
        return name;
    }

    /**
     * Seteaza id-ul clientului.
     *
     * @param name numele clientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returneaza numele clientului.
     *
     * @return email-ul clientului
     */
    public String getEmail() {
        return email;
    }

    /**
     * Seteaza emailul clientului.
     *
     * @param email emailul clientului
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returneaza adresa clientului.
     *
     * @return adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /**
     * Seteaza adresa clientului.
     *
     * @param address adresa clientului
     */
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
