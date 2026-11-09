public class CountValidSubstringsI {
    public long countValidSubstrings(String s) {
        int n = s.length();
        long bad = 0;
        int prestart = -1;
        for(int i=0; i<n; ++i){
            if(s.startsWith("abc", i)){
                int end = i+2;
                int start = i;
                long right = n-end;
                long left = start-prestart;
                long combi = left*right;
                bad += combi;
                prestart = start;
            }
        }
        long len = n;
        long all = len*(len+1)/2;
        return all - bad;
    }
}
