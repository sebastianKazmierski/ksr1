package loadData;

import all.Constants;
import org.apache.commons.io.IOUtils;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileOpener {

    public List<Path> loadArticlesFromDirectory() {
        List<Path> files = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(Paths.get(Constants.DIRECTORY_WITH_ARTICLES_XML))) {
            paths
                    .filter(Files::isRegularFile)
                    .forEach(files::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }

    public char[] getCharsFromFile(Path path) throws IOException {
        Reader fileReader = new FileReader(path.toFile());
        return IOUtils.toCharArray(fileReader);
    }
}
