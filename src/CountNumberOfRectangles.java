import java.util.*;

public class CountNumberOfRectangles {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Map<Integer,List<Integer>> m = new HashMap<>();
        for(int[] rect: rectangles){
            int len = rect[0];
            int ht = rect[1];
            m.computeIfAbsent(ht, k-> new ArrayList<>()).add(len);
        }
        for(int mk: m.keySet()){
            List<Integer> list = m.get(mk);
            Collections.sort(list);
            m.put(mk, list);
        }
        int[] res = new int[points.length];
        int ri = 0;
        for(int[] p: points){
            int cur = 0;
            int pl = p[0];
            int ph = p[1];
            for(int mk: m.keySet()){
                if(mk<ph){
                    continue;
                }
                int count = binary(m.get(mk), pl);
                cur += count;
            }
            res[ri++] = cur;
        }
        return res;
    }

    private int binary(List<Integer> list, int t){
        int l = 0;
        int u = list.size()-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(list.get(mid)>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        // from l
        return list.size()-l;
    }
}
