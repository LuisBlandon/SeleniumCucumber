package setup;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static setup.Constants.main;

public class Hooks {
	private static Logger log = LoggerFactory.getLogger(Hooks.class);

	@After
	public void tearDown(Scenario scenario) {
		if(!Thread.currentThread().getName().equals(main)) {
			if (scenario.isFailed()) {
				final byte[] screenshot = ((TakesScreenshot) OpenBrowser.eventFiringWebDriverList
						.get(String.valueOf(Thread.currentThread().getName()))).getScreenshotAs(OutputType.BYTES);
				scenario.embed(screenshot, "image/png");
			}
			OpenBrowser.eventFiringWebDriverList.get(String.valueOf(Thread.currentThread().getName())).close();
			Thread.currentThread().setName(main);
			log.info("Scenario executed:" + scenario.getName());
			log.info("Browser is closed");
		}
	}

	@Before
	public void startUp(Scenario scenario) {
		log.info("Scenario Running:" + scenario.getName());
	}
}
