package pl.mkrause.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
    @NamedQuery(name = "card.all", query = "Select c from Card c"),
})
public class Card {

    private long id;
    private String cardId;

    public Card(String cardId) {
        this.cardId = cardId;
    }

    public Card() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //unique, niepuste
    @Column(unique = true, nullable = false)
    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }
}
