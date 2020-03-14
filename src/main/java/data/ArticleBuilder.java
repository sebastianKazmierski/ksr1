package data;

import java.util.List;

public class ArticleBuilder {
    private String content;
    private List<String> place;

    public void withContent(String content) {
        this.content = content;
    }

    public void withPlace(List<String> place) {
        this.place = place;
    }

    public Article build() {
        return new Article(content, place);
    }
}
