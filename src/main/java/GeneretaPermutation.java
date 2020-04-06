public class GeneretaPermutation {
    public static void main(String[] args) {
        for (int i = 1; i < 12; i++) {
            for (int j = i + 1; j < 12; j++) {
                for (int k = j+1; k < 12; k++) {
                    System.out.println(i+" "+j+" "+k);
                }
            }
        }
    }
}
