import base.ArrayUtils;

import java.util.PriorityQueue;

public class KthSmallestProductOfTwoSortedArrays {
    public long kthSmallestProduct(int[] a, int[] b, long k) {
        long l = (long)-1e10;
        long u = (long)1e10;
        while(l<=u){
            long mid = l+(u-l)/2;
            long cnt = count(a, b, mid);
            // how many products <=mid
            if(cnt>=k){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return l;
    }

    // how many products <= m
    private long count(int[] a, int[] b, long m){
        long res = 0;
        for(int i=0; i<a.length; ++i){
            long cnt = 0;
            if(a[i]>0){
                cnt = find1(b, m*1.0/a[i]);
            }else if(a[i]<0){
                cnt = find2(b, m*1.0/a[i]);
            }else{
                // ==0
                cnt = m>=0? b.length: 0;
            }
            res += cnt;
        }
        return res;
    }

    // how many <=t, ie the last <=
    private int find1(int[] a, double t){
        int n = a.length;
        int l= 0;
        int u = n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid]<=t){
                l = mid+1;
            }else{
                u = mid-1;
            }
        }
        return l;
    }

    // how many >=t
    private int find2(int[] a, double t){
        int n = a.length;
        int l= 0;
        int u = n-1;
        while(l<=u){
            int mid = l+(u-l)/2;
            if(a[mid]>=t){
                u = mid-1;
            }else{
                l = mid+1;
            }
        }
        return n-l;
    }

    public static void main(String[] args) {
        System.out.println(new KthSmallestProductOfTwoSortedArrays().kthSmallestProduct(
                ArrayUtils.read1d("[-2,-1,0,1,2]"),
                ArrayUtils.read1d("[-3,-1,2,4,5]"),
                3
        ));
    }
}
