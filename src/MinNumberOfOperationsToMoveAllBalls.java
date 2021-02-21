public class MinNumberOfOperationsToMoveAllBalls {
    // two sides that are independent: left to right, then right to left
    public int[] minOperations(String s) {
        int n = s.length();
        int[] res = new int[n];
        int sum = 0;
        int count = 0;
        // each bigger- i
        for(int i=n-1; i>=0; i--){
            char c = s.charAt(i);
            res[i] += sum-i*count;
            if(c=='1'){
                sum += i;
                count++;
            }
        }
        count = 0;
        sum = 0;
        for(int i=0; i<n; i++){
            char c = s.charAt(i);
            res[i] += i*count-sum;
            if(c=='1'){
                sum += i;
                count++;
            }
        }
        return res;
    }
}
