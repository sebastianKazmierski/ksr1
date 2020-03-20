package interfaceModule;

import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;

import java.util.List;

public interface UserInterface {
    String getLabel();

    int getNumberOfNeighbours();

    List<FeatureExtractor> getFeatureExtractors(List<FeatureExtractor> availableFeatureExtractors);

    DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements);

    String getFileWithDataSplit();
}
