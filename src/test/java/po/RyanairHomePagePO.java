package po;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import functions.PageCommonMethods;

public class RyanairHomePagePO extends PageCommonMethods {
	private static final Logger LOGGER = LoggerFactory.getLogger(RyanairHomePagePO.class);

	/** Region generate identifiers from webelements @FindBy. */

	@FindBy(id = "input-button__departure")
	private WebElement buttonDeparture;

	@FindBy(id = "input-button__destination")
	private WebElement buttonDestination;

	@FindBy(css = "[class='list__clear-selection ry-button--anchor-blue ry-button--anchor']")
	private WebElement clearSelection;

	@FindBy(css = "[class='countries__country-inner']")
	private WebElement selectCountry;

	@FindBy(css = "[data-id='LIS']")
	private WebElement selectAirport;

	@FindBy(css = "[data-id='BVA']")
	private WebElement selectAirportDestination;

	@FindBy(css = "[data-id='2020-12-06']")
	private WebElement selectDepartureDate;

	@FindBy(css = "[data-ref='calendar-btn-next-month']")
	private WebElement btnNextMonth;

	@FindBy(css = "[data-id='2021-01-02']")
	private WebElement selectDateFinish;

	@FindBy(css = "[data-ref='passengers-picker__adults']")
	private WebElement selectPickerAdults;

	@FindBy(css = "[data-ref='passengers-picker__children']")
	private WebElement selectPickerChildren;

	@FindBy(css = "[data-ref='flight-search-widget__cta']")
	private WebElement btnSearchFlight;

	/** Endregion. */

	public void sendTrip(String departure, String destination) throws InterruptedException {
		LOGGER.debug("enter sendTrip Method");
		buttonDeparture.click();
		clearSelection.click();
		selectCountry.findElement(By.xpath("//*[contains(text(), '"+departure+"')]")).click();
		selectAirport.click();
		selectCountry.findElement(By.xpath("//*[contains(text(), '"+destination+"')]")).click();
		selectAirportDestination.click();
		waitFor();
		selectDates();
		waitFor();
		selectNumberPassenger();
	}
	public void selectDates()
	{
		LOGGER.debug("enter selectDates Method");
		selectDepartureDate.click();
		waitFor();
		btnNextMonth.click();
		waitFor();
		selectDateFinish.click();
		waitFor();
	}

	public void selectNumberPassenger()
	{
		LOGGER.debug("enter selectNumberPassenger Method");
		selectPickerAdults.findElement(By.className("counter__button-wrapper--enabled")).click();
		waitFor();
		selectPickerChildren.findElement(By.className("counter__button-wrapper--enabled")).click();
	}
	public void clickSearchFlight()
	{
		btnSearchFlight.click();
	}
}
