package loadData;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class AllDataValidator implements DataValidator {
    private final String REQUIRED_FILES = "src/main/resources/fileValidators/allFiles.txt";

    private List<String> getREQUIRED_FILES() {
        Scanner scanner = new Scanner(REQUIRED_FILES);

        List<String> filesNames = new ArrayList<>();
        while (scanner.hasNextLine()) {
            filesNames.add(scanner.nextLine());
        }
        return filesNames;
    }

    @Override
    public List<Path> validate(List<Path> paths) throws InvalidFilesException {
        List<String> filesNames = getREQUIRED_FILES();

        List<Path> validPath = new ArrayList<>();

        List<String> pathsString = paths.stream().map(e -> e.getFileName().toString()).collect(Collectors.toList());

        for (String pathString : pathsString) {
            int index = pathsString.indexOf(pathString);
            if (index != -1) {
                validPath.add(paths.get(index));
            } else {
                InvalidFilesException invalidFilesException = new InvalidFilesException();
                invalidFilesException.setActualFiles(paths.stream()
                        .map(e -> e.getFileName().toString()).collect(Collectors.toList()));
                invalidFilesException.setExpectedFiles(filesNames);
                throw invalidFilesException;
            }
        }
        return validPath;
    }
}
