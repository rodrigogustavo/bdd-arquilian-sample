package com.rodrigo.bdd_arquilian_sample;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class GameService {

    @PersistenceContext
    private EntityManager em;

    public void saveGame(Game game) {
    	em.persist(game);
    }

    public void saveAllUsers(List<Game> games) {
    	games.forEach(this::saveGame);
    }

    public Game findGameByTitle(Game game) {
	    return (Game) em.createNamedQuery("Game.findGameByTitle")
	            .setParameter("title", game.getTitle())
	            .getSingleResult();
    }
}
