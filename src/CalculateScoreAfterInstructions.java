import java.util.HashSet;
import java.util.Set;

public class CalculateScoreAfterInstructions {
    public long calculateScore(String[] instructions, int[] values) {
        int n = instructions.length;
        int i = 0;
        long res = 0;
        Set<Integer> seen = new HashSet<>();
        while(i>=0 && i<n && !seen.contains(i)){
            //   System.out.println(i);
            seen.add(i);
            String cur = instructions[i];
            if("add".equals(cur)){
                long score = values[i];
                res += score;
                ++i;
            }else{
                i += values[i];
            }
        }
        return res;
    }
}
