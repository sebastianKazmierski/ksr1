package interfaceModule.knnParameters;

public class DataSplitParameter implements KnnParameter {
    @Override
    public String description() {
        return "Podział na zbiór uczący u testowy";
    }

    @Override
    public KnnParameterType getParameterType() {
        return KnnParameterType.DATA_SPLIT;
    }
}
