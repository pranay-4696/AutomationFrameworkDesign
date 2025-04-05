package web.utils;

import web.exceptions.WebUtilsException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

@Log4j2
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WebPropertyUtils {

    public static Properties loadProperties(String fileName) {
        Properties properties;
        InputStream fileInputStream;
        log.info("Loading the Properties File...");
        try {
            properties = new Properties();
            fileInputStream = Files.newInputStream(Paths.get(fileName));
            properties.load(fileInputStream);
            return properties;
        } catch (IOException e) {
            throw new WebUtilsException("Properties File failed to load!" + e.getMessage());
        }
    }
}