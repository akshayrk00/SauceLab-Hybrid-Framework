package config;

import org.yaml.snakeyaml.Yaml;
import java.io.InputStream;

public class ConfigLoader {

    private static ConfigModel config;

    static {
        loadConfig();
    }

    private static void loadConfig() {
        Yaml yaml = new Yaml();
        InputStream inputStream = ConfigLoader.class
                .getClassLoader()
                .getResourceAsStream("config.yml");

        config = yaml.loadAs(inputStream, ConfigModel.class);
    }

    public static ConfigModel getConfig() {
        return config;
    }
}
