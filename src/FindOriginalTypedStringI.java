public class FindOriginalTypedStringI {
    public int possibleStringCount(String s) {
        int n = s.length();
        int i = 0;
        int res = 1;
        while(i<n){
            int j = i;
            while(j<n && s.charAt(j) == s.charAt(i)){
                ++j;
            }
            int len = j-i;
            res += len-1;
            i = j;
        }
        return res;
    }
}
