package data;

import constants.Constants;
import convertDataModule.Stemmer;
import convertDataModule.StopList;
import featuresModels.Functions;
import grouping.Place;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.text.StringEscapeUtils;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Article {
    private String content;
    private Place place;
    private List<String> contentTokens;
    private List<String> contentTokensAfterStopList;
    private List<String> contentTokensAfterStemming;
    private Integer numberOfWordsAfterStemming;
    @Setter
    private Map<Place, Integer> keyWordsForPlaces;

    public Article(String content, Place place) {
        this.content = this.prepareContent(content);
        this.place = place;
        this.tokenizeContent();
        this.setContentTokensAfterStopList();
        this.stem();
        this.setNumberOfWordsAfterStemming();
    }

    public Integer getNumberOfKeyWordsForPlace(Place place) {
        return keyWordsForPlaces.getOrDefault(place, 0);
    }

    private void stem() {
        contentTokensAfterStemming = Stemmer.stem(contentTokensAfterStopList);
    }

    private String prepareContent(String content) {
        String newContent = content.trim();
        String suffixToRemove = "REUTER";
        if (newContent.endsWith(suffixToRemove)) {
            newContent = newContent.substring(0, newContent.length() - suffixToRemove.length());
        }
        return StringEscapeUtils.unescapeXml(newContent.trim());
    }

    private void setNumberOfWordsAfterStemming() {
        numberOfWordsAfterStemming = Math.toIntExact(contentTokensAfterStemming.stream().filter(e -> Constants.END_WORD_PUNCTUATION.contains(e.substring(e.length() - 1))).count());
    }

    private void tokenizeContent() {
        contentTokens = Collections.list(new StringTokenizer(content, " \\\n")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    private void setContentTokensAfterStopList() {
        contentTokensAfterStopList = new ArrayList<>();

        for (String word : contentTokens) {
            List<String> wordAndPunctuationMarks = Functions.deletePunctuationMarksFromEnd(word);
            List<String> stopList = StopList.getStopList();

            if (!stopList.contains(wordAndPunctuationMarks.get(0).toLowerCase())) {
                if (!wordAndPunctuationMarks.get(0).isEmpty()) {
                    contentTokensAfterStopList.add(wordAndPunctuationMarks.get(0));
                }
                if (!wordAndPunctuationMarks.get(1).isEmpty()) {
                    contentTokensAfterStopList.add(wordAndPunctuationMarks.get(1));
                }
            }
        }
    }

    @Override
    public String toString() {
        return "all.Article{" +
                "content='" + content + '\'' +
                "\nplace=" + place +
                '}';
    }
}
