package functions;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import setup.Constants;
import setup.OpenBrowser;

/**
 * @author Luis E blandon
 *
 */
public class PageCommonMethods {

	protected static Logger log;
	private static WebDriverWait wait;
	Properties prop = new Properties();
	InputStream input = null;

	public PageCommonMethods() {

		log = LoggerFactory.getLogger(PageCommonMethods.class);

	}

	public PageCommonMethods(long timeInSeconds) {
		try {
			Thread.sleep(timeInSeconds * 1000);
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void waitFor() {
		try {
			Thread.sleep(Integer.parseInt(getPropertieValue("implicitConditionsWait")) * 1000);
		} catch (InterruptedException | NullPointerException e) {
			e.printStackTrace();
		}
	}

	public void waitForVisibility(WebElement element) {
		getWait(Integer.parseInt(getPropertieValue("expectedConditionsWait")))
				.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForClickableElement(WebElement element) {

		getWait(Integer.parseInt(getPropertieValue("expectedConditionsWait")))
				.until(ExpectedConditions.elementToBeClickable(element));

	}

	/**
	 * @param propertyName
	 *            load the file gradle.properties to read the different
	 *            properties inside the file
	 * @return value of property in the file gradle.properties
	 */
	public String getPropertieValue(String propertyName) {

		try {

			input = new FileInputStream("gradle.properties");

			// load a properties file
			prop.load(input);

			return prop.getProperty(propertyName);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return prop.getProperty(propertyName);
	}

	public boolean isClickable(WebElement webe) {
		EventFiringWebDriver edriver = OpenBrowser.getEdriver();

		try {
			WebDriverWait wait = new WebDriverWait(edriver, 5);
			wait.until(ExpectedConditions.elementToBeClickable(webe));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public WebDriverWait getWait(int timeOutInSeconds) {
		wait = new WebDriverWait(OpenBrowser.getEdriver(), timeOutInSeconds, 100);
		return wait;
	}

	public void waitForExpected(WebElement element) {

		getWait(Integer.parseInt(getPropertieValue("expectedConditionsWait")))
				.until(ExpectedConditions.and(ExpectedConditions.elementToBeClickable(element)

		));
	}

}
