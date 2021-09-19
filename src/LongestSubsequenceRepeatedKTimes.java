public class LongestSubsequenceRepeatedKTimes {
    // just brute force it...
    private StringBuilder res = null;
    private int limit = 0;
    private boolean[] hot = new boolean[26];
    public String longestSubsequenceRepeatedK(String s, int k) {
        int n = s.length();
        int[] count = new int[26];
        limit = n/k;
        for(int i=0; i<n; ++i){
            ++count[s.charAt(i)-'a'];
        }
        for(int i=0; i<26; ++i){
            if(count[i]>=k){
                hot[i] = true;
            }
        }
        dfs(new StringBuilder(), 0, s,k);
        return res==null?"":res.toString();
    }

    private void dfs(StringBuilder cur, int i, String s, int k){
        if(i==limit){
            return;
        }
        for(int j=0; j<26; ++j){
            if(!hot[j]){
                continue;
            }
            cur.append((char)('a'+j));
            if(issub(cur, s, k)){
                if(res == null || res.length() < cur.length()){
                    //System.out.println("re assigning from "+res);
                    res = new StringBuilder(cur);

                }else if(res.length() == cur.length() && res.compareTo(cur)<0){
                    // System.out.println("re assigning from "+res);
                    res = new StringBuilder(cur);
                }
                dfs(cur, i+1, s, k);
            }
            cur.deleteCharAt(cur.length()-1);
        }
    }

    private boolean issub(StringBuilder sb, String s, int k){
        int j = 0;
        int i = 0;
        while(i<s.length() && k>0){
            if(s.charAt(i) == sb.charAt(j)){
                ++j;
            }
            ++i;
            if(j==sb.length()){
                --k;
                j=0;
            }
        }
        return k==0;
    }
}
