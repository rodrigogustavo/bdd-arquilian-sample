package com.rodrigo.bdd_arquilian_sample;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.runtime.arquillian.CukeSpace;
import cucumber.runtime.arquillian.api.Features;

@RunWith(CukeSpace.class)
@Features({ "src/test/resources/com/rodrigo/sample/GameFeature.feature" })
@CucumberOptions(plugin = { "pretty", "html:target/cucumber_report" })
public class GameServiceTest {

	@Inject
	GameService gameService;

	List<Game> results;

    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClasses(Game.class, GameService.class)
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

    @Given("^I bought these games$")
    public void createGame(List<Game> games) {
        gameService.saveAllGames(games);
    }

    @When("^I check the game list$")
    public void iCheckTheGameList() {
    	results = gameService.findAllGames();
    }
    
    @When("^I sold \"([^\"]*)\"$")
    public void iSold(String title) {
    	gameService.deleteGame(title);
    }

    @Then("^Game \"([^\"]*)\" should be available$")
    public void gameShouldBeAvailable(String title) {
    	Game gameDB = gameService.findGameByTitle(title);
        Assertions.assertNotNull(gameDB);
        Assertions.assertEquals(title, gameDB.getTitle());
    }
    
    @Then("^I should have (\\d+) games$")
    public void iShouldHave(int quantity) {
    	Assertions.assertEquals(results.size(), quantity);
    }
}
