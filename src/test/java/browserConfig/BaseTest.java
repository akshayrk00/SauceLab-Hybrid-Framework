package browserConfig;



import base.BrowserFactory;
import base.DriverManager;
import config.ConfigLoader;

import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import utils.ExcelUtils;
import utils.Log;

import java.lang.reflect.Method;
import java.util.Map;

public class BaseTest {

    private static final Logger log = Log.getLogger(BaseTest.class);
    // Thread safe test data storage
    protected static ThreadLocal<Map<String, String>> testData = new ThreadLocal<>();

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setup( Method method, String browser) {

        log.info("===== Test Started =====");


        BrowserFactory.initDriver(browser);

        // Maximize (optional but recommended)
        DriverManager.getDriver().manage().window().maximize();

        String baseUrl = ConfigLoader.getConfig()
                .getFramework()
                .getBaseUrl();
        // Open URL
        DriverManager.getDriver().get(
                baseUrl);

        log.info("Navigated to: " + baseUrl);


        String testCaseName = method.getName();

        Map<String, String> data =
                ExcelUtils.getTestData(testCaseName);

        if (data == null || data.isEmpty()) {
            throw new RuntimeException(
                    "No Excel data found for test case: " + testCaseName
            );
        }

        testData.set(data);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {

        log.info("Closing browser");
        if (DriverManager.getDriver() != null) {
            DriverManager.getDriver().quit();
            DriverManager.unload();
        }
        log.info("===== Test Finished =====");

        testData.remove();
    }

    protected Map<String, String> getTestData() {
        return testData.get();
    }
}

