package com.rodrigo.bdd_arquilian_sample;

import java.util.List;

import javax.inject.Inject;

import org.junit.Assert;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class GameServiceTest {

	@Inject
	GameService gameService;
	
    @When("^Create game$")
    public void createGame(List<Game> games) {
        gameService.saveAllUsers(games);
    }

    @Then("^Game should exists$")
    public void gameShouldExists(List<Game> games) {
        for (Game game : games) {
            Game gameDB = gameService.findGameByTitle(game);
            Assert.assertNotNull(gameDB);
        }
    }

}
