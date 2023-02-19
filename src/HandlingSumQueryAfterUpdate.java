import java.util.ArrayList;
import java.util.List;

public class HandlingSumQueryAfterUpdate {

    private LazySegmentTree seg = new LazySegmentTree();

    public long[] handleQuery(int[] nums1, int[] nums2, int[][] queries) {
        int n = nums1.length;
        seg.constructST(nums1, n);
        List<Long> res = new ArrayList<>();
        long cursum = 0;
        for (int ni2 : nums2) {
            cursum += ni2;
        }
        for (int[] q : queries) {
            if (q[0] == 1) {
                int l = q[1];
                int r = q[2];
                seg.updateRange(n, l, r);
            } else if (q[0] == 2) {
                long p = q[1];
                long nums1sum = seg.getSum(n, 0, n - 1);
                cursum += nums1sum * p;
            } else {
                res.add(cursum);
            }
        }
        long[] rres = new long[res.size()];
        for (int i = 0; i < res.size(); ++i) {
            rres[i] = res.get(i);
        }
        return rres;
    }

    // Java program to demonstrate lazy propagation in segment tree
    class LazySegmentTree {
        final int MAX = 400000;     // Max tree size
        long tree[] = new long[MAX]; // To store segment tree
        long lazy[] = new long[MAX]; // To store pending updates

        /* si -> index of current node in segment tree
            ss and se -> Starting and ending indexes of elements for
                        which current nodes stores sum.
            us and eu -> starting and ending indexes of update query
            ue -> ending index of update query
            diff -> which we need to add in the range us to ue */
        void updateRangeUtil(int si, int ss, int se, int us,
                             int ue) {
            // If lazy value is non-zero for current node of segment
            // tree, then there are some pending updates. So we need
            // to make sure that the pending updates are done before
            // making new updates. Because this value may be used by
            // parent after recursive calls (See last line of this
            // function)
            if (lazy[si] != 0) {
                // Make pending updates using value stored in lazy
                // nodes
                tree[si] = (se - ss + 1) - tree[si];

                // checking if it is not leaf node because if
                // it is leaf node then we cannot go further
                if (ss != se) {
                    // We can postpone updating children we don't
                    // need their new values now.
                    // Since we are not yet updating children of si,
                    // we need to set lazy flags for the children
                    lazy[si * 2 + 1] ^= 1;
                    lazy[si * 2 + 2] ^= 1;
                }

                // Set the lazy value for current node as 0 as it
                // has been updated
                lazy[si] = 0;
            }

            // out of range
            if (ss > se || ss > ue || se < us)
                return;

            // Current segment is fully in range
            if (ss >= us && se <= ue) {
                // Add the difference to current node
                tree[si] = (se - ss + 1) - tree[si];

                // same logic for checking leaf node or not
                if (ss != se) {
                    // This is where we store values in lazy nodes,
                    // rather than updating the segment tree itself
                    // Since we don't need these updated values now
                    // we postpone updates by storing values in lazy[]
                    lazy[si * 2 + 1] ^= 1;
                    lazy[si * 2 + 2] ^= 1;
                }
                return;
            }

            // If not completely in rang, but overlaps, recur for
            // children,
            int mid = (ss + se) / 2;
            updateRangeUtil(si * 2 + 1, ss, mid, us, ue);
            updateRangeUtil(si * 2 + 2, mid + 1, se, us, ue);

            // And use the result of children calls to update this
            // node
            tree[si] = tree[si * 2 + 1] + tree[si * 2 + 2];
        }

        // Function to update a range of values in segment
        // tree
    /* us and eu -> starting and ending indexes of update query
        ue -> ending index of update query
        diff -> which we need to add in the range us to ue */
        void updateRange(int n, int us, int ue) {
            updateRangeUtil(0, 0, n - 1, us, ue);
        }

        /* A recursive function to get the sum of values in given
            range of the array. The following are parameters for
            this function.
            si --> Index of current node in the segment tree.
                Initially 0 is passed as root is always at'
                index 0
            ss & se --> Starting and ending indexes of the
                        segment represented by current node,
                        i.e., tree[si]
            qs & qe --> Starting and ending indexes of query
                        range */
        long getSumUtil(int ss, int se, int qs, int qe, int si) {
            // If lazy flag is set for current node of segment tree,
            // then there are some pending updates. So we need to
            // make sure that the pending updates are done before
            // processing the sub sum query
            if (lazy[si] != 0) {
                // Make pending updates to this node. Note that this
                // node represents sum of elements in arr[ss..se] and
                // all these elements must be increased by lazy[si]
                tree[si] += (se - ss + 1) - tree[si];

                // checking if it is not leaf node because if
                // it is leaf node then we cannot go further
                if (ss != se) {
                    // Since we are not yet updating children os si,
                    // we need to set lazy values for the children
                    lazy[si * 2 + 1] ^= 1;
                    lazy[si * 2 + 2] ^= 1;
                }

                // unset the lazy value for current node as it has
                // been updated
                lazy[si] = 0;
            }

            // Out of range
            if (ss > se || ss > qe || se < qs)
                return 0;

            // At this point sure, pending lazy updates are done
            // for current node. So we can return value (same as
            // was for query in our previous post)

            // If this segment lies in range
            if (ss >= qs && se <= qe)
                return tree[si];

            // If a part of this segment overlaps with the given
            // range
            int mid = (ss + se) / 2;
            return getSumUtil(ss, mid, qs, qe, 2 * si + 1) +
                    getSumUtil(mid + 1, se, qs, qe, 2 * si + 2);
        }

        // Return sum of elements in range from index qs (query
        // start) to qe (query end). It mainly uses getSumUtil()
        long getSum(int n, int qs, int qe) {
            // Check for erroneous input values
            if (qs < 0 || qe > n - 1 || qs > qe) {
                System.out.println("Invalid Input");
                return -1;
            }

            return getSumUtil(0, n - 1, qs, qe, 0);
        }

        /* A recursive function that constructs Segment Tree for
        array[ss..se]. si is index of current node in segment
        tree st. */
        void constructSTUtil(int arr[], int ss, int se, int si) {
            // out of range as ss can never be greater than se
            if (ss > se)
                return;

        /* If there is one element in array, store it in
        current node of segment tree and return */
            if (ss == se) {
                tree[si] = arr[ss];
                return;
            }

        /* If there are more than one elements, then recur
        for left and right subtrees and store the sum
        of values in this node */
            int mid = (ss + se) / 2;
            constructSTUtil(arr, ss, mid, si * 2 + 1);
            constructSTUtil(arr, mid + 1, se, si * 2 + 2);

            tree[si] = tree[si * 2 + 1] + tree[si * 2 + 2];
        }

        /* Function to construct segment tree from given array.
        This function allocates memory for segment tree and
        calls constructSTUtil() to fill the allocated memory */
        void constructST(int arr[], int n) {
            // Fill the allocated memory st
            constructSTUtil(arr, 0, n - 1, 0);
        }

    }
}
