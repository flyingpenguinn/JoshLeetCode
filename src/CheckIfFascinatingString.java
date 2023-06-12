import java.util.Arrays;

public class CheckIfFascinatingString {
    public boolean isFascinating(int n) {
        int n2 = 2*n;
        int n3 = 3*n;
        String sn = String.valueOf(n) + String.valueOf(n2) + String.valueOf(n3);
        char[] cn = sn.toCharArray();
        Arrays.sort(cn);
        if(cn.length != 9){
            return false;
        }
        for(int i=0; i<cn.length; ++i){
            if(cn[i] -'0' != i+1){
                return false;
            }
        }
        return true;
    }
}
