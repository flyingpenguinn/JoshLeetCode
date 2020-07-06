/*
LC#825
Some people will make friend requests. The list of their ages is given and ages[i] is the age of the ith person.

Person A will NOT friend request person B (B != A) if any of the following conditions are true:

age[B] <= 0.5 * age[A] + 7
age[B] > age[A]
age[B] > 100 && age[A] < 100
Otherwise, A will friend request B.

Note that if A requests B, B does not necessarily request A.  Also, people will not friend request themselves.

How many total friend requests are made?

Example 1:

Input: [16,16]
Output: 2
Explanation: 2 people friend request each other.
Example 2:

Input: [16,17,18]
Output: 2
Explanation: Friend requests are made 17 -> 16, 18 -> 17.
Example 3:

Input: [20,30,100,110,120]
Output: 3
Explanation: Friend requests are made 110 -> 100, 120 -> 110, 120 -> 100.


Notes:

1 <= ages.length <= 20000.
1 <= ages[i] <= 120.
 */
public class FriendsOfAppropriateAge {
    // loop on age instead of n. note when i==j , the formula is different: it would be n*(n-1) instead of
    public int numFriendRequests(int[] a) {
        if (a == null) {
            return 0;
        }
        int[] ages = new int[121]; // 0 to 120
        for (int i = 0; i < a.length; i++) {
            ages[a[i]]++;
        }
        int r = 0;
        // loop on age
        for (int i = 0; i <= 120; i++) {
            int start = (int) (0.5 * i + 8);  // b >0.5*a + 7
            int end = i;
            if (i < 100) {
                end = Math.min(end, 100);
            }
            for (int j = start; j <= end; j++) {
                if (i != j) {
                    r += ages[i] * ages[j];
                } else {
                    r += ages[i] * (ages[i] - 1);
                }
            }
        }
        return r;
    }
}
