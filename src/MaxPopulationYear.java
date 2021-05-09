import java.util.TreeMap;

public class MaxPopulationYear {
    public int maximumPopulation(int[][] logs) {
        TreeMap<Integer, Integer> m = new TreeMap<>();
        for (int[] log : logs) {
            m.put(log[0], m.getOrDefault(log[0], 0) + 1);
            m.put(log[1], m.getOrDefault(log[1], 0) - 1);
        }
        int csum = 0;
        int maxsum = -1;
        int maxk = -1;
        for (int k : m.keySet()) {
            csum += m.get(k);
            if (csum > maxsum) {
                maxsum = csum;
                maxk = k;
            }
        }
        return maxk;
    }
}
