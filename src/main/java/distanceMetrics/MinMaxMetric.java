package distanceMetrics;

import java.util.List;

public class MinMaxMetric implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        double min = 0.0;
        double max = 0.0;

        for (int i = 0; i < testElement.size(); i++) {
            if (testElement.get(i) > trainElement.get(i)) {
                max += testElement.get(i);
                min += trainElement.get(i);
            } else {
                max += trainElement.get(i);
                min += testElement.get(i);
            }
        }

        return max / min;
    }

    @Override
    public String description() {
        return "Minimum-maximum";
    }
}
