import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IncremoveableSubarraysIandII {
    // count how many subarrays are valid. each valid corresponds to one not valid case
    public long incremovableSubarrayCount(int[] a) {
        int n = a.length;
        List<Integer> left = new ArrayList<>();
        left.add(a[0]);
        int i = 1;
        while (i < n && a[i] > a[i - 1]) {
            left.add(a[i]);
            ++i;
        }
        List<Integer> right = new ArrayList<>();
        int j = n - 1;
        while (j >= i && (j == n - 1 || a[j + 1] > a[j])) {
            right.add(a[j]);
            --j;
        }
        Collections.reverse(right);
        //  System.out.println("left="+left);
        //  System.out.println("right="+right);
        long lsize = left.size();
        long rsize = right.size();
        if (rsize == 0) {
            return lsize * (lsize + 1) / 2;
        }
        long onlyleft = lsize;
        long onlyright = rsize;
        long res = onlyleft + onlyright;
        i = 0;
        j = 0;
        while (i < lsize && j < rsize) {
            while (j < rsize && right.get(j) <= left.get(i)) {
                ++j;
            }
            res += rsize - j;
            ++i;
        }
        ++res; // the empty array
        return res;
    }
}
