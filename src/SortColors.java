public class SortColors {

    /*  i is last 0,j is last 2.
    between m and j is unknown
    000i111m.....j2222
    */
    public void sortColors(int[] a) {
        int i = -1;
        int j = a.length;
        int m = 0;
        while (m < j) { // <j !
            if (a[m] == 0) {
                swap(a, ++i, m++);
            } else if (a[m] == 1) {
                m++;
            } else {
                // cant move mid as j ciuld be anything
                swap(a, --j, m);
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
        int nextStart = partition(a, 0, 0, a.length - 1);
        partition(a, 1, nextStart, a.length - 1);
    }

    // partition so that first part <= target, second part > target
    private int partition(int[] a, int target, int l, int u) {
        // i is the last position that is <=
        int i = l - 1; // l-1
        int j = l;
        while (j <= u) {
            if (a[j] <= target) {
                swap(++i, j, a);
            }
            j++;
        }
        return i + 1;
    }

    private void swap(int i, int j, int[] a) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }
}
