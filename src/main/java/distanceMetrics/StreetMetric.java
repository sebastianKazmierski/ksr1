package distanceMetrics;

import java.util.List;

public class StreetMetric implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        double result = 0.0;
        for (int i = 0; i < testElement.size(); i++) {
            result += Math.abs(testElement.get(i) - trainElement.get(i));
        }
        return result;
    }

        @Override
            public String description() {
                return "Metryka Uliczna";
            }
}
