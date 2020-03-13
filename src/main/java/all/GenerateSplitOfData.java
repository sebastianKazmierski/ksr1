package all;

import org.apache.commons.io.FilenameUtils;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

public class GenerateSplitOfData {
    public String generate(int numberOfData, int procentOfTrainSet) {
        // Otwiera plik i uzyskuje powiązany z nim strumień
        DateFormat dateFormat = new SimpleDateFormat("yy/MM/dd_HH:mm");
        Date date = new Date();
        try (OutputStream fout =
                     new BufferedOutputStream(
                             Files.newOutputStream(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS + "split" + dateFormat.format(date)
                                     )))) {
            for (int i = 0; i < 26; i++)
                fout.write((byte) ('A' + i));
        } catch (InvalidPathException e) {
            System.out.println("Błąd ścieżki: " + e);
        } catch (IOException e) {
            System.out.println("Błąd wejścia-wyjścia: " + e);
        }
    }

    public String getNameNewFile() {
        System.
    }
}
