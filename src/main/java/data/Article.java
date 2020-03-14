package data;

import convertDataModule.StopList;
import featuresModels.Functions;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

@Getter
public class Article {
    private String content;
    private List<String> place;
    private List<String> contentTokens;
    private List<String> contentTokensAfterStopList;

    public Article(String content, List<String> place) {
        this.content = content;
        this.place = place;
        this.tokenizeContent();
        this.setContentTokensAfterStopList();
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

            if (!stopList.contains(wordAndPunctuationMarks.get(0))) {
                contentTokensAfterStopList.add(wordAndPunctuationMarks.get(0));
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
