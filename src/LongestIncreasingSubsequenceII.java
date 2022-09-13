import base.ArrayUtils;

public class LongestIncreasingSubsequenceII {
    // copy pasted a segment tree implementation!

    public static void main(String[] args) {

        System.out.println(new LongestIncreasingSubsequenceII().lengthOfLIS(ArrayUtils.read1d("1,1,5"), 3));
    }


    public int lengthOfLIS(int[] a, int k) {
        int n = a.length;
        int max = 0;
        for (int i = 0; i < n; ++i) {
            max = Math.max(max, a[i]);
        }
        int m = max + 1;
        int[] dp = new int[m];
        int[] st = constructST(dp, m);
        int res = 0;
        for (int i = 0; i < n; ++i) {
            int t = a[i];
            int down = Math.max(0, t - k);
            int up = Math.max(0, t-1);
            int pmax = getMax(st, m, down, up);
            int cur = pmax + 1;
            res = Math.max(res, cur);
            dp[t] = cur;
            updateValue(dp, st, 0, m - 1, t, cur, 0);

        }
        return res;
    }

    // A utility function to get the
    // middle index of given range.
    static int getMid(int s, int e) {

        return s + (e - s) / 2;
    }

    /*
    * A recursive function to get the sum
    of values in given range of the array.
    * The following are parameters
      for this function.
    *
    * st -> Pointer to segment tree
    * node -> Index of current node in
    *         the segment tree.
    * ss & se -> Starting and ending indexes
    *         of the segment represented
    *         by current node, i.e., st[node]
    * l & r -> Starting and ending indexes
    *         of range query
    */
    static int MaxUtil(int[] st, int ss,
                       int se, int l,
                       int r, int node) {

        // If segment of this node is completely
        // part of given range, then return
        // the max of segment
        if (l <= ss && r >= se)
            return st[node];

        // If segment of this node does not
        // belong to given range
        if (se < l || ss > r)
            return -1;

        // If segment of this node is partially
        // the part of given range
        int mid = getMid(ss, se);

        return Math.max(
                MaxUtil(st, ss, mid, l, r,
                        2 * node + 1),
                MaxUtil(st, mid + 1, se, l, r,
                        2 * node + 2));
    }

    /*
    * A recursive function to update the
    nodes which have the given index in their
    * range. The following are parameters
    st, ss and se are same as defined above
    * index -> index of the element to be updated.
    */
    static void updateValue(int arr[], int[]
            st, int ss,
                            int se, int index,
                            int value,
                            int node) {
        if (index < ss || index > se) {
            System.out.println("Invalid Input");
            return;
        }

        if (ss == se) {

            // update value in array and in
            // segment tree
            arr[index] = value;
            st[node] = value;
        } else {
            int mid = getMid(ss, se);

            if (index >= ss && index <= mid)
                updateValue(arr, st, ss, mid,
                        index, value,
                        2 * node + 1);
            else
                updateValue(arr, st, mid + 1, se, index,
                        value, 2 * node + 2);

            st[node] = Math.max(st[2 * node + 1],
                    st[2 * node + 2]);
        }
        return;
    }

    // Return max of elements in range from
    // index l (query start) to r (query end).
    static int getMax(int[] st, int n, int l, int r) {

        // Check for erroneous input values
        if (l < 0 || r > n - 1 || l > r) {
            System.out.printf("Invalid Input\n");
            return -1;
        }

        return MaxUtil(st, 0, n - 1, l, r, 0);
    }

    // A recursive function that constructs Segment
    // Tree for array[ss..se]. si is index of
    // current node in segment tree st
    static int constructSTUtil(int arr[],
                               int ss, int se,
                               int[] st, int si) {

        // If there is one element in array, store
        // it in current node of segment tree and return
        if (ss == se) {
            st[si] = arr[ss];
            return arr[ss];
        }

        // If there are more than one elements, then
        // recur for left and right subtrees and
        // store the max of values in this node
        int mid = getMid(ss, se);

        st[si] = Math.max(
                constructSTUtil(arr, ss, mid,
                        st, si * 2 + 1),
                constructSTUtil(arr, mid + 1,
                        se, st,
                        si * 2 + 2));

        return st[si];
    }

    /*
    * Function to construct segment tree from
    given array. This function allocates
    * memory for segment tree.
    */
    static int[] constructST(int arr[], int n) {

        // Height of segment tree
        int x = (int) Math.ceil(Math.log(n) / Math.log(2));

        // Maximum size of segment tree
        int max_size = 2 * (int) Math.pow(2, x) - 1;

        // Allocate memory
        int[] st = new int[max_size];

        // Fill the allocated memory st
        constructSTUtil(arr, 0, n - 1, st, 0);

        // Return the constructed segment tree
        return st;
    }


}
