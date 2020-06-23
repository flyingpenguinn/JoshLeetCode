import java.util.*;

/*
LC#843
This problem is an interactive problem new to the LeetCode platform.

We are given a word list of unique words, each word is 6 letters long, and one word in this list is chosen as secret.

You may call master.guess(word) to guess a word.  The guessed word should have type string and must be from the original list with 6 lowercase letters.

This function returns an integer type, representing the number of exact matches (value and position) of your guess to the secret word.  Also, if your guess is not in the given wordlist, it will return -1 instead.

For each test case, you have 10 guesses to guess the word. At the end of any number of calls, if you have made 10 or less calls to master.guess and at least one of these guesses was the secret, you pass the testcase.

Besides the example test case below, there will be 5 additional test cases, each with 100 words in the word list.  The letters of each word in those testcases were chosen independently at random from 'a' to 'z', such that every word in the given word lists is unique.

Example 1:
Input: secret = "acckzz", wordlist = ["acckzz","ccbazz","eiowzz","abcczz"]

Explanation:

master.guess("aaaaaa") returns -1, because "aaaaaa" is not in wordlist.
master.guess("acckzz") returns 6, because "acckzz" is secret and has all 6 matches.
master.guess("ccbazz") returns 3, because "ccbazz" has 3 matches.
master.guess("eiowzz") returns 2, because "eiowzz" has 2 matches.
master.guess("abcczz") returns 4, because "abcczz" has 4 matches.

We made 5 calls to master.guess and one of them was the secret, so we pass the test case.
Note:  Any solutions that attempt to circumvent the judge will result in disqualification.
 */
public class GuessTheWord {

    /*
    from leetcode comment:

    There is no solution that can guarantee to find a secret word in 10 tries.
If I make up a test case with wordlist like ["aaaaaa", "bbbbbb" ...., "zzzzzz"],
it needs 26 tries to find the secret.

     why checking 0 match instead of 1,2,3...6 matches, please take a look at this comment. The probability of two words with 0 match is (25/26)^6 = 80%. That is to say, for a candidate word, we have 80% chance to see 0 match with the secret word.
     In this case, we had 80% chance to eliminate the candidate word and its "family" words which have at least 1 match
     */
    class Master {
        int guess(String s) {
            return 0;
        }
    }

    public void findSecretWord(String[] wordlist, Master master) {

        // biggest problem is 0 match. so to filter better we need a way to filter 0 matches quickly
        // one way is to see who shares chars with more strings and pick this more "representative" string first so as to filter more
        Map<String, Integer> map = new HashMap<>();
        // find the word with fewest 0 matches to start with
        for (String s : wordlist) {
            int nz = 0;
            for (String t : wordlist) {
                int mt = match(s, t);
                if (mt != 0) {
                    nz++;
                }
            }
            map.put(s, nz);
        }

        while (map.keySet().size() > 1) {
            String best = null;
            int bestc = 0;

            //      System.out.println("set ="+set);
            for (String k : map.keySet()) {
                Integer c = map.get(k);
                if (c > bestc) {
                    best = k;
                    bestc = c;
                }
            }
            int mt = master.guess(best);
            //      System.out.println(best + " " + bestc + " " + mt);
            if (mt == 6) {
                return;
            }
            map.remove(best);
            Set<String> set = new HashSet<>(map.keySet());
            for (String str : set) {
                if (match(str, best) != mt) {
                    map.remove(str);
                }
            }
        }
        master.guess(map.keySet().iterator().next());
    }

    int match(String s, String t) {
        int r = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == t.charAt(i)) {
                r++;
            }
        }
        return r;
    }
}
