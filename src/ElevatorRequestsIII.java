public class ElevatorRequestsIII {
    private long Max = (long)(2e18);
    public long elevatorRequests(int n, int start, int[][] requests) {
        // index, st, time
        PriorityQueue<long[]> pq = new PriorityQueue<>((x,y)-> Long.compare(x[2], y[2]));
        int rn = requests.length;
        long[][] dist = new long[rn+1][(1<<rn)];
        for(int i=0; i<=rn; ++i){
            Arrays.fill(dist[i], Max);
        }
        dist[rn][0] = 0;
    
        pq.offer(new long[]{rn, 0, 0});
        while(!pq.isEmpty()){
            long[] top = pq.poll();
         //   System.out.println(Arrays.toString(top));
            int index = (int)(top[0]);
            int st = (int)(top[1]);
            long ct = top[2];
            if(st+1 == (1<<rn)){
                return ct;
            }
            long cpos = index==rn? start: requests[index][1];
            for(int j=0; j<rn; ++j){
               if(((st>>j)&1)==0){
                  long npos = requests[j][1];
                  long narr = requests[j][0];
                  long cdist = Math.abs(cpos - npos);
                  int nst = st | (1<<j);
                  long nt = Math.max(narr, ct+cdist);
                  if(dist[j][nst]>nt){
                    dist[j][nst] = nt;
                    pq.offer(new long[]{j, nst, nt});
                  }
               }
            }
        }
        return -1;
    }
}
