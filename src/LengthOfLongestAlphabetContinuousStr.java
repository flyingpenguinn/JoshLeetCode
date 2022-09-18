public class LengthOfLongestAlphabetContinuousStr {
    class Solution {
        public int longestContinuousSubstring(String s) {
            int n = s.length();
            int i = 0;
            int res = 0;
            while(i<n){
                int j = i+1;
                while(j<n && s.charAt(j) == (s.charAt(j-1)+1)){
                    ++j;
                }
                // i...j-1
                int cur = j-i;
                res = Math.max(cur, res);
                i=j;
            }
            return res;
        }
    }
}
