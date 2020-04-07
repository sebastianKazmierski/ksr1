import java.util.*;
import java.util.stream.Collectors;

public class CountPointForFeature {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int number = Integer.parseInt(in.nextLine().trim());
        Map<Integer, Integer> map = new HashMap<>();

        int points = number;
        for (int i = 0; i < number; i++) {
            String s = in.nextLine().trim();
            String regex = "\\|";
            String[] split = s.split(regex);
            Integer[] array = new Integer[split.length];
            for (int i1 = 0; i1 < array.length; i1++) {
                array[i1] = Integer.parseInt(split[i1].trim());
            }

            for (Integer s1 : array) {
                if (map.containsKey(s1)) {
                    map.put(s1, map.get(s1) + points);
                } else {
                    map.put(s1, points);
                }
            }
            points--;
        }

        final LinkedHashMap<Integer,Integer> sortedByCount = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

        sortedByCount.forEach((key,value) -> System.out.println(key+". "+value));
    }
}
