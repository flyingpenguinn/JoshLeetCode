import java.util.TreeSet;

public class FindSamllestCommonElementInAllRows {
    public int smallestCommonElement(int[][] a) {
        int[] indexes = new int[a.length];

        while (true) {
            TreeSet<Integer> temp = new TreeSet<>();
            for (int i = 0; i < a.length; i++) {
                if (indexes[i] >= a[i].length) {
                    return -1;
                }
                temp.add(a[i][indexes[i]]);
            }
            if (temp.size() == 1) {
                return temp.first();
            } else {
                int biggest = temp.last();
                for (int i = 0; i < a.length; i++) {
                    while (indexes[i] < a[i].length && a[i][indexes[i]] < biggest) {
                        indexes[i]++;
                    }
                }
            }
        }
    }
}
