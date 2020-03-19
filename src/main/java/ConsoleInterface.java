import featuresModels.*;
import featuresModels.keyWords.WordHolder;
import grouping.Place;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ConsoleInterface {
    Scanner in = new Scanner(System.in);

    public void selectFeaturesExtractors2(WordHolder wordHolder) {
        selectFeaturesExtractors(f(wordHolder));
    }

    public void selectFeaturesExtractors(List<FeatureExtractor> featureExtractors) {
        for (int i = 0; i < featureExtractors.size(); i++) {
            System.out.println((i + 1) + ". " + featureExtractors.get(i));
        }
        System.out.println("Wybierz cechy na podstawie któych chcesz dokonać grupowania: (podaj numery wybranych cech oddzielone spacją)");
        System.out.print("> ");
        String[] chosen = in.nextLine().trim().split(" ");
        for (String number : chosen) {
            System.out.println(featureExtractors.get(Integer.parseInt(number)).description());
        }
    }

    public List<FeatureExtractor> f(WordHolder wordHolder) {
        List<FeatureExtractor> featureExtractorList = new ArrayList<>();

        featureExtractorList.add(new AverageLengthOfParagraph(new NumberOfParagraphsInRelationToLengthOfText(new LengthOfText())));
        featureExtractorList.add(new AverageLengthOfProperName());
        featureExtractorList.add(new AverageLengthOfSentences());
        featureExtractorList.add(new LengthOfText());
        featureExtractorList.add(new NumberOfKeyWordsInTenFirstPercentOfText(wordHolder));
        featureExtractorList.add(new NumberOfKeyWordsInWholeText(wordHolder));
        featureExtractorList.add(new NumberOfParagraphsInRelationToLengthOfText(new LengthOfText()));
        featureExtractorList.add(new NumberOfProperNameInRelationToLengthOfText());
        featureExtractorList.add(new NumberOfUniqueKeyWordsInRelationToLengthOfText(wordHolder, new LengthOfText()));
        featureExtractorList.add(new NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList());
        featureExtractorList.add(new NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText());

        for (Place place : Place.values()) {
            featureExtractorList.add(new NumberOfKeyWordsInPlace(place, wordHolder));
        }

        return featureExtractorList;
    }

    private String getNameOfFileWithSplit(Stream<Path> paths, String percentOfTrainData) {
        return paths.filter(path -> {
            String regex = "_";
            String[] split = path.getFileName().toString().split(regex);
            return split[1].equals(percentOfTrainData);
        }).toString();
    }

    private List<String> getAvailablePercentOfSplit(Stream<Path> paths) {
        return paths
                .filter(Files::isRegularFile)
                .map(path -> {
                    String regex = "_";
                    String[] split = path.getFileName().toString().split(regex);
                    return split[1];
                })
                .collect(Collectors.toList());
    }


    private List<String> checkSelectedOption(Stream<Path> paths, String chosen) {
        return paths
                .filter(Files::isRegularFile)
                .filter(path -> {
                    String regex = "_";
                    String[] split = path.getFileName().toString().split(regex);
                    return split[1].equals(chosen);
                })
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());
    }

    private String correctChoice(List<String> percentSplit) {
        System.out.println();
        System.out.println(":( Podana wartośc jest nie prawidłowa. Spróbuj jeszcze raz");
        System.out.println();
        return getUserChoice(percentSplit);
    }

    private String getUserChoice(List<String> percentSplit) {
        for (int i = 0; i < percentSplit.size(); i++) {
            System.out.println((i + 1) + ". zestaw treningowy: " + percentSplit.get(i) + ", zestaw testowy" +
                    (100 - Integer.parseInt(percentSplit.get(i))));
        }
        System.out.println("Wybierz podział na zestaw treningowy i testowy: (wpisz ile procent artykułów ma zawierać zestaw treningowy)");
        System.out.print("> ");
        return in.nextLine().trim();
    }


}
