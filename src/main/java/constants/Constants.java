package constants;

import java.util.List;

public class Constants {
    public static final String DIRECTORY_WITH_ARTICLES_XML = "src/main/resources/textsXML";

    public static final String PATH_TO_DIRECTORY_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS = "src/main/resources/dataSplit";
    public static final String NAME_OF_FILE_WITH_DATA_SPLIT_ON_TEST_AMD_TRAIN_SETS = "split_30_03-13_21-30.sd";

    public static final String PATH_TO_DIRECTORY_WITH_STOP_LIST = "src/main/resources/stopLists";
    public static final String NAME_OF_FILE_WITH_STOP_LIST = "stopList2.txt";

    public static final List<String> END_WORD_PUNCTUATION = List.of("!", "?", ",", ".", ":", ";");
}
