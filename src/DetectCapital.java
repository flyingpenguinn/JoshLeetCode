/*
LC#520
Given a word, you need to judge whether the usage of capitals in it is right or not.

We define the usage of capitals in a word to be right when one of the following cases holds:

All letters in this word are capitals, like "USA".
All letters in this word are not capitals, like "leetcode".
Only the first letter in this word is capital, like "Google".
Otherwise, we define that this word doesn't use capitals in a right way.


Example 1:

Input: "USA"
Output: True


Example 2:

Input: "FlaG"
Output: False


Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters.
 */
public class DetectCapital {
    //1. when we see cap, we must have never seen lower
    //2. when we see lower, either never see cap, or cap is the first
    public boolean detectCapitalUse(String word) {
        if (word == null) {
            return true;
        }
        int lastCap = -1;
        int lastLower = -1;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                if (lastLower >= 0) {
                    return false;
                }
                lastCap = i;
            } else {
                if (lastCap > 0) {
                    return false;
                }
                lastLower = i;
            }
        }
        return true;
    }
}
