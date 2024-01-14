import java.util.Arrays;

public class CountElementsWithMaxFrequency {
    public int maxFrequencyElements(int[] a) {
        int[] m = new int[101];
        for (int ai : a) {
            ++m[ai];
        }
        Arrays.sort(m);
        int i = m.length - 2;
        int res = m[m.length - 1];
        while (i >= 0 && m[i] == m[m.length - 1]) {
            res += m[m.length - 1];
            --i;
        }
        return res;
    }
}
