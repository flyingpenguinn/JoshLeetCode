import java.util.Arrays;

public class MaxNumberOfRemovableChars {

    public int maximumRemovals(String s, String p, int[] removable) {
        int rn = removable.length;
        if(rn==0){
            return 0;
        }
        int[][] rems = new int[rn][2];
        for(int i=0; i<rn; i++){
            rems[i][0] = removable[i];
            rems[i][1] = i;
        }
        Arrays.sort(rems, (x,y) -> Integer.compare(x[0], y[0]));
        int l = 0;
        int u = rn-1;
        while(l<=u){
            int mid = l+(u-l)/2;

            int j=0;
            int k = 0;
            for(int i=0; i<s.length() && k<p.length(); i++){
                if(j<rn && rems[j][0]==i ){
                    if(rems[j++][1] <=mid){
                        // skip this i not to compare with p. note we always move j here
                        continue;
                    }
                }
                if(k<p.length() && s.charAt(i)==p.charAt(k)){
                    k++;
                }
            }
            if(k==p.length()){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return u+1;
    }
}
