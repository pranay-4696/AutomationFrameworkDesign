package web.constants;

import web.exceptions.WebUtilsException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum BrowserName {

    CHROME("chrome"),
    FIREFOX("firefox"),
    MS_EDGE("msedge");

    private final String browserValue;

    public static BrowserName fromString(String browserName) {
        return Arrays.stream(BrowserName.values())
                .filter(browserType -> browserType.getBrowserValue().equalsIgnoreCase(browserName))
                .findFirst().orElseThrow(() -> new WebUtilsException("Unknown browser: " + browserName));
    }
}