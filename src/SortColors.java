public class SortColors {

    /*  i is last 0,j is last 2.
    between m and j is unknown
    000i111m.....j2222
    */
    public void sortColors(int[] a) {
        int i = -1;
        int k = a.length;
        int j = 0;
        while (j < k) {
            if (a[j] == 0) {
                swap(a, ++i, j);
                j++;
            } else if (a[j] == 1) {
                j++;
            } else {
                // ==2
                swap(a, --k, j);
                // not moving j in case this is a 0 we need to switch it further
            }
        }
    }

    void swap(int[] a, int i, int j) {
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
