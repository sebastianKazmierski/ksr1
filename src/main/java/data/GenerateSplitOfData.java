package data;

import constants.Constants;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class GenerateSplitOfData {
    private final String EXTENSION = ".sd";

    public String generate(int numberOfData, int percentOfTrainSet) {
        if (percentOfTrainSet < 0 || percentOfTrainSet > 100) {
            throw new IllegalArgumentException("Argument percentOfTrainSet must be in the range of 0 to 100");
        }

        DateFormat dateFormat = new SimpleDateFormat("MM-dd_HH-mm");
        Date date = new Date();

        String fileName = "split_" + percentOfTrainSet + "_" + dateFormat.format(date) + this.EXTENSION;

        try (OutputStream newFile = new BufferedOutputStream(Files.newOutputStream(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS +"\\" + fileName)))) {
            Random generator = new Random();
            for (int i = 0; i < numberOfData; i++) {
                if (generator.nextDouble() <=  ((double)percentOfTrainSet) / 100) {
                    newFile.write((ArticleSets.TRAIN.getIntValue()));
                } else {
                    newFile.write((ArticleSets.TEST.getIntValue()));
                }
            }
        } catch (InvalidPathException e) {
            System.err.println("Path error: " + e);
        } catch (IOException e) {
            System.err.println("In-out error: " + e);
        }
        return fileName;
    }
}
