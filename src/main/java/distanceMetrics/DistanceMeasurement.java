package distanceMetrics;

import java.util.List;

@FunctionalInterface
public interface DistanceMeasurement {
    double count(List<Double> testElement, List<Double> trainElement);
}
