
import java.util.HashMap;
import java.util.Map;

/*
LC#686
Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it. If no such solution, return -1.

For example, with A = "abcd" and B = "cdabcdab".

Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of A repeated two times ("abcdabcd").

Note:
The length of A and B will be between 1 and 10000.
 */
public class RepeatedStringMatching {
    /*
     if there is an answr must be in one of the 3 cases:
     1. a contains b
     2. a+a contains b
     3. b is xxxaaaaaxxxx. by adding a to match b, we only need to add one more a at most
     */
    public int repeatedStringMatch(String a, String b) {
        int res = 1;
        StringBuilder s = new StringBuilder(a);
        while (s.length() < b.length()) {
            ++res;
            s.append(a);
        }
        if (s.indexOf(b) != -1) {
            return res;
        }
        s.append(a);
        if (s.indexOf(b) != -1) {
            return res + 1;
        }
        return -1;
    }


    public static void main(String[] args) {
        System.out.println(new RepeatedStringMatching().repeatedStringMatch("abcd", "cdabcdab"));


    }
}
