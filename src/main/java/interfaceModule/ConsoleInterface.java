package interfaceModule;

import changeSettings.ChangeSettings;
import changeSettings.ChangeSettingsType;
import constants.Constants;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Label;
import other.CaseDescription;
import other.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInterface<T extends Label<T>> implements UserInterface<T> {
    Scanner in;
    ChoseElementInterface<FeatureExtractor<T>> choseFeatureExtractors;
    ChoseElementInterface<DistanceMeasurement> choseDistanceMeasurement;
    ChoseNumberOfNeighbours choseNumberOfNeighbours;
    ChoseElementInterface<ChangeSettings> choseChangeSettings;
    T[] enumConstants;


    public ConsoleInterface(Class<T> tClass) {
        this.enumConstants = tClass.getEnumConstants();
        this.in = new Scanner(System.in);
        this.choseFeatureExtractors = new ChoseElementInterface<>(this.in, TypeOfChoice.MULTIPLE);
        this.choseDistanceMeasurement = new ChoseElementInterface<>(this.in, TypeOfChoice.SINGLE);
        this.choseNumberOfNeighbours = new ChoseNumberOfNeighbours(this.in);
        this.choseChangeSettings = new ChoseElementInterface<>(this.in, TypeOfChoice.SINGLE);
    }

    public void displayResult(Result<T> result) {
        System.out.println("Accuracy: " + result.getAccuracy());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (T enumConstant : this.enumConstants) {
            System.out.println();
            System.out.println("Etykieta:  " + enumConstant.getLabel());
            System.out.println("Recall:    " + decimalFormat.format(result.getRecall(enumConstant)));
            System.out.println("Precision: " + decimalFormat.format(result.getPrecision(enumConstant)));
        }
        this.in.nextLine();
    }

    public void displayResultInColumn(CaseDescription<T> caseDescription) {
        System.out.println(caseDescription.getLabel());
        System.out.println(caseDescription.getDataSplit());
        System.out.println(caseDescription.getFeatureExtractorList());
        System.out.println(caseDescription.getDistanceMeasurement());
        System.out.println(caseDescription.getNumberOfNeighbours());

        Result<T> result = caseDescription.getResult();
        System.out.println(result.getAccuracy());
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (T enumConstant : this.enumConstants) {
            System.out.println(decimalFormat.format(result.getRecall(enumConstant)));
            System.out.println(decimalFormat.format(result.getPrecision(enumConstant)));
        }
        this.in.nextLine();
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

    @Override
    public ChangeSettingsType getChangeSettings(List<ChangeSettings> changeSettingsList) {
        return this.choseChangeSettings.getAnswer(changeSettingsList).get(0).getChange();
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
