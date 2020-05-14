public class FindFirstLastInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        int first = find(nums, target, true);
        int last = find(nums, target, false);
        return new int[]{first, last};
    }

    private int find(int[] a, int target, boolean isFirst) {
        int l = 0;
        int u = a.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (isFirst) {
                if (a[mid] >= target) {
                    u = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (a[mid] <= target) {
                    l = mid + 1;
                } else {
                    u = mid - 1;
                }
            }
        }
        // l and u may not be target, we need to verify it really is
        if (isFirst && inRange(a, l) && a[l] == target) {
            return l;
        }
        if (!isFirst && inRange(a, u) &&  a[u] == target) {
            return u;
        }
        return -1;
    }

    private boolean inRange(int[] a, int l) {
        return l < a.length && l >= 0;
    }
}
