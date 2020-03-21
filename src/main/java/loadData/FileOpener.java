package loadData;

import constants.Constants;
import loadData.dataValidators.DataValidator;
import loadData.dataValidators.InvalidFilesException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileOpener {

    public List<Path> loadArticlesFromDirectory(DataValidator dataValidator) throws InvalidFilesException {
        List<Path> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(Constants.DIRECTORY_WITH_ARTICLES_XML))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(files::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dataValidator.validate(files);
    }
}
