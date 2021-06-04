public class CountPairsInTwoArrays {
    public long countPairs(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int size = 200000;
        int offset = 100000;
        int[] bit = new int[size];
        long res = 0;
        for(int j=0; j<n; j++){
            int diff = nums1[j] - nums2[j]+offset;
            res += query(bit, diff);
            update(bit, nums1[j]-nums2[j]+offset);
        }
        return res;
    }

    private int query(int[] bit, int i){
        int res = 0;
        while(i>0){
            res += bit[i];
            i -= i&(-i);
        }
        return res;
    }

    private void update(int[] bit, int i){
        while(i<bit.length){
            bit[i]++;
            i += i&(-i);
        }
    }


}
