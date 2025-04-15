public class CountGoodTripletsInArray {
    // merge two arrays to one by replacing a values with b's index. then it becomes finding increasing triplet problem in a, which can be solved by BIT
    private int[] bit;
    public long goodTriplets(int[] a,int[] b) {
        int n = a.length;
        bit = new int[n+1];
        int[] bm = new int[n];
        for(int i=0; i<n; ++i){
            bm[b[i]] = i+1;
        }
        long res = 0;
        for(int i=0; i<n; ++i){
            int index = bm[a[i]];
            long left = q(index-1);
            //  System.out.println(a[i]+" "+left+" "+index);
            u(index);
            long right = n-1-i-(index-1-left);
            //    System.out.println(right);
            res += left*right;
        }
        return res;
    }

    private void u(int i){
        while(i<bit.length){
            ++bit[i];
            i += i&(-i);
        }
    }

    private int q(int i){
        int sum = 0;
        while(i>0){
            sum += bit[i];
            i -= i&(-i);
        }
        return sum;
    }
}
