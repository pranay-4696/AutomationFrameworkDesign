package api.testcases;

import api.lib.properties.PropertyFileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ApiBaseTest {

    private static final Logger LOG = LogManager.getLogger();

    static {
        try {
            String environment = PropertyFileReader.getPropertyValue("ENVIRONMENT");
            LOG.info("Running tests in environment: {}", environment);
        } catch (Exception e) {
            LOG.error("Error during API Base Test initialization", e);
            throw new RuntimeException(e);
        }
    }
}