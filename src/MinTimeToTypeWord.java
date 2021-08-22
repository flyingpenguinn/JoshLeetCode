public class MinTimeToTypeWord {
    // always move the shorter distance
    public int minTimeToType(String word) {
        int cur = 0;
        int res = 0;
        int n=word.length();
        for(int i=0; i<n; i++){
            int cind = word.charAt(i)-'a';
            int dist1 = cind-cur;
            if(dist1<0){
                dist1+= 26;
            }
            int dist2 = cur-cind;
            if(dist2<0){
                dist2 += 26;
            }
            int mindist = Math.min(dist1, dist2);
            res += mindist+1;
            cur = cind;
        }
        return res;
    }
}
