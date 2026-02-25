import base.ArrayUtils;

import java.util.*;

public class MaxRequestsWithoutViolatingLimit {
    public int maxRequests(int[][] rqs, int k, int window) {
        Map<Integer, List<Integer>> m = new HashMap<>();
        for(int[] rq: rqs){
            int user = rq[0];
            int time = rq[1];
            m.computeIfAbsent(user, p-> new ArrayList<>()).add(time);
        }
        int rn = rqs.length;
        int res = 0;
        for(int key: m.keySet()){
            List<Integer> list = m.get(key);
            Collections.sort(list);
            Deque<Integer> dq = new ArrayDeque<>();
            int n = list.size();
            for(int i=0; i<n; ++i){
                Integer v = list.get(i);
                while(!dq.isEmpty() && dq.peekFirst()< v -window){
                    dq.pollFirst();
                }
                dq.offerLast(v);
                if(dq.size()>k){
                    dq.pollLast();
                    ++res;
                }
            }
        }
        return rn-res;
    }

    static void main() {
        System.out.println(new MaxRequestsWithoutViolatingLimit().maxRequests(ArrayUtils.read("[[4,6],[8,4],[9,4],[2,4],[2,8],[1,5],[2,7],[9,9],[3,2]]"), 1, 3));
        System.out.println(new MaxRequestsWithoutViolatingLimit().maxRequests(ArrayUtils.read("[[1,1],[2,1],[1,7],[2,8]]"), 1, 4));
        System.out.println(new MaxRequestsWithoutViolatingLimit().maxRequests(ArrayUtils.read("[[1,2],[1,5],[1,2],[1,6]]"), 2, 5));
        System.out.println(new MaxRequestsWithoutViolatingLimit().maxRequests(ArrayUtils.read("[[1,1],[2,5],[1,2],[3,9]]"), 1, 1));
    }
}
