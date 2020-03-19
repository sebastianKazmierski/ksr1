package distanceMetrics;

import java.util.List;

public class CzebyszewMetric implements DistanceMeasurement {
    @Override
    public double count(List<Double> testElement, List<Double> trainElement) {
        double result = 0;
        double distance;
        for (int i = 0; i < testElement.size(); i++){
            distance = Math.abs(testElement.get(i) - trainElement.get(i));
            if(distance > result)
                result = distance;
        }
        return result;
    }
}
