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
        return split[1]+"%";
    }

    public String getFeatureExtractorList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FeatureExtractor<T> tFeatureExtractor : this.featureExtractorList) {
            stringBuilder.append(tFeatureExtractor.description());
            stringBuilder.append(" | ");
        }
        return stringBuilder.toString();
    }

    public String getFeatureExtractorNumberList() {
        StringBuilder stringBuilder = new StringBuilder();
        for (FeatureExtractor<T> tFeatureExtractor : this.featureExtractorList) {
            stringBuilder.append(tFeatureExtractor.getNumber());
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
        int numberOfBasicFields = 9;
        int numberOfAdditionalFields = enumConstants.length * 2;
        String[] headers = new String[numberOfBasicFields + numberOfAdditionalFields];
        headers[0] = "Etykieta";
        headers[1] = "Podział danych";
        headers[2] = "Cechy";
        headers[3] = "Cechy numerycznie";
        headers[4] = "Metryka";
        headers[5] = "Liczba sąsiadów";
        headers[6] = "Accuracy";
        headers[7] = "Średni Recall";
        headers[8] = "Średni Precision";


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
        row.add(getFeatureExtractorNumberList());
        row.add(getDistanceMeasurement());
        row.add(getNumberOfNeighbours());

        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        row.add(decimalFormat.format(this.result.getAccuracy()));
        double recall_sum = 0.0;
        double precision_sum = 0.0;

        T[] enumConstants = this.tClass.getEnumConstants();

        for (T enumConstant : enumConstants) {
            recall_sum += this.result.getRecall(enumConstant);
            precision_sum += this.result.getPrecision(enumConstant);
        }
        double avg_recall = recall_sum / enumConstants.length;
        double avg_precision = precision_sum / enumConstants.length;

        row.add(decimalFormat.format(avg_recall));
        row.add(decimalFormat.format(avg_precision));

        for (T enumConstant : enumConstants) {
            row.add(decimalFormat.format(this.result.getRecall(enumConstant)));
            row.add(decimalFormat.format(this.result.getPrecision(enumConstant)));
        }
        return row;
    }
}
