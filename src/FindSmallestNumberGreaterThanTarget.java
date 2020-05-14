import java.util.Arrays;

/*
LC#744
Given a list of sorted characters letters containing only lowercase letters, and given a target letter target, find the smallest element in the list that is larger than the given target.

Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.

Examples:
Input:
letters = ["c", "f", "j"]
target = "a"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "c"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "d"
Output: "f"

Input:
letters = ["c", "f", "j"]
target = "g"
Output: "j"

Input:
letters = ["c", "f", "j"]
target = "j"
Output: "c"

Input:
letters = ["c", "f", "j"]
target = "k"
Output: "c"
Note:
letters has a length in range [2, 10000].
letters consists of lowercase letters, and contains at least 2 unique letters.
target is a lowercase letter.
 */
public class FindSmallestNumberGreaterThanTarget {
    // smallest bigger and sorted... binary search!
    public char nextGreatestLetter(char[] letters, char target) {
        int min = 100;
        for (char c : letters) {
            if (c > target) {
                min = Math.min(min, c - 'a');
            } else {
                min = Math.min(min, c + 26 - 'a');
            }
        }
        return (char) ('a' + (min % 26));
    }

}

class FindSmallestNumberGreaterBinarySearch {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0;
        int u = letters.length - 1;
        while (l <= u) {
            int mid = l + (u - l) / 2;
            if (letters[mid] <= target) {
                l = mid + 1;
            } else {
                u = mid - 1;
            }
        }
        if (l == letters.length) {
            return letters[0];
        } else {
            return letters[l];
        }
    }
}
