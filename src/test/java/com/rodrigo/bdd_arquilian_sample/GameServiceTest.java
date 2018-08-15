package com.rodrigo.bdd_arquilian_sample;

import java.util.List;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
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
	
    @Deployment
    public static JavaArchive createDeployment() {
        JavaArchive jar = ShrinkWrap.create(JavaArchive.class).addClasses(Game.class, GameService.class)
                .addAsResource("persistence.xml", "META-INF/persistence.xml")
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
        System.out.println(jar.toString(true));
        return jar;
    }

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
