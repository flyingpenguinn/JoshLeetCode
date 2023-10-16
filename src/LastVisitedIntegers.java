import java.util.ArrayList;
import java.util.List;

public class LastVisitedIntegers {
    public List<Integer> lastVisitedIntegers(List<String> a) {
        int n = a.size();
        List<Integer> nums = new ArrayList<>();
        int nj = 0;
        int pk = 0;
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            if (a.get(i).equals("prev")) {
                int index = nums.size() - 1 - pk;
                if (index < 0) {
                    res.add(-1);
                } else {
                    res.add(nums.get(index));
                }
                ++pk;
            } else {
                nums.add(Integer.valueOf(a.get(i)));
                pk = 0;
            }
        }
        return res;
    }
}
