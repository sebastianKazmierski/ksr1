package distanceMetrics;

import java.util.List;

public class AverageMinimumMetric implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        double min = 0.0;
        double sum = 0.0;

        for (int i = 0; i < testElement.size(); i++) {
            min += Math.min(testElement.get(i), trainElement.get(i));
            sum += testElement.get(i) + trainElement.get(i);
        }

        return min / (sum * 0.5);
    }

        @Override
            public String description() {
                return "Średnia arytmetyczna minimum";
            }
}
