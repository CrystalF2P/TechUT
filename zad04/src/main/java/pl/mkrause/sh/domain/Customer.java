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
	@NamedQuery(name = "customer.all", query = "Select * from Customer c"),
	@NamedQuery(name = "customer.byVipId", query = "Select c from Customer c where c.vip = true")
})
public class Customer {

	private Long id;

	private String firstName = "unknown";
	private boolean vip = false;
	private int vipId; //numer karty vip, żeby wyszukać klienta

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

	public boolean getVip() {
		return vip;
	}	
	public void setVip(boolean vip) {
		this.vip = vip;
	}
	
	public int getVipId() {
		return vipId;
	}
	public void setVipId(int vipid) {
		this.vipId = vipid;
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
