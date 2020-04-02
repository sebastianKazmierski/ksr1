package other;

import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Label;

import java.util.List;

public class CaseDescription<T extends Label<T>> {
    private Class<T> tClass;
    private String fileName;
    private List<FeatureExtractor<T>> featureExtractorList;
    private DistanceMeasurement distanceMeasurement;
    private int numberOfNeighbours;
    private Result<T> result;

    public CaseDescription(Class<T> tClass, String fileName, List<FeatureExtractor<T>> featureExtractorList, DistanceMeasurement distanceMeasurement, int numberOfNeighbours, Result<T> result) {
        this.tClass = tClass;
        this.fileName = fileName;
        this.featureExtractorList = featureExtractorList;
        this.distanceMeasurement = distanceMeasurement;
        this.numberOfNeighbours = numberOfNeighbours;
        this.result = result;
    }

    public Result<T> getResult() {
        return this.result;
    }

    public String getLabel() {
        return this.tClass.getEnumConstants()[0].description();
    }

    public String getDataSplit() {
        String regex = "_";
        String[] split = this.fileName.split(regex);
        return split[1];
    }

    public String getFeatureExtractorList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FeatureExtractor<T> tFeatureExtractor : this.featureExtractorList) {
            stringBuilder.append(tFeatureExtractor.description());
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }

    public String getDistanceMeasurement() {
        return this.distanceMeasurement.description();
    }

    public String getNumberOfNeighbours() {
        return Integer.toString(this.numberOfNeighbours);
    }
}
