import java.util.HashMap;
import java.util.Map;

public class FindPairsWithACertainSum {
    class FindSumPairs {

        private int[] a;
        private int[] b;
        private Map<Integer,Integer> m = new HashMap<>();
        public FindSumPairs(int[] nums1, int[] nums2) {
            a= nums1;
            b = nums2;
            for(int v: nums2){
                update(m, v, 1);
            }

        }

        public void add(int index, int val) {

            update(m, b[index], -1);
            update(m, b[index]+val, 1);
            b[index] += val;

        }

        public int count(int tot) {
            int res = 0;
            for(int i=0; i<a.length; i++){
                res += m.getOrDefault(tot-a[i], 0);
            }
            return res;
        }

        private void update(Map<Integer,Integer> m, int k, int d){
            int nv = m.getOrDefault(k, 0)+d;
            if(nv<=0){
                m.remove(k);
            }else{
                m.put(k, nv);
            }
        }
    }

/**
 * Your FindSumPairs object will be instantiated and called as such:
 * FindSumPairs obj = new FindSumPairs(nums1, nums2);
 * obj.add(index,val);
 * int param_2 = obj.count(tot);
 */
}
