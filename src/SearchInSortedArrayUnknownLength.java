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

    public int search(ArrayReader reader, int target) {
        long u = 1;
        // u could be beyond range, or just >=target
        while (reader.get((int) u) < target) {
            u = u << 1;
        }
        long l = u / 2;
        while (l <= u) {
            long mid = (int) (l + (u - l) / 2);
            if (reader.get((int) mid) >= target) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        // l is the first == or bigger need another check
        if (reader.get((int) l) == target) {
            return (int) l;
        } else {
            return -1;
        }
    }
}
