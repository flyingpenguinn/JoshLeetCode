/*
LC#383
Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.

Each letter in the magazine string can only be used once in your ransom note.

Note:
You may assume that both strings contain only lowercase letters.

canConstruct("a", "b") -> false
canConstruct("aa", "ab") -> false
canConstruct("aa", "aab") -> true
 */
public class RansomNote {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] m = new int[255];

        for (int i = 0; i < magazine.length(); i++) {
            m[magazine.charAt(i)]++;
        }

        for (int i = 0; i < ransomNote.length(); i++) {
            int rind = ransomNote.charAt(i);
            m[rind]--;
            if (m[rind] < 0) {
                return false;
            }
        }
        return true;
    }
}
