import base.ArrayUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LatestTimeToCatchBus {
    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        Set<Integer> pset = new HashSet<>();
        for(int p: passengers){
            pset.add(p);
        }
        int bn = buses.length;
        int pn = passengers.length;
        int l = 1;
        int u = (int)1e9;
        while(l<=u){
            int mid = l+(u-l)/2;

            if(cando(buses, passengers, capacity, mid)){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        while(pset.contains(u)){
            --u;
        }
        return u;
    }

    private boolean cando(int[] b, int[] p, int cap, int m){
        int bn = b.length;
        int pn = p.length;
        int i = 0;
        int j = 0;

        int lim = 0;
        int oldlim = -1;
        for(;lim<pn; ++lim){
            if(m<p[lim]){
                oldlim= p[lim];
                p[lim] = m;
                break;
            }
        }
        int cur = 0;
        boolean rt = false;
        while(i<bn){
            while(j<=lim && (j==lim?m: p[j]) <= b[i] && cur<cap){
                ++j;
                ++cur;
            }
            if(j > lim){
                rt= true;
                break;
            }else{
                cur = 0;
                ++i;
            }
        }
        if(lim<pn) {
            p[lim] = oldlim;
        }
        return rt;
    }

    public static void main(String[] args) {
        System.out.println(new LatestTimeToCatchBus().latestTimeCatchTheBus(ArrayUtils.read1d("20,30,10"), ArrayUtils.read1d("19,13,26,4,25,11,21"), 2));
    }
}
