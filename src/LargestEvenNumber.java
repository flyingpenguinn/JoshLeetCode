public class LargestEvenNumber {
    public String largestEven(String s) {
        int n = s.length();
        int i = n-1;
        while(i>=0 && (s.charAt(i)-'0')%2==1){
            --i;
        }
        return s.substring(0, i+1);
    }
}
