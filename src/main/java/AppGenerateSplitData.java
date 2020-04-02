import data.GenerateSplitOfData;

public class AppGenerateSplitData {
    public static void main(String[] args) {
        GenerateSplitOfData generateSplitOfData = new GenerateSplitOfData();

        generateSplitOfData.generate(21000, 50);
        generateSplitOfData.generate(21000, 70);
    }
}
