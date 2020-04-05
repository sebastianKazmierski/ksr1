package interfaceModule.knnParameters;

public class FeaturesParameter implements KnnParameter {
    @Override
    public String description() {
        return "Cechy";
    }

    @Override
    public KnnParameterType getParameterType() {
        return KnnParameterType.FEATURES;
    }
}
