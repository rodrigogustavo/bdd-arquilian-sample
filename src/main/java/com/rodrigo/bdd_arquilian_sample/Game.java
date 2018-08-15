package com.rodrigo.bdd_arquilian_sample;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "GAMES")
@NamedQueries({ @NamedQuery(name = "Game.findGameByTitle", query = "SELECT g FROM Game g WHERE g.title = :title") })
public class Game {

	@Id
	private BigDecimal id;

	private String title;
	private int minimalAge;
	private String developer;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMinimalAge() {
		return minimalAge;
	}

	public void setMinimalAge(int minimalAge) {
		this.minimalAge = minimalAge;
	}

	public String getDeveloper() {
		return developer;
	}

	public void setDeveloper(String developer) {
		this.developer = developer;
	}
}
