import java.util.*;

public class SecondMinTimeToReachDestination {
    private class Node{
        int x;
        int t;

        public Node(int x, int t) {
            this.x = x;
            this.t = t;
        }
    }
    public int secondMinimum(int n, int[][] edges, int time, int change) {
        Set<Integer>[] g = new HashSet[n+1];
        for(int i=0; i<g.length; ++i){
            g[i] = new HashSet<>();
        }
        for(int[] e: edges){
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }
        PriorityQueue<Node> pq = new PriorityQueue<>((x,y)-> Integer.compare(x.t, y.t));
        TreeSet<Integer> []dist = new TreeSet[n+1];
        for(int i=0; i<dist.length; ++i){
            dist[i] = new TreeSet<>();
        }
        pq.offer(new Node(1, 0));
        dist[1].add(0);
        TreeSet<Integer> res = new TreeSet<>();
        while(!pq.isEmpty()){
            Node top = pq.poll();
            int cx = top.x;
            int ct = top.t;
            if(cx==n){
                res.add(ct);
                if(res.size()==2){
                    break;
                }
            }
            int flips = ct/change;
            if(flips%2==1){
                ct = (flips+1)*change;
            }
            int nct = ct+time;
            for(int ne: g[cx]){
                //   cout<<"checking "<<cx<<" -> "<<ne<<endl;
                TreeSet<Integer> neq= dist[ne];
                if(neq.size()<2){
                    neq.add(nct);
                    //     cout<<"pushing "<<ne<<" "<<nct<<endl;
                    pq.offer(new Node(ne, nct));
                }else{
                    // ==2
                    if(nct < neq.last()){
                        neq.add(nct);
                        neq.remove(neq.last());
                        pq.offer(new Node(ne, nct));
                    }else{
                        // else not good enough we leave
                    }
                }
            }
        }
        return res.last();
    }
}
