package other;

import distanceMetrics.DistanceMeasurement;
import featuresModels.FeatureExtractor;
import grouping.Label;

import java.text.DecimalFormat;
import java.util.ArrayList;
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

    public String[] getHeaders() {
        T[] enumConstants = this.tClass.getEnumConstants();
        int numberOfBasicFields = 6;
        int numberOfAdditionalFields = enumConstants.length * 2;
        String[] headers = new String[numberOfBasicFields + numberOfAdditionalFields];
        headers[0] = "Etykieta";
        headers[1] = "Podział danych";
        headers[2] = "Cechy";
        headers[3] = "Metryka";
        headers[4] = "Liczba sąsiadów";
        headers[5] = "Accuracy";

        int i = numberOfBasicFields;
        for (T enumConstant : enumConstants) {
            headers[i++] = "Recall " + enumConstant.getLabel();
            headers[i++] = "Precision " + enumConstant.getLabel();
        }
        return headers;
    }


    public List<String> getRow() {
        List<String> row = new ArrayList<>();
        row.add(getLabel());
        row.add(getDataSplit());
        row.add(getFeatureExtractorList());
        row.add(getDistanceMeasurement());
        row.add(getNumberOfNeighbours());

        row.add(Double.toString(this.result.getAccuracy()));
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (T enumConstant : this.tClass.getEnumConstants()) {
            row.add(decimalFormat.format(this.result.getRecall(enumConstant)));
            row.add(decimalFormat.format(this.result.getPrecision(enumConstant)));
        }
        return row;
    }
}
