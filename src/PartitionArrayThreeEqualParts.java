import base.ArrayUtils;

public class PartitionArrayThreeEqualParts {
    // just need to find two parts
    public boolean canThreePartsEqualSum(int[] a) {
        int n = a.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += a[i];
        }
        if (sum % 3 != 0) {
            return false;
        }
        int times = 0;
        int pref = 0;
        for (int i = 0; i < n; i++) {
            if (times == 2) {
                return true;
            }
            pref += a[i];
            if (pref == sum / 3) {
                times++;
                pref = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(new PartitionArrayThreeEqualParts().canThreePartsEqualSum(ArrayUtils.read1d("[18,12,-18,18,-19,-1,10,10]")));
    }
}