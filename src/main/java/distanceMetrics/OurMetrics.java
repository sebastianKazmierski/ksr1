package distanceMetrics;

import java.util.ArrayList;
import java.util.List;

public class OurMetrics implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        List<Double> distances = new ArrayList<>();
        for (int i = 0; i < testElement.size(); i++) {
            distances.add( Math.abs(testElement.get(i) - trainElement.get(i)));
        }
        double interval = 0.5 / distances.size();
        distances.sort(Double::compareTo);
        double quotient = 1.0;
        double result = 0.0;
        for (Double distance : distances) {
            result += quotient * distance;
            quotient -= interval;
        }
        return result;
    }

    @Override
    public String description() {
        return "Nasz metryka";
    }
}
