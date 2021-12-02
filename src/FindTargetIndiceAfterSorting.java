import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindTargetIndiceAfterSorting {
    public List<Integer> targetIndices(int[] a, int target) {
        int cnt = 0;
        int rank = 0; // `cnt` is the frequency of `target`, `rank` is the sum of frequencies of all numbers < target
        for (int n : a) {
            cnt += (n == target)?1:0;
            rank += n < target?1:0;
        }
        List<Integer> ans = new ArrayList<>();
        while (cnt-->0) {
            ans.add(rank++);
        }
        return ans;
    }
}
