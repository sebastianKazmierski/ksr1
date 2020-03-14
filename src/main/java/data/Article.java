package data;

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
    }

    public void tokenizeContent() {
        contentTokens = Collections.list(new StringTokenizer(content, " \\\n")).stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "all.Article{" +
                "content='" + content + '\'' +
                "\nplace=" + place +
                '}';
    }
}
