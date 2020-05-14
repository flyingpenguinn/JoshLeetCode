public class MissingElementInSortedArray {
    public int missingElement(int[] a, int k) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;

            int th = getth(mid, a);
            // System.out.println(mid+" "+th);
            if (th < k) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }

        int uth = getth(u, a);
        //   System.out.println(u+" "+uth);

        return a[u] + (k - uth);

    }

    // pos of the missing number immediately before a[i]. in the end u< l>= so missing one is between u and l
    int getth(int i, int[] a) {
        return a[i] - a[0] - i;
    }
}
