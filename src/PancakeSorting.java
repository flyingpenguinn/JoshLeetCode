import java.util.ArrayList;
import java.util.List;

public class PancakeSorting {
    public List<Integer> pancakeSort(int[] a) {
        int n = a.length;
        List<Integer> r = new ArrayList<>();
        for (int cake = n; cake >= 1; cake--) {
            // from biggest backward
            for (int i = 0; i < n; i++) {
                if (a[i] == cake) {
                    if (i == cake - 1) {
                        // already sorted
                        break;
                    } else {
                        // need a flip, first flip the biggest to top then to the back
                        if (i != 0) {
                            flip(a, 0, i);
                            r.add(i + 1);
                        }
                        if (cake - 1 != 0) {
                            flip(a, 0, cake - 1);
                            r.add(cake);
                        }
                    }
                }
            }
        }
        return r;
    }

    private void flip(int[] a, int i, int j) {
        // i to j flipped, inclusive
        while (i < j) {
            swap(a, i++, j--);
        }
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        int[] a = {3, 2, 4, 1};
        System.out.println(new PancakeSorting().pancakeSort(a));
    }
}
