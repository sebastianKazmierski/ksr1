/*
import featuresModels.*;
import featuresModels.keyWords.WordHolder;
import grouping.Place;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    public void selectFeaturesExtractors2(WordHolder wordHolder) {
        selectFeaturesExtractors(f());
    }

    public void selectFeaturesExtractors(List<FeatureExtractor> featureExtractors) {
        for (int i = 0; i < featureExtractors.size(); i++) {
            System.out.println((i + 1) + ". " + featureExtractors.get(i));
        }
        System.out.println("Wybierz cechy na podstawie któych chcesz dokonać grupowania: (podaj numery wybranych cech oddzielone spacją");
        System.out.print("> ");
        Scanner in = new Scanner(System.in);
        List<String> chosen = Arrays.asList(in.nextLine().split(" "));
        for (String number : chosen) {
            System.out.println(featureExtractors.get(Integer.parseInt(number)).description());
        }
    }

    public List<FeatureExtractor> f(WordHolder wordHolder) {
        List<FeatureExtractor> featureExtractorList = new ArrayList<>();

        featureExtractorList.add(new AverageLengthOfParagraph());
        featureExtractorList.add(new AverageLengthOfProperName());
        featureExtractorList.add(new AverageLengthOfSentences());
        featureExtractorList.add(new LengthOfText());
        featureExtractorList.add(new NumberOfKeyWordsInTenFirstPercentOfText(wordHolder));
        featureExtractorList.add(new NumberOfKeyWordsInWholeText(wordHolder));
        featureExtractorList.add(new NumberOfParagraphsInRelationToLengthOfText(new LengthOfText()));
        featureExtractorList.add(new NumberOfProperNameInRelationToLengthOfText());
        featureExtractorList.add(new NumberOfUniqueKeyWordsInRelationToLengthOfText(wordHolder,new LengthOfText()));
        featureExtractorList.add(new NumberOfWordsRemoveByStopListInRelationToLengthOfTextAfterStopList());
        featureExtractorList.add(new NumberOfWordsWhichAreMultipleTimesInTextInRelationToLengthOfText());

        for (Place place : Place.values()) {
            featureExtractorList.add(new NumberOfKeyWordsInPlace(place,wordHolder));
        }

        return featureExtractorList;
    }



}
*/
