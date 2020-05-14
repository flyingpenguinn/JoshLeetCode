import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
LC#1156
Given a string text, we are allowed to swap two of the characters in the string. Find the length of the longest substring with repeated characters.



Example 1:

Input: text = "ababa"
Output: 3
Explanation: We can swap the first 'b' with the last 'a', or the last 'b' with the first 'a'. Then, the longest repeated character substring is "aaa", which its length is 3.
Example 2:

Input: text = "aaabaaa"
Output: 6
Explanation: Swap 'b' with the last 'a' (or the first 'a'), and we get longest repeated character substring "aaaaaa", which its length is 6.
Example 3:

Input: text = "aaabbaaa"
Output: 4
Example 4:

Input: text = "aaaaa"
Output: 5
Explanation: No need to swap, longest repeated character substring is "aaaaa", length is 5.
Example 5:

Input: text = "abcdef"
Output: 1


Constraints:

1 <= text.length <= 20000
text consist of lowercase English characters only.
 */
public class SwapForLongestRepeatedChar {
    // find max window where there are two chars at most one has count>1, and can be covered outside the window
    // not the corner cases handled in "good" method
    public int maxRepOpt1(String s) {
        int[] m= new int[26];
        int n= s.length();
        char[] cs= s.toCharArray();
        for(int i=0;i<n;i++){
            m[cs[i]-'a']++;
        }
        int high=-1;
        int low=0;
        int[] cm= new int[26];
        int max= 1;
        while(true){
            if(good(cm,m)){
                max= Math.max(max,high-low+1);
                high++;
                if(high==n){
                    break;
                }
                cm[cs[high]-'a']++;
            }else{
                cm[cs[low]-'a']--;
                low++;
            }
        }
        return max;
    }
    int Max=1000000;

    boolean good(int[] cm, int[] m){
        // at most one nz one is >1
        int min=Max;
        int max=0;
        int mini=-1;
        int maxi=-1;
        int nz=0;
        for(int i=0;i<26;i++){
            if(cm[i]>0){
                nz++;
                if(cm[i]<min){
                    min= cm[i];
                    mini= i;
                }
                if(cm[i]>max){
                    max= cm[i];
                    maxi=i;
                }
            }
        }
        if(nz>=3){
            return false;
        }
        else if(nz<=1){
            return true;
            // empty or already good
        }else{

            if(min>1){
                return false;
            }
            if(max>1 && m[maxi] == cm[maxi]){
                // no spare ones to swap into
                return false;
            }
            if(max==1 && m[mini]==cm[mini] && m[maxi] == cm[maxi]){
                // min=max=1 can pick either
                return false;
            }
            return true;
        }
    }
}
