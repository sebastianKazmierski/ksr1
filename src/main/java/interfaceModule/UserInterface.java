package interfaceModule;

import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;

import java.util.List;

public interface UserInterface<T extends Enum<T>> {
    String getLabel();

    int getNumberOfNeighbours();

    List<FeatureExtractor<T>> getFeatureExtractors(List<FeatureExtractor<T>> availableFeatureExtractors);

    DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements);

    String getFileWithDataSplit();
}
