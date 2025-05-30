public class MinNumberOfSwapsToMakeStringAlternate {
    // sliding window:
    // type 1 means we do s+=s;
    // type 2 means we keep records of the mismatches for starting = 0 or 1. but we then use sliding window to eject the contribution head when it goes out of range
    public int minFlips(String s) {
        int n = s.length();
        s += s;
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < 2*n; i++) {
            sb1.append(i % 2 == 1 ? '0' : '1');
            sb2.append(i % 2 == 1 ? '1' : '0');
        }
        String s1 = sb1.toString();
        String s2 = sb2.toString();
        int ans = (int) 1e9;
        int ans1 = 0;
        int ans2 = 0;
        for (int i = 0; i < 2*n; i++) {
            if (s1.charAt(i) != s.charAt(i)) {
                ++ans1;
            }
            if (s2.charAt(i) != s.charAt(i)) {
                ++ans2;
            }
            if (i >= n) { //the most left element is outside of sliding window, we need to subtract the ans if we did `flip` before.
                if (s1.charAt(i - n) != s.charAt(i - n)) --ans1;
                if (s2.charAt(i - n) != s.charAt(i - n)) --ans2;
            }
            if (i >= n - 1)
                ans = Math.min(ans1, Math.min(ans2, ans));
        }
        return ans;
    }
}

class MinNumberOfSwapsDp {
    // my way: calc the initial one first then calc the diff from it each time
    public int minFlips(String s) {
        int n = s.length();
        return Math.min(solve(s, 0), solve(s, 1));
    }

    private int solve(String s, int t){
        int cur = 0;
        int n = s.length();
        int exp= t;
        for(int i=0; i<n; i++){
            int cind = s.charAt(i)-'0';
            if(cind!=exp){
                ++cur;
            }
            exp ^= 1;
        }
        int res = cur;
        exp = t;
        for(int i=1; i<n; ++i){
            if(s.charAt(i-1)-'0' != exp){
                --cur;
            }
            int mod = n%2;
            int endexp = exp;
            if(mod==1){
                endexp ^= 1;
            }
            if(s.charAt(i-1)-'0' != endexp){
                ++cur;
            }
            res = Math.min(res, cur);
            exp ^= 1;
        }
        return res;
    }
}

class MinNumberOfSwapsDp2 {
    // if len is even same as comparing 1010 and 0101
    // otherwise if odd length, for each given point if we start from it after rotation, the left part will have expectation that is reverse of current expectation. we know the diff with ~expectation immediately
    public int minFlips(String s) {
        int n = s.length();
        return Math.min(solve(s, 1), solve(s, 0));
    }

    private int solve(String s, int cur) {
        int n = s.length();
        int[] diff = new int[n];
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) - '0' != cur) {
                diff[i] = 1;
            }
            cur ^= 1;
        }
        int[] rightDiff = new int[n + 1];
        for (int i = n - 1; i >= 0; i--) {
            rightDiff[i] = rightDiff[i + 1] + diff[i];
        }
        if (n % 2 == 0) {
            return rightDiff[0];
        }
        int leftDiff = 0;
        int res = n + 1;
        for (int i = 0; i < n; i++) {
            int cr = rightDiff[i];
            int curlen = cr + (i - leftDiff);
            res = Math.min(res, curlen);
            leftDiff += diff[i];
        }
        return res;
    }
}

