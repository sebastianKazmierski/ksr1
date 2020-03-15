package data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void getContentTokensAfterStopList() {
        String contetn = "  Alice HAS A cat, whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article article = new Article(contetn, List.of("USA"));
        assertEquals(article.getContentTokensAfterStopList(),List.of("Alice","cat",",","whose","name","Bob",".","Alice","like","Bob",".","Bob",
                "like","Alice",".","Eiffel","Tower","."));
    }
}