import java.util.Collections;
import java.util.PriorityQueue;

public class MinDiffInSumAfterRemoval {
    // think about partition point and enumerate it
    // however we dont need to come up with the mins and maxs at the same time. we can calculate them separately
    public long minimumDifference(int[] a) {
        int n = a.length;
        long[] mins = new long[n];
        long[] maxs = new long[n];
        int k = n/3;
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Collections.reverseOrder());
        long psum = 0;
        for(int i=0; i<n; ++i){
            psum += a[i];
            pq1.offer(a[i]);
            if(pq1.size()>k){
                psum -= pq1.poll();
            }
            //     System.out.println("mins["+i+"] "+psum);
            mins[i] = psum;
        }

        PriorityQueue<Integer> pq2 = new PriorityQueue<>();
        psum = 0;
        for(int i=n-1; i>=0; --i){
            psum += a[i];
            pq2.offer(a[i]);
            if(pq2.size()>k){
                psum -= pq2.poll();
            }
            maxs[i] = psum;
            //   System.out.println("maxs["+i+"] "+psum);
        }
        long[] maxAfter = new long[n];
        maxAfter[n-1] = maxs[n-1];
        for(int i=n-2; i>=0; --i){
            maxAfter[i] = Math.max(maxs[i], maxAfter[i+1]);
        }
        long res = (long)(1e17);
        for(int i=k-1; i<n-k; ++i){
            long cur = mins[i]-maxAfter[i+1];
            res = Math.min(res, cur);
        }
        return res;
    }


}
