package in.zeta.tech.lib.report.allure;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AllureTestResult {

    @JsonProperty("name")
    private String name;
    @JsonProperty("status")
    private String status;
    @JsonProperty("statusDetails")
    private TestFailureReason failureReason;
    @JsonProperty("attachments")
    private List<RequestAttachments> attachments;

    @JsonProperty("befores")
    private List<AllureTestResult> beforeMethod;

}
