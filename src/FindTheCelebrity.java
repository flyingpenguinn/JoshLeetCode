import java.util.ArrayDeque;
import java.util.Deque;

/*
LC#277
Suppose you are at a party with n people (labeled from 0 to n - 1) and among them, there may exist one celebrity. The definition of a celebrity is that all the other n - 1 people know him/her but he/she does not know any of them.

Now you want to find out who the celebrity is or verify that there is not one. The only thing you are allowed to do is to ask questions like: "Hi, A. Do you know B?" to get information of whether A knows B. You need to find out the celebrity (or verify there is not one) by asking as few questions as possible (in the asymptotic sense).

You are given a helper function bool knows(a, b) which tells you whether A knows B. Implement a function int findCelebrity(n). There will be exactly one celebrity if he/she is in the party. Return the celebrity's label if there is a celebrity in the party. If there is no celebrity, return -1.



Example 1:


Input: graph = [
  [1,1,0],
  [0,1,0],
  [1,1,1]
]
Output: 1
Explanation: There are three persons labeled with 0, 1 and 2. graph[i][j] = 1 means person i knows person j, otherwise graph[i][j] = 0 means person i does not know person j. The celebrity is the person labeled as 1 because both 0 and 2 know him but 1 does not know anybody.
Example 2:


Input: graph = [
  [1,0,1],
  [1,1,0],
  [0,1,1]
]
Output: -1
Explanation: There is no celebrity.


Note:

The directed graph is represented as an adjacency matrix, which is an n x n matrix where a[i][j] = 1 means person i knows person j while a[i][j] = 0 means the contrary.
Remember that you won't have direct access to the adjacency matrix.
 */
public class FindTheCelebrity {
    // eliminate one person a time and then check if the remaining one is really celebrity
    // similar to townjudge problem, but here we have an o1 online api to use, while in town judge we know the array directly
    public int findCelebrity(int n) {
        // n>=1
        int cand = 0;
        // cand... i-1, defeated by cand, or no hope
        for (int i = 1; i < n; i++) {
            if (defeated(cand, i)) { // actually just needs knows(cand, i)
                cand = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i != cand && defeated(cand, i)) {
                return -1;
            }
        }
        return cand;
    }

    protected boolean defeated(int cand, int i) {
        return !knows(i, cand) || knows(cand, i);
    }

    private boolean knows(int p2, int p1) {
        // given by problem setter
        return false;
    }
}
