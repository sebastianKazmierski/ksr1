package convertDataModule;

import constants.Constants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StopList {
    private static List<String> stopList;

    static {
        try {
            stopList = Files.readAllLines(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_STOP_LIST + "\\" + Constants.NAME_OF_FILE_WITH_STOP_LIST));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getStopList() {
        return stopList;
    }

}
