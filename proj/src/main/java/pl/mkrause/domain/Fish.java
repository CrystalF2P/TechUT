package pl.mkrause.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "fish.all", query = "Select f from Fish f"),
})
public class Fish {

    private long id;
    private String gatunek;
    private double waga;
    private Date dataZlowienia;

    private List<Fisherman> fishermen = new ArrayList<Fisherman>();

    public Fish(String gatunek, double waga, Date dataZlowienia) {
        this.gatunek = gatunek;
        this.waga = waga;
        this.dataZlowienia = dataZlowienia;
    }

    public Fish() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    //tylko niepusta, nie moze byc unique bo waga sie powtarza
    @Column(unique=true, nullable = false)
    public double getWaga() {
        return waga;
    }

    public void setWaga(double waga) {
        this.waga = waga;
    }


    @Temporal(TemporalType.DATE)
    public Date getDataZlowienia() {
        return dataZlowienia;
    }

    public void setDataZlowienia(Date dataZlowienia) {
        this.dataZlowienia = dataZlowienia;
    }

    
    //Polaczenie M:M z Fisherman
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Fisherman> getFishermen() {
        return fishermen;
    }

    public void setFishermen(List<Fisherman> fishermen) {
        this.fishermen = fishermen;
    }
}
