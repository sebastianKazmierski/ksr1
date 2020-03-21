package interfaceModule;

import changeSettings.ChangeSettings;
import changeSettings.ChangeSettingsType;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;

import java.util.List;

public interface UserInterface<T extends Enum<T>> {
    int getNumberOfNeighbours();

    List<FeatureExtractor<T>> getFeatureExtractors(List<FeatureExtractor<T>> availableFeatureExtractors);

    DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements);

    String getFileWithDataSplit();

    ChangeSettingsType getChangeSettings(List<ChangeSettings> changeSettingsList);
}
