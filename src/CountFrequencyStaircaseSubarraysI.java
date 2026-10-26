import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

 public class CountFrequencyStaircaseSubarraysI {
    public int countStaircase(int[] a) {
        int n = a.length;
        int res = 0;
        for (int i = 0; i < n; ++i) {
            Map<Integer, Integer> seen = new HashMap<>();
            List<Integer> seq = new ArrayList<>();
            int goods = 0;
            int cindex = -1;
            for (int j = i; j < n; ++j) {
                int v = a[j];
                int index = -1;
                if (!seen.containsKey(v)) {
                    ++cindex;
                    seen.put(v, cindex);
                    index = cindex;
                    seq.add(1);

                } else {
                    index = seen.get(v);
                    int cfreq = seq.get(index);
                    seq.set(index, cfreq + 1);
                }
                if (seq.get(index) == index + 1) {
                    ++goods;
                } else if (seq.get(index) > index + 1) {
                    break;
                }
                if (goods == seq.size()){
                    ++res;
                }
            }
        }
        return res;
    }
}
