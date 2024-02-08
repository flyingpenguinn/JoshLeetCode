import java.util.HashSet;
import java.util.Set;

public class CountNumbersWithUniqueDigitsII {
    public int numberCount(int a, int b) {
        int res = 0;
        for(int i=a; i<=b; ++i){
            Set<Integer> set = new HashSet<>();
            String str = String.valueOf(i);
            for(int j=0; j<str.length(); ++j){
                int cind = str.charAt(j)-'0';
                set.add(cind);
            }
            if(set.size() == str.length()){
                ++res;
            }
        }
        return res;
    }
}
