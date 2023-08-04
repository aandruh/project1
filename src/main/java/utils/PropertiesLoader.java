package utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private PropertiesLoader() {
    }

    /**
     * @param filename - "resources folder-relative path" + File.separator + "filename.properties"
     * @return Properties
     * @throws ClassNotFoundException
     */
    public static Properties uploadPropertiesFile(String filename) {
        Properties property = new Properties();
        ClassLoader loader = null;
        try {
            loader = Class.forName(PropertiesLoader.class.getName()).getClassLoader();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try (InputStream fileInputStreamWithConfigFile = loader.getResourceAsStream(filename)) {
            property.load(fileInputStreamWithConfigFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }

    public static String loadProperty_ofConfigFile(String name) {
        Properties props = new Properties();
        try {
            props.load(PropertiesLoader.class.getResourceAsStream("/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String value = null;

        if (name != null) {
            value = props.getProperty(name);
        }
        return value;
    }
}


