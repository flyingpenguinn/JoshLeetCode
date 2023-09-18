import java.util.List;

public class SumValueAtIndiceWithKBitsSet {
    public int sumIndicesWithKSetBits(List<Integer> a, int k) {
        int res = 0;
        int n = a.size();
        for (int i = 0; i < n; ++i) {
            String binary = Integer.toBinaryString(i);
            int cur = 0;
            for (int j = 0; j < binary.length(); ++j) {
                if (binary.charAt(j) == '1') {
                    ++cur;
                }
            }
            //    System.out.println(i+" "+binary);
            if (cur == k) {
                res += a.get(i);
            }
        }
        return res;
    }
}
