public class ShortestWayFormString {
    public int shortestWay(String s, String t) {
        int sn = s.length();
        int tn = t.length();
        int i = 0;
        int j = 0;
        int res= 0;
        while(j<tn){
            int k = i;
            while(k<i+sn){
                if(k % sn==0){
                    ++res;
                }
                char sv = s.charAt(k%sn);
                if(sv == t.charAt(j)){
                    break;
                }else{
                    ++k;
                }
            }
            if(k==i+sn){
                return -1;
            }else{
                i = k+1;
                ++j;
            }
        }
        return res;
    }
}
