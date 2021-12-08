import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindThreeDigitEvenNumbers {
    public int[] findEvenNumbers(int[] a) {
        int[] count = new int[10];
        for(int ai: a){
            count[ai]++;
        }
        List<Integer> res = new ArrayList<>();
        for(int i=100; i<=998; i+=2){
            int[] ncount = Arrays.copyOf(count, 10);
            int[] ds = new int[3];
            ds[0] = (i/100);
            ds[1] = (i%10);
            ds[2] = (i-i/100*100)/10;
            boolean bad = false;
            for(int di: ds){
                if(ncount[di]==0){
                    bad = true;
                    break;
                }
                --ncount[di];
            }
            if(!bad){
                res.add(i);
            }
        }
        int[] rr = new int[res.size()];
        for(int i=0; i<res.size(); ++i){
            rr[i] = res.get(i);
        }
        return rr;
    }
}
