import java.util.PriorityQueue;

public class RemoveStonesToMinimizeTotal {
    public int minStoneSum(int[] a, int k) {
        int n = a.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((x, y)-> Integer.compare(y[1], x[1]));
        int sum = 0;
        for(int i=0; i<n; i++){
            sum += a[i];
        }
        for(int i=0; i<n;i++){
            pq.offer( new int[]{a[i],a[i]/2});
        }
        while(k>0){
            int[] top = pq.poll();
            sum -= top[1];
            int nnum = top[0]-top[1];
            pq.offer(new int[]{nnum, nnum/2});
            k--;
        }
        return sum;
    }
}
