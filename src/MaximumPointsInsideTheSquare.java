import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumPointsInsideTheSquare {
    public int maxPointsInsideSquare(int[][] ps, String s) {
        int n = ps.length;
        Map<Integer,List<Integer>> m = new HashMap<>();
        for(int i=0; i<n; ++i){
            int e = Math.max(Math.abs(ps[i][0]), Math.abs(ps[i][1]));
            int cind = s.charAt(i)-'a';
            m.computeIfAbsent(cind, k-> new ArrayList<>()).add(e);
        }
        int res = (int)2e9;
        for(int i=0; i<26; ++i){
            if(m.containsKey(i) && m.get(i).size()>=2){
                List<Integer> list = m.get(i); Collections.sort(list);
                res = Math.min(res, list.get(1)-1);
            }
        }
        int count = 0;
        for(int i=0; i<n; ++i){
            if(m.containsKey(i)){
                List<Integer> list = m.get(i); Collections.sort(list);
                if(list.get(0)<=res){ ++count; }
            }
        }
        return count;
    }
}
