import base.ArrayUtils;

import java.util.ArrayDeque;
import java.util.Deque;

public class CountSubarrayWithCostLessthanEqualtoK {
    // using max and min dq to maintain subarray min/max. faster than treemap
    public long countSubarrays(int[] a, long k) {
        int n = a.length;
        int j = 0;
        Deque<Integer> maxdq = new ArrayDeque<>();
        Deque<Integer> mindq = new ArrayDeque<>();

        long res = 0;
        for (int i = 0; i < n; ++i) {
            while (j < n) {
                long jv = a[j];
                long len = j - i + 1;
                long maxv = maxdq.isEmpty() ? jv : Math.max(jv, a[maxdq.peekFirst()]);
                long minv = mindq.isEmpty() ? jv : Math.min(jv, a[mindq.peekFirst()]);
                long cc = (maxv - minv) * len;
                if (cc > k) {
                    break;
                }
                while (!maxdq.isEmpty() && a[maxdq.peekLast()] <= jv) {
                    maxdq.pollLast();
                }
                maxdq.offerLast(j);
                while (!mindq.isEmpty() && a[mindq.peekLast()] >= jv) {
                    mindq.pollLast();
                }
                mindq.offerLast(j);
                ++j;
            }
            // i to j-1 good
            long len = j - i;
            res += len;
            if(!maxdq.isEmpty() && maxdq.peekFirst() == i){
                maxdq.pollFirst();
            }
            if(!mindq.isEmpty() && mindq.peekFirst() == i){
                mindq.pollFirst();
            }
        }
        return res;
    }

    static void main() {
        System.out.println(new CountSubarrayWithCostLessthanEqualtoK().countSubarrays(ArrayUtils.read1d("615126499,970816165,569188519,364454806,736956188,341445398,359644622,680570340,252771394,436334832,728098108,65298849,796536115,773954637,926970168,432894058,631531045,685008279,379208269,790192794,48343414,645214511,111042008,324453297,941627316,937413040,537267316,408364808,755807635,842284295,665283924,107872654,194612755,618257345,727448422,273565005,878484562,792116045,137679885,224919618,264941696,593613895,712809533,776198506,467389322,199823213,663797609,791194208,352362567"), 22069094621L));
    }
}
