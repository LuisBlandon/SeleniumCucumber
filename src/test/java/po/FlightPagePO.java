package po;

import functions.PageCommonMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import setup.OpenBrowser;

public class FlightPagePO extends PageCommonMethods {
	private static final Logger LOGGER = LoggerFactory.getLogger(FlightPagePO.class);

	/** Region generate identifiers from webelements @FindBy. */

	@FindBy(css = "[data-ref='FR 1083']")
	private WebElement confirmDeparture;

	@FindBy(css = "[class='fare-card__button-text ng-star-inserted']")
	private WebElement btnValueFare;

	@FindBy(css = "[data-ref='FR 1084']")
	private WebElement confirmDestination;

	@FindBy(css = "[class='login-touchpoint__login-later h3']")
	private WebElement lblLoginLater;

	/** Endregion. */

	public void confirmTrip()
	{
		confirmDeparture.click();
		waitFor();
		((JavascriptExecutor) OpenBrowser.getEdriver()).executeScript("arguments[0].scrollIntoView(true);", btnValueFare);
		btnValueFare.click();
		waitFor();
		confirmDestination.click();
		waitFor();
		((JavascriptExecutor) OpenBrowser.getEdriver()).executeScript("arguments[0].scrollIntoView(true);", btnValueFare);
		btnValueFare.click();
		waitFor();
		((JavascriptExecutor) OpenBrowser.getEdriver()).executeScript("arguments[0].scrollIntoView(true);", lblLoginLater);
		lblLoginLater.click();
	}
}
