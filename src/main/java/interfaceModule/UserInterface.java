package interfaceModule;

import changeSettings.ChangeSettings;
import changeSettings.ChangeSettingsType;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Label;
import other.CaseDescription;
import other.Result;

import java.util.List;

public interface UserInterface<T extends Label<T>> {
    int getNumberOfNeighbours();

    List<FeatureExtractor<T>> getFeatureExtractors(List<FeatureExtractor<T>> availableFeatureExtractors);

    DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements);

    String getFileWithDataSplit();

    ChangeSettingsType getChangeSettings(List<ChangeSettings> changeSettingsList);

    void displayResult(Result<T> result);

    void displayResultInColumn(CaseDescription<T> caseDescription);
}
