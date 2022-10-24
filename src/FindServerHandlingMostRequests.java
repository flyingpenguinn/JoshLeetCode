import base.ArrayUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;

public class FindServerHandlingMostRequests {
    // as we move ahead in time, we enable more servers
    // 2nd trick is to use a treeset to get a good server in logn: either ceiling or first.
    // Note this problem is not asking to pick the server that is always right to the last one. If that's the case, we most likely need to use the "+n" trick
    public List<Integer> busiestServers(int k, int[] a, int[] w) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> Integer.compare(x[0], y[0]));
        // time, pos
        TreeSet<Integer> avail = new TreeSet<> ();
        for(int i=0; i<k; i++){
            avail.add(i);
        }
        int[] handled = new int[k];
        for(int i=0; i<n; i++){
            int time = a[i];
            while(!pq.isEmpty() && pq.peek()[0] <= time){
                avail.add(pq.poll()[1]);
            }
            int server = getserver(avail, i%k);
            if(server==-1){
                // dropped
                continue;
            }
            handled[server%n]++;
            avail.remove(server);
            pq.offer(new int[]{time+w[i], server+n});
        }
        int max = -1;
        List<Integer> res = new ArrayList<>();
        for(int i=0; i<k;i++){
            if(handled[i]==max){
                res.add(i);
            }else if(handled[i]>max){
                max = handled[i];
                res.clear();
                res.add(i);
            }
        }
        return res;
    }

    private int getserver(TreeSet<Integer> ts, int i){
        return ts.isEmpty()? -1: ts.first();
    }

    public static void main(String[] args) {
        System.out.println(new FindServerHandlingMostRequests().busiestServers(3, ArrayUtils.read1d("1,2,3,4"), ArrayUtils.read1d("1,5,3,3")));
    }
}
