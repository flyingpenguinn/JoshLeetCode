import java.util.HashMap;
import java.util.Map;

public class DotProductOfTwoSparseVector {
    // asked during my facebook interview...
    class SparseVector {
        private Map<Integer, Integer> m = new HashMap<>();

        public SparseVector(int[] a) {
            for (int i = 0; i < a.length; i++) {
                m.put(i, a[i]);
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVector vec) {
            int res = 0;
            Map<Integer, Integer> m2 = vec.m;
            for (int k1 : m.keySet()) {
                Integer v2 = m2.get(k1);
                if (v2 != null) {
                    res += m.get(k1) * v2;
                }
            }
            return res;
        }
    }

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
}
