package pl.mkrause.sh.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQueries({ 
	@NamedQuery(name = "angler.all", query = "Select a from Angler a"),
	@NamedQuery(name = "angler.boatNumber", query = "Select a from Angler a where a.boatnum = :boatnum")
})
public class Angler {

	private Long id;

	private String firstName = "unknown";
	private int boatnum;

	private List<Fish> pluralfish = new ArrayList<Fish>();

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getBoatNum() {
		return boatnum;
	}
	
	public void setBoatNum(int boatnum) {
		this.boatnum = boatnum;
	}
	

	// Be careful here, both with lazy and eager fetch type
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	public List<Fish> getFish() {
		return pluralfish;
	}
	public void setFish(List<Fish> pluralfish) {
		this.pluralfish = pluralfish;
	}
}
