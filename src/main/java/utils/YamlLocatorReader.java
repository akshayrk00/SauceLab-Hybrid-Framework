package utils;

import org.openqa.selenium.By;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class YamlLocatorReader {

    private static final Map<String, Map<String, Object>> cache = new HashMap<>();

    @SuppressWarnings("unchecked")
    private static Map<String, Object> loadYaml(String pageName) {

        if (cache.containsKey(pageName)) {
            return cache.get(pageName);
        }

        try {
            String filePath = "locators/" + pageName + ".yml";

            InputStream inputStream = YamlLocatorReader.class
                    .getClassLoader()
                    .getResourceAsStream(filePath);

            if (inputStream == null) {
                throw new RuntimeException("YAML file not found: " + filePath);
            }

            Yaml yaml = new Yaml();
            Map<String, Object> data = yaml.load(inputStream);

            cache.put(pageName, data);
            return data;

        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML for: " + pageName, e);
        }
    }

    @SuppressWarnings("unchecked")
    public static By getLocator(String elementName) {


        String pageName = Thread.currentThread()
                .getStackTrace()[2]
                .getClassName();

        pageName = pageName.substring(pageName.lastIndexOf('.') + 1);

        Map<String, Object> pageData = loadYaml(pageName);

        Map<String, String> element =
                (Map<String, String>) pageData.get(elementName);

        if (element == null) {
            throw new RuntimeException(
                    "Element not found: " + elementName + " in " + pageName);
        }

        String type = element.get("type");
        String value = element.get("value");

        switch (type.toLowerCase()) {
            case "id": return By.id(value);
            case "name": return By.name(value);
            case "xpath": return By.xpath(value);
            case "css": return By.cssSelector(value);
            case "class": return By.className(value);
            case "tag": return By.tagName(value);
            case "linktext": return By.linkText(value);
            case "partiallinktext": return By.partialLinkText(value);
            default:
                throw new RuntimeException("Invalid locator type: " + type);
        }
    }
}
