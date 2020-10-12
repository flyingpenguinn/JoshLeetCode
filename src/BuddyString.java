import java.util.ArrayList;
import java.util.List;

/*
LC#859
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.



Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false


Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
 */
public class BuddyString {

    // mind the special logic when they are equal!
    public boolean buddyStrings(String a, String b) {
        if(a.length() != b.length()){
            return false;
        }
        List<Integer> diffs = new ArrayList<>();
        for(int i=0; i<a.length(); i++){
            if(a.charAt(i) != b.charAt(i)){
                diffs.add(i);
                if(diffs.size()>2){
                    return false;
                }
            }
        }
        if(diffs.isEmpty()){
            boolean[] seen = new boolean[26];
            for(int i=0; i<a.length(); i++){
                if(seen[a.charAt(i)-'a']){
                    return true;
                }
                seen[a.charAt(i)-'a'] = true;
            }
            return false;
        }else{
            return diffs.size()==2 && a.charAt(diffs.get(1)) == b.charAt(diffs.get(0)) && a.charAt(diffs.get(0)) == b.charAt(diffs.get(1));
        }
    }

    public static void main(String[] args) {
        System.out.println(new BuddyString().buddyStrings("bc", "ac"));
    }
}
