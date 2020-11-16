package setup;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class OpenBrowser {

    private static final Logger log = LoggerFactory.getLogger(OpenBrowser.class);
    public WebDriver driver;
    private IEventListener listener;
    private static String choosenBrowser;
    public static WebDriverWait webDriverWait;
    private static EventFiringWebDriver edriver;
    public static int threadCount = 1;
    public static Map<String, EventFiringWebDriver> eventFiringWebDriverList = new HashMap<>();

    public void initBrowser() throws IOException {
        choosenBrowser = System.getenv("BROWSER");
        log.info("Tests are running on browser:" + choosenBrowser);
        driver = getDriver(choosenBrowser);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        Thread.currentThread()
                .setName("Thread" + threadCount++);
        eventFiringWebDriverList.put(String.valueOf(Thread.currentThread()
                .getName()), edriver);
        webDriverWait = new WebDriverWait(driver, 60);
        log.info("Test is running on the url:" + getUrl());
        edriver.navigate()
                .to(getUrl());
        edriver.manage()
                .timeouts()
                .implicitlyWait(30, TimeUnit.SECONDS);
        edriver.manage()
                .timeouts()
                .pageLoadTimeout(30, TimeUnit.SECONDS);
        edriver.manage()
                .timeouts()
                .setScriptTimeout(30, TimeUnit.SECONDS);
        setEdriver(edriver);
    }

    public static EventFiringWebDriver getEdriver() {
        return edriver;
    }

    public void setEdriver(EventFiringWebDriver driver) {
        edriver = driver;
    }

    /**
     * Method to open a new URL with request ID to call spokes system
     *
     * @param Url
     * @return
     * @throws IOException
     */
    public EventFiringWebDriver initBrowser(String Url) throws IOException {
        choosenBrowser = System.getenv("BROWSER");
        log.info("Tests are running on browser:" + choosenBrowser);
        driver = getDriver(choosenBrowser);
        listener = new IEventListener();
        edriver = new EventFiringWebDriver(driver);
        edriver.register(listener);
        setEdriver(edriver);
        webDriverWait = new WebDriverWait(driver, 60);
        edriver.navigate()
                .to(Url);
        edriver.manage()
                .timeouts()
                .implicitlyWait(30, TimeUnit.SECONDS);
        edriver.manage()
                .timeouts()
                .pageLoadTimeout(30, TimeUnit.SECONDS);
        edriver.manage()
                .timeouts()
                .setScriptTimeout(30, TimeUnit.SECONDS);
        return edriver;
    }

    public WebDriver getDriver(String browser) throws IOException {
        DesiredCapabilities.chrome();
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--lang=en-US");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-plugins");
        options.addArguments("--start-maximized");
        options.addArguments("--disable-extensions");
        options.addArguments("window-size=1280x1024");
		WebDriverManager.chromedriver().setup();
        switch (browser.toLowerCase()) {
            case "chrome":
                options.addArguments("--lang=us");
                driver = new ChromeDriver(options);
                break;
            /*
             * Chrome version greater than 58 required
             */
            case "chromeheadless":
                DesiredCapabilities capabilitiesChromeHeadless = new DesiredCapabilities();
				WebDriverManager.chromedriver().setup();
                options.addArguments("--headless");
                capabilitiesChromeHeadless.setPlatform(Platform.ANY);
                capabilitiesChromeHeadless.setJavascriptEnabled(true);
                capabilitiesChromeHeadless.setCapability(ChromeOptions.CAPABILITY, options);
                driver = new ChromeDriver(capabilitiesChromeHeadless);
                break;
            case "phantomjs":

                break;
        }
        return driver;
    }

    public String getUrl() {
        switch (System.getenv("ENV")) {
            case Constants.TEST_ENV:
                return System.getenv("TEST_RYANAIR_URL");
            case Constants.DEV_ENV:
                return System.getenv("DEV_RYANAIR_URL");
            default:
                Assert.fail("Environment should be set to Test or Dev");
                return null;
        }
    }
}