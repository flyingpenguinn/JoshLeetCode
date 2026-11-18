public class MinChangesToMakeBinaryStringBeautiful {
    // 2026-11-17 daily redo, ~2 min, 1 WA (wrote ++i instead of i += 2).
    // Observation: beautiful <=> every aligned pair (2i, 2i+1) is equal, since any even uniform block
    // splits into uniform pairs. So the pairs are independent: one change per mismatched pair.
    public int minChanges(String s) {
        int n = s.length();
        int res = 0;
        for(int i=0; i+1<n; i += 2){
            if(s.charAt(i) != s.charAt(i+1)){
                ++res;
            }
        }
        return res;
    }
}
