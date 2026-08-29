class Solution {
    public int minOperations(String s) {
        int n = s.length();
        int res = (int)(1e9);
        for(int i=0; i<n; ++i){
            String cur =  s.substring(i)+s.substring(0, i);
            int cres = calc(cur) + i;
            res = Math.min(res, cres);
        }
        return res;
    }

    private int calc(String s){
        char[] c = s.toCharArray();
        int n = c.length;
        int i = 0;
        int j = n-1;
        int res = 0;
        while(i<j){
            int ci = c[i]-'a';
            int cj = c[j]-'a';
            int cmin = Math.min(ci, cj);
            int cmax = Math.max(ci, cj);
            int way1 = cmax-cmin;
            int way2 = 26-cmax+cmin;
            int minway = Math.min(way1, way2);
            res += minway;
            ++i;
            --j;
        }
    //   System.out.println(s+" "+res);
        return res;
    }
}
