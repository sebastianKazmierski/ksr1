package all;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
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
        contentTokens = Collections.list(new StringTokenizer(content, " ")).stream()
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
