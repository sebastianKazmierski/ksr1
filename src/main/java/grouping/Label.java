package grouping;

import interfaceModule.ElementSelectedByUser;

public interface Label<T> extends ElementSelectedByUser {
    String getLabel();

    @Override
    default String wordToInsertIntoQuestionAboutThisObject() {
        return "etykietę";
    }
}
