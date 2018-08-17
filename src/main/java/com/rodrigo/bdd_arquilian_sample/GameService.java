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
    
    public void deleteGame(String title) {
    	em.createNamedQuery("Game.deleteGame")
        .setParameter("title", title)
        .executeUpdate();
    }

    public void saveAllGames(List<Game> games) {
    	games.forEach(this::saveGame);
    }
    
    public Game findGameByTitle(String title) {
	    return (Game) em.createNamedQuery("Game.findGameByTitle")
	            .setParameter("title", title)
	            .getSingleResult();
    }
    
    public List<Game> findAllGames() {
    	return em.createNamedQuery("Game.findAllGames")
    			.getResultList();
    }
}
