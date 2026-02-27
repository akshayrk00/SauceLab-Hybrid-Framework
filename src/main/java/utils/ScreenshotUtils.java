package utils;

import base.DriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;

public class ScreenshotUtils {

    public static String captureScreenshot(String testName) {

        WebDriver driver = DriverManager.getDriver();

        TakesScreenshot ts = (TakesScreenshot) driver;
        byte[] source = ts.getScreenshotAs(OutputType.BYTES);

        String path = System.getProperty("user.dir")
                + "/test-output/screenshots/"
                + testName + ".png";

        try {
            File file = new File(path);
            file.getParentFile().mkdirs();

            FileOutputStream fos = new FileOutputStream(file);
            fos.write(source);
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return path;
    }
}