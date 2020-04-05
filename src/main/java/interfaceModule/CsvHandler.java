package interfaceModule;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CsvHandler {
    private final String EXTENSION = ".csv";
    private final String PATH = "results\\";
    private final String NAME_OF_DIRECTORY_WITH_DATA = "filesWithData";

//    public String createDirectoryForData() throws IOException {
//        DateFormat dateFormat = new SimpleDateFormat("_MM-dd_HH-mm-ss");
//        Date date = new Date();
//        Path path = Paths.get(PATH + NAME_OF_DIRECTORY_WITH_DATA + dateFormat.format(date));
//        Files.createDirectories(path);
//        return path.toString();
//    }

    public void createFile(String fileName, String[] headers, List<List<String>> data) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("_MM-dd_HH-mm");
        Date date = new Date();
        FileWriter out = new FileWriter(this.PATH + fileName + dateFormat.format(date) + this.EXTENSION);
        try (CSVPrinter printer = new CSVPrinter(out, CSVFormat.DEFAULT
                .withHeader(headers))) {
            for (List<String> record : data) {
                printer.printRecord(record);
            }
        }
    }
}
