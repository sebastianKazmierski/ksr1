package interfaceModule;

import constants.Constants;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInterface {
    Scanner in;
    ChoseElementInterface<FeatureExtractor> choseFeatureExtractors;
    ChoseElementInterface<DistanceMeasurement> choseDistanceMeasurement;

    public ConsoleInterface() {
        this.in = new Scanner(System.in);
        this.choseFeatureExtractors = new ChoseElementInterface<>(in);
        this.choseDistanceMeasurement = new ChoseElementInterface<>(in);
    }

    public List<FeatureExtractor> getFeatureExtractors(List<FeatureExtractor> featureExtractors)  {
        return choseFeatureExtractors.getFeatureExtractors(featureExtractors);
    }

    public List<DistanceMeasurement> getDistanceMeasurement(List<DistanceMeasurement> distanceMeasurements)  {
        return choseDistanceMeasurement.getFeatureExtractors(distanceMeasurements);
    }

    public String getFileWithDataSplit() {
        String fileName = null;
        try (Stream<Path> paths = Files.walk(Paths.get(Constants.PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS))) {
            List<String> namesOfFilesWithSplitPattern = getNamesOfFilesWithSplitPattern(paths);
            List<String> percentSplit = getAvailablePercentOfSplit(namesOfFilesWithSplitPattern);

            String chosen = getUserChoice(percentSplit);
            while (!percentSplit.contains(chosen)) {
                chosen = correctChoice(percentSplit);
            }

            fileName = getNameOfSelectedFile(namesOfFilesWithSplitPattern, chosen);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private List<String> getNamesOfFilesWithSplitPattern(Stream<Path> paths) {
        return paths.map(path -> path.getFileName().toString()).filter(s -> {
            String regex = "_";
            String[] split = s.split(regex);
            return split.length > 1 && split[0].equals("split");
        }).collect(Collectors.toList());
    }

    private String getNameOfSelectedFile(List<String> fileNames, String finalChosen) {
        for (String fileName : fileNames) {
            String regex = "_";
            String[] split = fileName.split(regex);
            if (split[1].equals(finalChosen)) {
                return fileName;
            }
        }
        return null;
    }

    private List<String> getAvailablePercentOfSplit(List<String> paths) {
        return paths.stream().map(s -> {
            String regex = "_";
            String[] split = s.split(regex);
            return split[1];
        }).collect(Collectors.toList());
    }


    private String correctChoice(List<String> percentSplit) {
        System.out.println();
        System.out.println(":( Podana wartośc jest nie prawidłowa. Spróbuj jeszcze raz");
        System.out.println();
        return getUserChoice(percentSplit);
    }

    private String getUserChoice(List<String> percentSplit) {
        for (int i = 0; i < percentSplit.size(); i++) {
            System.out.println((i + 1) + ". zestaw treningowy: " + percentSplit.get(i) + ", zestaw testowy: " +
                    (100 - Integer.parseInt(percentSplit.get(i))));
        }
        System.out.println("Wybierz podział na zestaw treningowy i testowy: (wpisz ile procent artykułów ma zawierać zestaw treningowy)");
        System.out.print("> ");
        return in.nextLine().trim();
    }
}
