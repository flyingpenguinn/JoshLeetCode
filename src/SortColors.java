public class SortColors {

    public void sortColors(int[] a) {
        int i = -1;
        int j = 0;
        int k = a.length;
        while (j < k) {
            if (a[j] == 0) {
                swap(a, ++i, j);
                j++;
            } else if (a[j] == 2) {
                swap(a, --k, j); // not moving j yet because k could be a 0
            } else {
                j++;
            }
        }
        // either move j or k so must finish
        // invariant: 0...i are all 0. k....n-1 are all 2. i+1...j-1 are all 1. j...k-1 are waiting to be checked
    }

    private void swap(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}

class SortColorTwoPassPartition {
    public void sortColors(int[] a) {
        int end = partition(a, 0, 0, a.length - 1);
        partition(a, 1, end + 1, a.length - 1);
    }

    // partition so that first part <= t, second part > target
    private int partition(int[] a, int t, int l, int u) {
        // i is the last position that is <=
        int i = l - 1; // l-1
        int j = l;
        while (j <= u) {
            if (a[j] <= t) {
                swap(++i, j, a);
            }
            j++;
        }
        return i;
    }

    private void swap(int i, int j, int[] a) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
