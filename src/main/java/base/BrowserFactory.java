package base;

import config.ConfigLoader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.Log;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {

    private static final Logger log = Log.getLogger(BrowserFactory.class);


    public static void initDriver(String browser) {

        log.info("Initializing browser...");


        if (browser == null || browser.isEmpty()) {
            browser = ConfigLoader.getConfig()
                    .getFramework()
                    .getBrowser();
            log.info("Browser fetched from config.yml: " + browser);
        }

        WebDriver driver;

        switch (browser.toLowerCase()) {

            case "chrome":
                log.info("Launching Chrome browser");
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                if (ConfigLoader.getConfig()
                        .getFramework()
                        .isHeadless()) {


                    chromeOptions.addArguments("--headless=new");
                }


                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                prefs.put("profile.password_manager_leak_detection", false);

                chromeOptions.addArguments("--disable-notifications");
                prefs.put("safebrowsing.enabled", false);

                chromeOptions.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                log.info("Launching FireFox browser");
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();

                if (ConfigLoader.getConfig()
                        .getFramework()
                        .isHeadless()) {

                    firefoxOptions.addArguments("-headless");
                }

                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                log.info("Launching Edge browser");
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();

                if (ConfigLoader.getConfig()
                        .getFramework()
                        .isHeadless()) {

                    edgeOptions.addArguments("--headless=new");
                }

                driver = new EdgeDriver(edgeOptions);
                break;

            default:
                log.error("Unsupported browser: " + browser);
                throw new RuntimeException("Unsupported browser: " + browser);
        }

        // ✅ Set driver to ThreadLocal
        DriverManager.setDriver(driver);

        // ✅ Apply timeouts from config
        driver.manage().timeouts().implicitlyWait(
                Duration.ofSeconds(
                        ConfigLoader.getConfig()
                                .getFramework()
                                .getImplicitWait()
                )
        );
        log.info("Browser launched successfully");

        driver.manage().timeouts().pageLoadTimeout(
                Duration.ofSeconds(
                        ConfigLoader.getConfig()
                                .getTimeouts()
                                .getPageLoad()
                )
        );

        driver.manage().window().maximize();
    }
}
