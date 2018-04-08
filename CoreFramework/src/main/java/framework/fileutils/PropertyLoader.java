package framework.fileutils;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class PropertyLoader {
    static Properties properties;

    public static Properties loadPropertiesFile(String pathOfFile) throws IOException {
        Objects.requireNonNull(pathOfFile);
        if (pathOfFile.toLowerCase().contains(".porps") || pathOfFile.toLowerCase().contains(".porperties")) {
            throw new IllegalArgumentException("File format is not as expected.");
        }
        File file = new File(pathOfFile);
        FileReader reader = new FileReader(file);
        properties = new Properties();
        properties.load(reader);
        return properties;
    }
}
