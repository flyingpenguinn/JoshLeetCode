import java.util.Random;

/*
LC#470
Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.

Do NOT use system's Math.random().



Example 1:

Input: 1
Output: [7]
Example 2:

Input: 2
Output: [8,4]
Example 3:

Input: 3
Output: [8,1,10]


Note:

rand7 is predefined.
Each testcase has one argument: n, the number of times that rand10 is called.


Follow up:

What is the expected value for the number of calls to rand7() function?
Could you minimize the number of calls to rand7()?
 */
public class Rand10UsingRand7 {
    // i.e. rand 0-9 using rand 0-6
    public int rand10() {
        while (true) {
            int sum = 7 * rand6() + rand6(); // - to 48
            // note we dont use x*rand6() only becaus that only covers limited numbers and 0 and 5 are duplicates
            // can't do rand6+rand6 because number 1 has two representations: 1,0 or 0,1. note this problem exists for any
            // x*rand6 where x <=6
            if (sum < 40) {
                return 1 + sum % 10;
            }
        }
    }

    // 0 to 6
    private int rand6() {
        return rand7() - 1;
    }

    // fake implementation
    private int rand7() {
        return 0;
    }
}
