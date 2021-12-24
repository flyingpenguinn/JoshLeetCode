public class FindFirstPalindromicString {
    public String firstPalindrome(String[] words) {
        for(String w: words){
            if(ispalin(w)){
                return w;
            }
        }
        return "";
    }

    private boolean ispalin(String w){
        int i = 0;
        int j = w.length()-1;
        while(i<j){
            if(w.charAt(i++) != w.charAt(j--)){
                return false;
            }
        }
        return true;
    }
}
