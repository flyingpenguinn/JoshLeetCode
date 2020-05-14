import base.ArrayUtils;

public class PartitionArrayThreeEqualParts {
    public boolean canThreePartsEqualSum(int[] a) {
        int sum = 0;
        int n = a.length;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % 3 != 0) {
            return false;
        }
        int rsum = 0;
        int t = sum / 3;
        int parts = 3;
        for (int i = 0; i < n; i++) {
            rsum += a[i];
            if (rsum == t) {
                parts--;
                if (parts == 1) {
                    // can return when we find 2 parts because the last part must == sum/3 no matter how many zeros are there
                    return true;
                } else {
                    rsum = 0;
                }
            }
            // can't return when > because there could be negativge values...
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionArrayThreeEqualParts().canThreePartsEqualSum(ArrayUtils.read1d("[18,12,-18,18,-19,-1,10,10]")));
    }
}