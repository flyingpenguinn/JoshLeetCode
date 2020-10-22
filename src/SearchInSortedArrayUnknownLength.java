public class SearchInSortedArrayUnknownLength {
    class ArrayReader {
        int[] a;

        public ArrayReader(int[] a) {
            this.a = a;
        }

        int get(int k) {
            return k >= a.length ? Integer.MAX_VALUE : a[k];
        }
    }

    public int search(ArrayReader reader, int t) {
        int u = 1;
        // search t so that we will get l = u/2 better than starting from 0
        while (reader.get(u) < t) {
            u <<= 1;
        }
        int l = u / 2;
        u = Math.min(u, 10000);
        while (l <= u) {
            int mid = l + (u - l) / 2;
            int found = reader.get(mid);
            if (found == t) {
                return mid;
            } else if (found < t) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        return -1;
    }
}
