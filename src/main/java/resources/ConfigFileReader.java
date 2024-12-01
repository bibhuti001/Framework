package resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigFileReader {

    private static ConfigFileReader instance;
    private Properties properties;

    private ConfigFileReader() {
        properties = new Properties();
        String projectDir = System.getProperty("user.dir");
        String filePath = Paths.get(projectDir, "src", "main", "java", "resources", "data.properties").toString();
        try (InputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static ConfigFileReader getInstance() {
        if (instance == null) {
            instance = new ConfigFileReader();
        }
        return instance;
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getUrl() {
        return properties.getProperty("url");
    }

    public String getValidUser() {
        return properties.getProperty("validUser");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getLockedOutUser() {
        return properties.getProperty("lockedOutUser");
    }
    
    public String getInvalidUser() {
        return properties.getProperty("invalidUser");
    }
    public String getInvalidPassword() {
        return properties.getProperty("invalidPassword");
    }
}
