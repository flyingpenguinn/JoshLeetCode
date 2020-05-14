public class MaxConsecutiveOnesII {

    public static void main(String[] args) {
        int[] a = {1, 0, 1, 1, 0};
        System.out.println(new MaxConsecutiveOnesII().findMaxConsecutiveOnes(a));
    }

    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;


        int[] right = new int[n];
        int oneOnRight = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (nums[i] == 0) {
                right[i] = oneOnRight;
                oneOnRight = 0;
            } else {
                oneOnRight++;
            }
        }
        int max = 0;
        int ones = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                max = Math.max(max, ones + right[i] + 1);
                ones = 0;
            } else {
                ones++;
                max = Math.max(max, ones);
            }
        }
        return max;
    }
}

class MaxConsecutiveOnesIISlidingWindow {

    public static void main(String[] args) {
        int[] a = {0, 0};
        System.out.println(new MaxConsecutiveOnesII().findMaxConsecutiveOnes(a));
    }

    // maintain a window of at most k zeros. same as question "max substring with at most k zeros..."
    public int findMaxConsecutiveOnes(int[] a) {
        int low = 0;
        int high = -1;
        int zeros = 0;
        int r = 0;
        int n = a.length;
        while(true){
            if(zeros<=1){
                r = Math.max(r,high-low+1);
                high++;
                if(high==n){
                    break;
                }
                zeros += a[high]==1?0:1;
            }else{
                zeros -= a[low]==1?0:1;
                low++;
            }
        }
        return r;
    }
}

