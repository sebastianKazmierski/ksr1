import data.GenerateSplitOfData;

public class AppGenerateSplitData {
    public static void main(String[] args) {
        GenerateSplitOfData generateSplitOfData = new GenerateSplitOfData();

        generateSplitOfData.generate(21000, 60);
        generateSplitOfData.generate(21000, 40);
    }
}
