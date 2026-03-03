import base.ArrayUtils;

public class MinBitwiseOrFromGrid {
    public int minimumOR(int[][] a) {

        int mask = (1 << 20) - 1;
        for (int i = 19; i >= 0; i--) {
            int unset = ~(1 << i);
          //  System.out.println(Integer.toBinaryString(unset));
         //   System.out.println(Integer.toBinaryString(mask));
            int cmask = mask & unset;
          //  System.out.println(Integer.toBinaryString(cmask));
            if(canunset(a, cmask)){
                mask &= (~(1<<i));
            }
        }
        return mask;
    }

    private boolean canunset(int[][] a, int cmask) {
        int m = a.length;
        int n = a[0].length;
        int rev = ~cmask;
        for(int i=0; i<m; ++i){
            boolean good = false;
            for(int j=0; j<n; ++j){
                if(((a[i][j]& rev))==0){
                    good = true;
                    break;
                }
            }
            if(!good){
                return false;
            }
        }
        return true;
    }

    static void main() {
        System.out.println(new MinBitwiseOrFromGrid().minimumOR(ArrayUtils.read("[[1,5],[2,4]]")));
    }
}
