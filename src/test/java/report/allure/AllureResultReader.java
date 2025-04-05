package report.allure;

import commons.CommonUtilities;
import api.utils.JsonHelper;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static api.constants.TestDataFilePath.*;

public class AllureResultReader {

    JsonHelper jsonHelper = new JsonHelper();

    @SneakyThrows
    public List<AllureTestResult> readAllureResults() {
        if (!Files.exists(Paths.get(ALLURE_RESULT))) return new ArrayList<>();

        try (Stream<Path> walk = Files.walk(Paths.get(ALLURE_RESULT))) {
            List<String> resultJsons = walk.filter(p -> !Files.isDirectory(p))
                    .map(p -> p.toString().toLowerCase()).filter(f -> f.endsWith(".json")).collect(Collectors.toList());

            List<AllureTestResult> testResults = resultJsons.stream().filter(f -> f.endsWith("result.json"))
                    .map(this::readResultJson).filter(Objects::nonNull)
                    .filter(r -> Objects.nonNull(r.getAttachments()) && !r.getAttachments().isEmpty())
                    .collect(Collectors.toList());

            List<AllureTestResult> testResultsSetupFailures = resultJsons.stream().filter(f -> f.endsWith("container.json"))
                    .map(this::readResultJson).filter(Objects::nonNull)
                    .filter(r -> Objects.nonNull(r.getBeforeMethod()) && r.getBeforeMethod().size() > 0)
                    .map(r -> r.getBeforeMethod().get(0)).filter(s -> !s.getStatus().equalsIgnoreCase("passed"))
                    .collect(Collectors.toList());
            testResults.addAll(testResultsSetupFailures);
            return testResults;
        }
    }

    @SneakyThrows
    private AllureTestResult readResultJson(String testResultJsonFile) {
        String jsonResult = FileUtils.readFileToString(new File(testResultJsonFile), StandardCharsets.UTF_8);
        if(jsonResult.isEmpty()) return null;
        return jsonHelper.getObjectFromString(jsonResult, AllureTestResult.class);
    }

    @SneakyThrows
    @Deprecated
    public void generateCategoriesJsonFile(String filePath) {
        File catJson = new File(CommonUtilities.getFilesFromDirectory(filePath).get(0).toString());
        FileInputStream inputStream = new FileInputStream(catJson);
        Scanner sc = new Scanner(inputStream);
        StringBuilder buffer = new StringBuilder();
        while(sc.hasNext()) {
            buffer.append(sc.nextLine());
        }
        File catJsonRuntime = new File(Paths.get(ALLURE_RESULT) +"/categories.json");
        FileWriter writer = new FileWriter(catJsonRuntime);
        writer.write(buffer.toString());
        writer.flush();
        writer.close();
        sc.close();
    }

    private List<String> extractUniqueFailureMessages() {
        List<AllureTestResult> results = readAllureResults();
        return results.isEmpty() ? new ArrayList<>() :
                results.stream().filter(a -> Objects.nonNull(a.getFailureReason()))
                        .map(a -> a.getFailureReason().getMessage())
                        .filter(StringUtils::isNotEmpty).distinct().collect(Collectors.toList());
    }

    public String extractFailureCategory(String failureMsg) {

        Pattern pattern1 = Pattern.compile("(.*?)"+ HYPHEN_ARROW);
        Pattern pattern2 = Pattern.compile("(.+?)\\b" + "expected" + "\\b");
        Pattern pattern3 = Pattern.compile("(.*?)"+ HYPHEN);

        String[] assertionPoints = failureMsg.split(",\\s*");
        for (String assertionMsg : assertionPoints) {
            Matcher matcher1 = pattern1.matcher(assertionMsg);
            Matcher matcher2 = pattern2.matcher(assertionMsg);
            Matcher matcher3 = pattern3.matcher(assertionMsg);

            if (matcher1.find()) {
                return matcher1.group(1).trim();
            }  else if (matcher3.find()) {
                return matcher3.group(1).trim();
            } else if (matcher2.find()) {
                return matcher2.group(1).trim();
            } else {
                return failureMsg;
            }
        }
        return "";
    }
}
