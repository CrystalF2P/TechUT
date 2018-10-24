package pl.mkrause.crud.domain;

import java.sql.Date;

public class Fish {
	
	private long id;
	private String gatunek;
	private Date dataZlowienia;
	private double waga;

	
	public Fish() {
	}
	
	
	
	public Fish(String gatunek, Date dataZlowienia, float waga) {
		super();
		this.gatunek = gatunek;
		this.dataZlowienia = dataZlowienia;
		this.waga = waga;	
	}
	
	
	
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
	
	
	public Date getDataZlowienia() {
		return dataZlowienia;
	}
	public void setDataZlowienia(Date dataZlowienia) {
		this.dataZlowienia = dataZlowienia;
	}
	
	
	public double getWaga() {
		return waga;
	}
	public void setWaga(double waga) {
		this.waga = waga;
	}
	
	
	
	
}
