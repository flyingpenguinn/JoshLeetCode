import java.util.*;

public class HighAccessEmployees {
    public List<String> findHighAccessEmployees(List<List<String>> a) {
        int n = a.size();
        Map<String, List<Integer>> m = new HashMap<>();
        for (int i = 0; i < n; ++i) {
            String name = a.get(i).get(0);
            String time = a.get(i).get(1);
            String hour = time.substring(0, 2);
            String minute = time.substring(2, 4);
            int ht = Integer.valueOf(hour);
            int mt = Integer.valueOf(minute);
            int tt = ht * 60 + mt;
            m.computeIfAbsent(name, p -> new ArrayList()).add(tt);
        }
        //   System.out.println(m);
        List<String> res = new ArrayList<>();
        for (String k : m.keySet()) {
            List<Integer> ts = m.get(k);
            Collections.sort(ts);
            int tn = ts.size();
            int j = 0;
            for (int i = 0; i < tn; ++i) {
                while (j < tn && ts.get(i) - ts.get(j) >= 60) {
                    ++j;
                }
                // j...i is <60
                int count = i - j + 1;
                if (count >= 3) {
                    res.add(k);
                    break;
                }
            }
        }
        return res;
    }
}
