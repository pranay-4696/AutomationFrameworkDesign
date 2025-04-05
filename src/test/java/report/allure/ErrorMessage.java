package in.zeta.tech.lib.report.allure;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;


@Data
public class ErrorMessage {

    @JsonProperty("name")
    private String name;
    @JsonProperty("messageRegex")
    private String messageRegex;
    @JsonProperty("matchedStatuses")
    private List<String> matchedStatuses = Arrays.asList("failed", "skipped", "broken");



}