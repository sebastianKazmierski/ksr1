package interfaceModule.knnParameters;

public class DistanceMeasurementParameter implements  KnnParameter {
    @Override
    public String description() {
        return "Metryka";
    }

    @Override
    public KnnParameterType getParameterType() {
        return KnnParameterType.DISTANCE_MEASUREMENT;
    }
}
