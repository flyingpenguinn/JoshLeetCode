public class FindUniqueBinaryString {
    // cantor's algo: diff from num1 at the first pos, num2 at the 2nd pos, etc
    public String findDifferentBinaryString(String[] nums) {
        String ans ="";
        for(int i=0; i<nums.length; i++) {
            int cind = (nums[i].charAt(i) - '0');
            ans += Integer.toString((cind ^1));
        }
        return ans;
    }
}
