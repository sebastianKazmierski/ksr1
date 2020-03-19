package distanceMetrics;

import java.util.List;

public interface DistanceMeasurement {
    double count(List<Double> testElement, List<Double> trainElement);

    String description();
}
