public class LargestOddNumberInString {
    public String largestOddNumber(String s) {
        int n = s.length();
        for(int i=n-1; i>=0; i--){
            int cind = s.charAt(i)-'0';
            if(cind %2==1){
                return s.substring(0, i+1);
            }
        }
        return "";
    }
}
