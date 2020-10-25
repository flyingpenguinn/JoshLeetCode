public class SlowestKey {
    public char slowestKey(int[] a, String s) {
        int last = 0;
        int max = -1;
        char maxc = (char)('a'-1);
        for(int i=0; i<a.length;i++){
            int cur = a[i]-last;
            if(cur>max){
                max = cur;
                maxc = s.charAt(i);
            }else if(cur==max){
                if(s.charAt(i)>maxc){
                    maxc = s.charAt(i);
                }
            }
            last = a[i];
        }
        return maxc;
    }
}
