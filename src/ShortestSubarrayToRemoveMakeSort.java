import java.util.TreeMap;

public class ShortestSubarrayToRemoveMakeSort {
    // for each i, what if we delete from i+1 to some end point?
    // if it's part of the end slope, nothing is needed
    // otherwise we find the ceiling of it in the "end slope"
    // we can use two pointers in finding ceiling
    public int findLengthOfShortestSubarray(int[] a) {
        int n = a.length;

        int i = n - 1;
        for (; i >= 0; i--) {
            if (i + 1 < n && a[i + 1] < a[i]) {
                break;
            }
        }
        int end = i;
        // from end+1...n-1 is sorted
        // from 0...end it must also be sorted otherwise we break

        // delete 0... end in the worst case because i...n-1 are sorted already
        int min = end + 1;
        // we then check if 0...end can give us better results
        int nIndex = end + 1;
        for (i = 0; i <= end; i++) {
            if (i > 0 && a[i - 1] > a[i]) {
                break;
            }
            while (nIndex < n && a[nIndex] < a[i]) {
                nIndex++;
            }
            // from i+1 to nindex-1 gone
            int cur = nIndex - i - 1;
            min = Math.min(min, cur);
        }
        return min;
    }
}
