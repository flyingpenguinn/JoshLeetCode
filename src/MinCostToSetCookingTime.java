import java.util.ArrayList;
import java.util.List;

public class MinCostToSetCookingTime {
    private int res = (int) (1e9);

    public int minCostSetTime(int startAt, int moveCost, int pushCost, int targetSeconds) {
        List<Integer> nums = new ArrayList<>();
        dfs(startAt, moveCost, pushCost, targetSeconds, 0, nums);
        return res;
    }

    private void dfs(int i, int m, int p, int t, int cur, List<Integer> nums) {

        List<Integer> copied = new ArrayList<>();
        for (int k = 1; k <= 4 - nums.size(); ++k) {
            copied.add(0);
        }
        for (int ni : nums) {
            copied.add(ni);
        }
        int mins = copied.get(0) * 10 + copied.get(1);
        int seconds = copied.get(2) * 10 + copied.get(3);
        int curtime = mins * 60 + seconds;
        if (curtime == t) {
            res = Math.min(res, cur);
            return;
        } else if (curtime > t) {
            return;
        }
        if (nums.size() == 4) {
            return;
        }

        for (int j = 0; j <= 9; ++j) {
            int mc = i == j ? 0 : m;
            nums.add(j);
            dfs(j, m, p, t, cur + mc + p, nums);
            nums.remove(nums.size() - 1);
        }
    }
}
