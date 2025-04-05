package commons;

import lombok.SneakyThrows;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class CommonUtilities {

    @SneakyThrows
    public static List getFilesFromDirectory(String fileDir) {
        return Files.walk(Paths.get(fileDir))
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .collect(Collectors.toList());
    }
}
