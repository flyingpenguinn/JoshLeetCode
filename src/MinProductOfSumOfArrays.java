import java.util.Arrays;

public class MinProductOfSumOfArrays {
    public int minProductSum(int[] num1, int[] num2) {
        Arrays.sort(num1);
        Arrays.sort(num2);
        int res = 0;
        for(int i=0; i<num1.length; i++){
            res += num1[i]*num2[num2.length-1-i];
        }
        return res;
    }
}
