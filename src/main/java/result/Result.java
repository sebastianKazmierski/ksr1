package result;

import grouping.Label;

public class Result<T extends Label<T>> {
    T[] enumConstants;
    int[][] matrix;

    public Result(Class<T> tClass) {
        this.enumConstants = tClass.getEnumConstants();
        this.matrix = new int[this.enumConstants.length][this.enumConstants.length];
    }

    public void addResult(T actual, T predicated) {
        int actualIndex = getIndex(actual);
        int predicatedIndex = getIndex(predicated);
        this.matrix[actualIndex][predicatedIndex]++;
    }

    private int getIndex(T label) {
        for (int i = 0; i < this.enumConstants.length; i++) {
            if (this.enumConstants[i] == label) {
                return i;
            }
        }
        return 0;
    }

    public double getAccuracy() {
        double sumOfCorrect = 0.0;
        double sumOfAll = 0.0;

        for (int i = 0; i < this.matrix.length; i++) {
            sumOfCorrect += this.matrix[i][i];
        }

        for (int[] row : this.matrix) {
            for (int value : row) {
                sumOfAll+=value;
            }
        }

        return sumOfCorrect / sumOfAll;
    }
    
    public double getPrecision(T label) {
        int predicatedIndex = getIndex(label);
        double correctPredicated = this.matrix[predicatedIndex][predicatedIndex];
        double allPredicated = 0.0;

        for (int i = 0; i < this.matrix.length; i++) {
            allPredicated += this.matrix[i][predicatedIndex];
        }

        if (allPredicated == 0) {
            return 0;
        }

        return correctPredicated / allPredicated;
    }

    public double getRecall(T label) {
        int actualIndex = getIndex(label);
        double correctPredicated = this.matrix[actualIndex][actualIndex];
        double allActual = 0.0;

        for (int i = 0; i < this.matrix.length; i++) {
            allActual += this.matrix[actualIndex][i];
        }

        if (allActual == 0) {
            return 0;
        }

        return correctPredicated / allActual;
    }


}
