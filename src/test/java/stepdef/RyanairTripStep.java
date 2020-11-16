package stepdef;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import cucumber.api.DataTable;
import org.junit.Assert;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import po.FlightPagePO;
import setup.OpenBrowser;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import po.RyanairHomePagePO;

public class RyanairTripStep {
	private EventFiringWebDriver edriver;
	RyanairHomePagePO ryanairHomePagePO;
	FlightPagePO flightPagePO;
	OpenBrowser openBrowser = new OpenBrowser();

	public void setPO() throws IOException {
		openBrowser.initBrowser();
		edriver = OpenBrowser.getEdriver();
		ryanairHomePagePO = PageFactory.initElements(edriver, RyanairHomePagePO.class);
		flightPagePO = PageFactory.initElements(edriver, FlightPagePO.class);
	}

	@Given("^User have the following information to create a new trip$")
	public void userHaveTheFollowingInformation(DataTable fieldDataTable) throws IOException, InterruptedException {
		setPO();
		List<Map<String, String>> list = fieldDataTable.asMaps(String.class, String.class);
		ryanairHomePagePO.sendTrip(list.get(0).get("Departure"), list.get(0).get("Destination"));
	}

	@When("^User selects the trip$")
	public void userSelectsTrip() {
		ryanairHomePagePO.clickSearchFlight();
	}

	@And("^User confirms trip")
	public void userConfirmsTrip() throws InterruptedException {
		flightPagePO.confirmTrip();
	}

	@Then("^User go to the search results page")
	public void userGoToThePage(String titlePage) {
	//	Assert.assertTrue();
	}

}
