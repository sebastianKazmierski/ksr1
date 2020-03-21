package interfaceModule;

import constants.Constants;
import data.ArticleStore;
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

public class ConsoleInterface<T extends Enum<T>> implements UserInterface<T> {
    Scanner in;
    ChoseElementInterface<FeatureExtractor<T>> choseFeatureExtractors;
    ChoseElementInterface<DistanceMeasurement> choseDistanceMeasurement;
    ChoseNumberOfNeighbours choseNumberOfNeighbours;
    ChoseLabel choseLabel;

    public ConsoleInterface() {
        this.in = new Scanner(System.in);
        this.choseFeatureExtractors = new ChoseElementInterface<>(this.in, TypeOfChoice.MULTIPLE);
        this.choseDistanceMeasurement = new ChoseElementInterface<>(this.in, TypeOfChoice.SINGLE);
        this.choseNumberOfNeighbours = new ChoseNumberOfNeighbours(this.in);
        this.choseLabel = new ChoseLabel(this.in);
    }

    public void displayResult(ArticleStore<T> articleStore, List<FeatureExtractor<T>> featureExtractorList, DistanceMeasurement distanceMeasurement, int numberOfNeighbours) {
        
    }

    @Override
    public String getLabel() {
        return this.choseLabel.getLabel();
    }

    @Override
    public int getNumberOfNeighbours() {
        return this.choseNumberOfNeighbours.getNumberOfNeighbours();
    }

    @Override
    public List<FeatureExtractor<T>> getFeatureExtractors(List<FeatureExtractor<T>> availableFeatureExtractors) {
        return this.choseFeatureExtractors.getAnswer(availableFeatureExtractors);
    }

    @Override
    public DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements) {
        return this.choseDistanceMeasurement.getAnswer(availableDistanceMeasurements).get(0);
    }

    @Override
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
        return this.in.nextLine().trim();
    }
}
