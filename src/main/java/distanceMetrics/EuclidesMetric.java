package distanceMetrics;

import java.util.List;

public class EuclidesMetric implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        double result = 0.0;
        for (int i = 0; i < testElement.size(); i++) {
            result += (testElement.get(i) - testElement.get(i)) * (testElement.get(i) - testElement.get(i));
        }
        return result;
    }
}
