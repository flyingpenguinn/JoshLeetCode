public class MaximumGapBetweenStations {
    public int maximumGap(String s, String t) {
        char[] cs = s.toCharArray();
        char[] ct = t.toCharArray();
        int sn = cs.length;
        int tn = ct.length;
        int[] last = new int[sn];
        int i = sn-1;
        int j = tn-1;
        while(i>=0 && j>=0){
            if(cs[i] == ct[j]){
                last[i] = j;
                --i;
            }
            --j;
        }
        int[] first = new int[sn];
        i = 0;
        j = 0;
        while(i<sn && j<tn){
            if(cs[i] == ct[j]){
                first[i] = j;
                ++i;
            }
            ++j;
        }
        int res = 0;
        for(i=1; i<sn; ++i){
            int cur = last[i] - first[i-1];
            res = Math.max(res, cur);
        }
        return res;
    }
}
