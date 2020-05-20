import java.util.*;

/*
LC#642
Design a search autocomplete system for a search engine. Users may input a sentence (at least one word and end with a special character '#'). For each character they type except '#', you need to return the top 3 historical hot sentences that have prefix the same as the part of sentence already typed. Here are the specific rules:

The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one). If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
If less than 3 hot sentences exist, then just return as many as you can.
When the input is a special character, it means the sentence ends, and in this case, you need to return an empty list.
Your job is to implement the following functions:

The constructor function:

AutocompleteSystem(String[] sentences, int[] times): This is the constructor. The input is historical data. Sentences is a string array consists of previously typed sentences. Times is the corresponding times a sentence has been typed. Your system should record these historical data.

Now, the user wants to input a new sentence. The following function will provide the next character the user types:

List<String> input(char c): The input c is the next character typed by the user. The character will only be lower-case letters ('a' to 'z'), blank space (' ') or a special character ('#'). Also, the previously typed sentence should be recorded in your system. The output will be the top 3 historical hot sentences that have prefix the same as the part of sentence already typed.


Example:
Operation: AutocompleteSystem(["i love you", "island","ironman", "i love leetcode"], [5,3,2,2])
The system have already tracked down the following sentences and their corresponding times:
"i love you" : 5 times
"island" : 3 times
"ironman" : 2 times
"i love leetcode" : 2 times
Now, the user begins another search:

Operation: input('i')
Output: ["i love you", "island","i love leetcode"]
Explanation:
There are four sentences that have prefix "i". Among them, "ironman" and "i love leetcode" have same hot degree. Since ' ' has ASCII code 32 and 'r' has ASCII code 114, "i love leetcode" should be in front of "ironman". Also we only need to output top 3 hot sentences, so "ironman" will be ignored.

Operation: input(' ')
Output: ["i love you","i love leetcode"]
Explanation:
There are only two sentences that have prefix "i ".

Operation: input('a')
Output: []
Explanation:
There are no sentences that have prefix "i a".

Operation: input('#')
Output: []
Explanation:
The user finished the input, the sentence "i a" should be saved as a historical sentence in system. And the following input will be counted as a new search.


Note:

The input sentence will always start with a letter and end with '#', and only one blank space will exist between two words.
The number of complete sentences that to be searched won't exceed 100. The length of each sentence including those in the historical data won't exceed 100.
Please use double-quote instead of single-quote when you write test cases even for a character input.
Please remember to RESET your class variables declared in class AutocompleteSystem, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class DesignSearchAutoCompletionSystem {

}

class AutocompleteSystem {
    class Trie {
        char c;
        List<String> hot = new ArrayList<>();
        Trie[] ch = new Trie[255];

        public Trie(char c) {
            this.c = c;
        }

        void insert(String s, int i) {
            // in tri do processing before i== check and do in current node. after i== decide where to go next
            if (!hot.contains(s)) {
                hot.add(s);
            }
            sorthot(hot);
            if (i == s.length()) {
                return;
            }
            char c = s.charAt(i);
            Trie node = this.ch[c];
            if (node == null) {
                node = ch[c] = new Trie(c);
            }

            node.insert(s, i + 1);
        }

        private void sorthot(List<String> hot) {
            Collections.sort(hot, new Comparator<String>() {
                @Override
                public int compare(String o1, String o2) {
                    int f1 = freq.getOrDefault(o1, 0);
                    int f2 = freq.getOrDefault(o2, 0);
                    if (f1 != f2) {
                        // hotter first
                        return Integer.compare(f2, f1);
                    } else {
                        return o1.compareTo(o2);
                    }
                }
            });
            if (hot.size() > 3) {
                hot.remove(hot.size() - 1);
            }
        }
    }

    Map<String, Integer> freq = new HashMap<>();

    Trie root = new Trie('-');
    Trie p = root;
    StringBuilder sb = new StringBuilder();

    public AutocompleteSystem(String[] sentences, int[] times) {
        for (int i = 0; i < sentences.length; i++) {
            freq.put(sentences[i], times[i]);
        }
        for (int i = 0; i < sentences.length; i++) {
            root.insert(sentences[i], 0);
        }
    }

    public List<String> input(char c) {
        if (c == '#') {
            String str = sb.toString();
            sb = new StringBuilder();
            int nv = freq.getOrDefault(str, 0) + 1;
            freq.put(str, nv);
            root.insert(str, 0);
            p = root;
            return new ArrayList<>();
        } else {
            sb.append(c);
            if (p == null) {
                return new ArrayList<>();
            } else {
                Trie next = p.ch[c];
                p = next;
                return next == null ? new ArrayList<>() : next.hot;
            }
        }
    }
}