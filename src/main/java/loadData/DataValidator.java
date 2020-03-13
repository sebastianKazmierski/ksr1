package loadData;

import java.nio.file.Path;
import java.util.List;

@FunctionalInterface
public interface DataValidator {
    List<Path> validate(List<Path> paths) throws InvalidFilesException;
}
