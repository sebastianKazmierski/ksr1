package interfaceModule.knnParameters;

public class NumberOfNeighboursParameter implements  KnnParameter {
    @Override
    public String description() {
        return "Liczba sąsiadów";
    }

    @Override
    public KnnParameterType getParameterType() {
        return KnnParameterType.NUMBER_OF_NEIGHBOURS;
    }
}
