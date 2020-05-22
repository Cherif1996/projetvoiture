package com.jeeProjectGb.entities;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Categorie {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String game;
    private int puissanceFiscale;
	public Categorie() {
		
	}
	public void setId(long id) {
		this.id = id;
	}
	public Categorie(String game, int puissanceFiscale) {
		super();
		this.game = game;
		this.puissanceFiscale = puissanceFiscale;
	}
	public long getId() {
		return id;
	}
	
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public int getPuissanceFiscale() {
		return puissanceFiscale;
	}
	public void setPuissanceFiscale(int puissanceFiscale) {
		this.puissanceFiscale = puissanceFiscale;
	}
	
	
}
