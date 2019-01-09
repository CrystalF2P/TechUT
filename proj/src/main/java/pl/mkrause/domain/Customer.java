package pl.mkrause.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "customer.all", query = "Select c from Customer c"),
})
public class Customer {

    private long id;
    private String imie;
    private String nazwisko;

    private List<Fish> fishes = new ArrayList<Fish>();

    public Customer(String imie, String nazwisko) {
        this.imie = imie;
        this.nazwisko = nazwisko;
    }

    public Customer() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setnazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    
    //Polaczenie 1:M z Fish
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    public List<Fish> getFishes() {
        return fishes;
    }

    public void setFishes(List<Fish> fishes) {
        this.fishes = fishes;
    }
}
