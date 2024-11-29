public class ShiftDistanceBetweenStrings {
    private long[] dp;
    public long shiftDistance(String s, String t, int[] nc, int[] pc) {
        int n = s.length();
        long res = 0;
        for(int i=0; i<n; ++i){
            int sc = s.charAt(i)-'a';
            int tc = t.charAt(i)-'a';
            if(sc==tc){
                continue;
            }
            long gon = 0;
            int psc= sc;
            while(psc!= tc){
                gon += nc[psc];
                ++psc;
                psc %= 26;
            }
            psc = sc;
            long gop = 0;
            while(psc != tc){
                gop += pc[psc];
                --psc;
                psc += 26;
                psc %= 26;
            }
            long cur = Math.min(gon, gop);
            res += cur;
        }
        return res;
    }
}
