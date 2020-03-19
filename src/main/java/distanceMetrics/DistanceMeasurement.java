package distanceMetrics;

import interfaceModule.ElementSelectedByUser;

import java.util.List;

public interface DistanceMeasurement extends ElementSelectedByUser {
    double count(List<Double> testElement, List<Double> trainElement);

    default String wordToInsertIntoQuestionAboutThisObject () {
        return "metrykę";
    }
}
