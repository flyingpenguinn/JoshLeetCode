import java.util.ArrayList;
import java.util.List;

public class CountValidSubstringsII {
    public long countValidSubstrings(String s) {
        char[] c = s.toCharArray();
        int n = c.length;
        List<Integer>[] im = new ArrayList[3];
        for(int i=0; i<3; ++i){
            im[i] = new ArrayList<>();
        }
        for(int i=0; i<n; ++i){
            int cind = c[i]-'a';
            im[cind].add(i);
        }
        for(int i=0; i<3; ++i){
        //    System.out.println(im[i]);
        }
        long bad = 0;
        for(int i=0; i<n; ++i){
            int nexta = binary(im[0], i, n);
            int nextb = binary(im[1], nexta+1, n);
            int nextc = binary(im[2], nextb+1, n);
        //    System.out.println(nexta+" "+nextb+" "+nextc);
            if(nextc<n){
                long rem = n-nextc;
                bad += rem;
            }
        }
        long len = n;
        long all = len*(len+1)/2;
        return all - bad;
    }

    private int binary(List<Integer> a, int t, int nf){
       // System.out.println("binary search "+t+" in "+a);
        int n = a.size();
        int l = 0;
        int u = n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a.get(mid)>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        if(l<n){
            return a.get(l);
        }else{
            return nf;
        }
    }
}
