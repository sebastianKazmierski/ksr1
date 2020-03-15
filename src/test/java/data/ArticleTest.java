package data;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleTest {

    @Test
    void getContentTokensAfterStopList() {
        String content1 = "  Alice HAS A cat, whose name is Bob. Alice like Bob.\nBob like Alice. They are on the Eiffel Tower. ";
        Article article1 = new Article(content1, List.of("USA"));
        assertEquals(article1.getContentTokensAfterStopList(),List.of("Alice","cat",",","whose","name","Bob",".","Alice","like","Bob",".","Bob",
                "like","Alice",".","Eiffel","Tower","."));


        String content2 = "  There is a lot ...\nAlice has a dog!!! Whose is this cat?!";
        Article article2 = new Article(content2, List.of("USA"));
        assertEquals(article2.getContentTokensAfterStopList(),List.of("lot","...","Alice","dog","!!!","Whose","cat","?!"));
    }

    @Test
    void getContentTokens() {
        String content = "  There is a lot ...\nAlice has a dog!!! Whose is this cat?! This is Bob 's cat.";
        Article article = new Article(content, List.of("USA"));
        assertEquals(List.of("There","is","a","lot","...","Alice","has","a","dog!!!","Whose","is","this","cat?!","This","is","Bob","'s","cat."),article.getContentTokens());
    }

}