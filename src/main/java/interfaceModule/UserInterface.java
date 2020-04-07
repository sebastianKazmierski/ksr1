package interfaceModule;

import changeSettings.ChangeSettings;
import changeSettings.ChangeSettingsType;
import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Label;
import interfaceModule.knnParameters.KnnParameter;
import interfaceModule.knnParameters.KnnParameterType;
import result.CaseDescription;

import java.io.IOException;
import java.util.List;

public interface UserInterface<T extends Label<T>> {
    int getNumberOfNeighbours();

    List<FeatureExtractor<T>> getFeatureExtractors(List<FeatureExtractor<T>> availableFeatureExtractors);

    DistanceMeasurement getDistanceMeasurement(List<DistanceMeasurement> availableDistanceMeasurements);

    String getFileWithDataSplit();

    ChangeSettingsType getChangeSettings(List<ChangeSettings> changeSettingsList);

    void displayResult(CaseDescription<T> caseDescription);

    void displayResultInColumn1(List<CaseDescription<T>> caseDescriptions, ChangeSettingsType changeSettingsType) throws IOException;

    int getNumberOfSequence();

    KnnParameterType getKnnParameterType(List<KnnParameter> knnParameters);

    void displayResultInColumn(CaseDescription<T> caseDescription);
}
