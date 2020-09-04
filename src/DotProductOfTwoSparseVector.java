import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            if (m.size() > m2.size()) {
                return vec.dotProduct(this);
            }
            for (int k1 : m.keySet()) {
                Integer v2 = m2.get(k1);
                if (v2 != null) {
                    res += m.get(k1) * v2;
                }
            }
            return res;
        }
    }

    class SparseVectorSorted {
        private List<int[]> data;

        public SparseVectorSorted(int[] a) {
            data = new ArrayList<>();
            for (int i = 0; i < a.length; i++) {
                if (a[i] != 0) {
                    data.add(new int[]{i, a[i]});
                }
            }
        }

        // Return the dotProduct of two sparse vectors
        public int dotProduct(SparseVectorSorted vec) {
            int i = 0;
            int j = 0;
            int res = 0;
            while (i < data.size() && j < vec.data.size()) {
                int i1 = data.get(i)[0];
                int i2 = vec.data.get(j)[0];
                if (i1 == i2) {
                    res += data.get(i)[1] * vec.data.get(j)[1];
                    i++;
                    j++;
                } else if (i1 < i2) {
                    i++;
                } else {
                    j++;
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
