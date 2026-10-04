public class MinimumRotationsDialNumberI {
    public int minRotations(String s) {
        int n = s.length();
        int pre = 0;
        int res = 0;
        for(int i=0; i<n; ++i){
            char c = s.charAt(i);
            int cind = c-'0';
            int way1 = 0;
            int way2 = 0;
            if(cind>pre){
                way1 = cind-pre;
                way2 = 10-cind+pre;
            }else{
                way1 = pre-cind;
                way2 = 10-pre+cind;
            }
         //   System.out.println(pre+" "+cind+" "+way1+" "+way2);
            int cres = Math.min(way1, way2);
            res += cres;
            pre = cind;
        }
        return res;
    }
}
