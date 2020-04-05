package interfaceModule.knnParameters;

import interfaceModule.ElementSelectedByUser;

public interface KnnParameter extends ElementSelectedByUser {

    default String wordToInsertIntoQuestionAboutThisObject() {
        return "parametr algorytmu knn";
    }

    KnnParameterType getParameterType();
}
