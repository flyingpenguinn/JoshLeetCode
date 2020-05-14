import java.util.Arrays;

public class SquareOfSortedArray {
    public int[] sortedSquares(int[] a) {
        // we can also do it reversed so we dont need to binary search 0 first
        // r[size--] = xxx, one pointer from 0, the other from end of the array
        int biggestNegative = binarySearchFirstSmaller(a, 0);
        if (biggestNegative == -1) {
            for (int i = 0; i < a.length; i++) {
                a[i] *= a[i];
            }
            return a;
        } else {

            int[] r = new int[a.length];
            int np = biggestNegative;
            int pp = biggestNegative + 1;
            int rp = 0;
            while (np >= 0 && pp < a.length) {
                int nps = a[np] * a[np];
                int pps = a[pp] * a[pp];
                if (pps < nps) {
                    r[rp++] = pps;
                    pp++;
                } else {
                    r[rp++] = nps;
                    np--;
                }
            }
            while (np >= 0) {
                int nps = a[np] * a[np];
                r[rp++] = nps;
                np--;
            }
            while (pp < a.length) {
                int pps = a[pp] * a[pp];
                r[rp++] = pps;
                pp++;
            }
            return r;
        }

    }

    private int binarySearchFirstSmaller(int[] a, int target) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (a[mid] >= target) {
                u = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return u;
    }

    public static void main(String[] args) {

    }
}
