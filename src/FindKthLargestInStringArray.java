import java.util.Arrays;

public class FindKthLargestInStringArray {
    // can use heap or select algo too
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(nums, (x, y)->{
            return compare(x,y);
        });
        return nums[nums.length-k];
    }

    private int compare(String x, String y){
        if(x.length()!= y.length()){
            return Integer.compare(x.length(), y.length());
        }
        for(int i=0; i<x.length(); ++i){
            if(x.charAt(i)!= y.charAt(i)){
                return Character.compare(x.charAt(i), y.charAt(i));
            }
        }
        return 0;
    }
}
