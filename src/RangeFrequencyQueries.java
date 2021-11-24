import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeFrequencyQueries {
    class RangeFreqQuery {
        private Map<Integer, List<Integer>> m = new HashMap<>();
        public  RangeFreqQuery(int[] a) {
            for(int i=0; i<a.length; ++i){
                m.computeIfAbsent(a[i], k-> new ArrayList<>()).add(i);
            }
        }

        int query(int left, int right, int value) {
            if(!m.containsKey(value)){
                return 0;
            }
            List<Integer> v = m.get(value);
            int sp = lower(v, left);
            if(sp==-1 || sp==v.size()){
                return 0;
            }
            int ep = upper(v, right);
            if(ep==-1 || ep==v.size()){
                return 0;
            }
            int res = ep-sp+1;
            return res;
        }

        // first >=
        private int lower(List<Integer> a, int v){
            int l = 0;
            int u = a.size()-1;
            while(l<=u){
                int mid = l+(u-l)/2;
                if(a.get(mid)>=v){
                    u = mid-1;
                }else{
                    l = mid+1;
                }
            }
            return l;
        }

        // last >=
        private int upper(List<Integer> a, int v){
            int l = 0;
            int u =a.size()-1;
            while(l<=u){
                int mid = l+(u-l)/2;
                if(a.get(mid)>v){
                    u = mid-1;
                }else{
                    l = mid+1;
                }
            }
            return u;
        }
    }
}
