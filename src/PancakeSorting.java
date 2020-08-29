import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {
    // step 1: find the max value in this round
    // step 2: flip it to the top
    // step 3: flip it to the position it should be in
    public List<Integer> pancakeSort(int[] a) {
        int n = a.length;
        List<Integer> r = new ArrayList<>();
        for (int k = n - 1; k >= 1; k--) {
            int i = 0;
            while (i < n && a[i] != k + 1) {
                i++;
            }
            if (i != 0) {
                r.add(i + 1);
                reverse(a, 0, i);
            }
            r.add(k + 1);
            reverse(a, 0, k);
        }
        return r;

    }

    private void reverse(int[] a, int i, int j) {
        while (i < j) {
            int tmp = a[i];
            a[i] = a[j];
            a[j] = tmp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 4, 1};
        System.out.println(new PancakeSorting().pancakeSort(a));
    }
}
