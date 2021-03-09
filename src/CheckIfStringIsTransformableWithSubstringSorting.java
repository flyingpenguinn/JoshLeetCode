import java.util.*;

public class CheckIfStringIsTransformableWithSubstringSorting {
    // able to sort any substring == able to sort adjacent values.
    // for each number in t, we try to match in s with the left most candidate
    // as long as there is no smaller number on the left blocking the moving we are good

    public boolean isTransformable(String s, String t) {
        Deque<Integer>[] m = new ArrayDeque[10];
        for(int i=0; i<10; i++){
            m[i] = new ArrayDeque<>();
        }
        for(int i=0; i<s.length(); i++){
            int cind = s.charAt(i)-'0';
            m[cind].offerLast(i);
        }
        for(int i=0; i<t.length(); i++){
            int cind = t.charAt(i)-'0';
            if(m[cind].isEmpty()){
                return false;
            }
            int spos = m[cind].pollFirst();
            for(int j=0; j<cind;j++){
                Deque<Integer> nums = m[j];
                // there is a smaller number blocking cind from moving left
                if(!nums.isEmpty() && nums.peekFirst() < spos){
                    return false;
                }
            }
        }
        return true;
    }
}
