import java.util.Arrays;

public class NumberOfDistinctCategories {

    interface CategoryHandler {

        public boolean haveSameCategory(int a, int b);
    }


    private int[] pa;

    public int numberOfCategories(int n, CategoryHandler cat) {
        pa = new int[n];
        Arrays.fill(pa, -1);
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                if (cat.haveSameCategory(i, j)) {
                    union(i, j);
                }
            }
        }
        int res = 0;
        for (int i = 0; i < n; ++i) {
            if (pa[i] == -1) {
                ++res;
            }
        }
        return res;
    }

    private int find(int i) {
        if (pa[i] == -1) {
            return i;
        }
        int rt = find(pa[i]);
        pa[i] = rt;
        return rt;
    }

    private void union(int i, int j) {
        int ai = find(i);
        int aj = find(j);
        if (ai == aj) {
            return;
        }
        pa[ai] = aj;
    }
}
