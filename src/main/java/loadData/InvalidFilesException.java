package loadData;

import lombok.Setter;

import java.util.List;

@Setter
public class InvalidFilesException extends Exception {

    private List<String> expectedFiles;
    private List<String> actualFiles;

    @Override
    public String toString() {
        String error = super.toString();
        return error + "\n"
                + "Expected files: " + expectedFiles.toString() + "\n"
                + "Actual files: " + actualFiles.toString();
    }
}