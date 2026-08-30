import java.util.ArrayList;
import java.util.List;

public class BinaryPrefixDivisibleBy5 {
    public List<Boolean> prefixesDivBy5(int[] a) {
        List<Boolean> r= new ArrayList<>();
        int b=0;
        int Mod=1000000000;
        for(int i=0;i<a.length;i++){
            b= (b<<1)+a[i];
            if(b%5==0){ r.add(true); }
            else{ r.add(false); }
            b%=Mod;
        }
        return r;
    }
}
