import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/*
LC#1268
Given an array of strings products and a string searchWord. We want to design a system that suggests at most three product names from products after each character of searchWord is typed. Suggested products should have common prefix with the searchWord. If there are more than three products with a common prefix return the three lexicographically minimums products.

Return list of lists of the suggested products after each character of searchWord is typed.



Example 1:

Input: products = ["mobile","mouse","moneypot","monitor","mousepad"], searchWord = "mouse"
Output: [
["mobile","moneypot","monitor"],
["mobile","moneypot","monitor"],
["mouse","mousepad"],
["mouse","mousepad"],
["mouse","mousepad"]
]
Explanation: products sorted lexicographically = ["mobile","moneypot","monitor","mouse","mousepad"]
After typing m and mo all products match and we show user ["mobile","moneypot","monitor"]
After typing mou, mous and mouse the system suggests ["mouse","mousepad"]
Example 2:

Input: products = ["havana"], searchWord = "havana"
Output: [["havana"],["havana"],["havana"],["havana"],["havana"],["havana"]]
Example 3:

Input: products = ["bags","baggage","banner","box","cloths"], searchWord = "bags"
Output: [["baggage","bags","banner"],["baggage","bags","banner"],["baggage","bags"],["bags"]]
Example 4:

Input: products = ["havana"], searchWord = "tatiana"
Output: [[],[],[],[],[],[],[]]


Constraints:

1 <= products.length <= 1000
1 <= Σ products[i].length <= 2 * 10^4
All characters of products[i] are lower-case English letters.
1 <= searchWord.length <= 1000
All characters of searchWord are lower-case English letters.
 */
public class SearchSuggestionSystem {
    class TrieNode {
        char c;
        TrieNode[] children = new TrieNode[26];
        PriorityQueue<String> top3 = new PriorityQueue<>(Collections.reverseOrder());

        public TrieNode(char c) {
            this.c = c;
        }

        void insert(String s, int i) {
            if (top3.size() < 3) {
                top3.offer(s);
            } else {
                if (top3.peek().compareTo(s) > 0) {
                    top3.poll();
                    top3.offer(s);
                }
            }
            if (i == s.length()) {
                return;
            }
            char nc = s.charAt(i);
            int nci = nc - 'a';
            if (children[nci] == null) {
                children[nci] = new TrieNode(nc);
            }
            children[nci].insert(s, i + 1);
        }

    }

    TrieNode root = new TrieNode('#');

    public List<List<String>> suggestedProducts(String[] products, String searchWord) {
        for (String p : products) {
            root.insert(p, 0);
        }
        TrieNode p = root;
        List<List<String>> r = new ArrayList<>();
        for (int i = 0; i < searchWord.length(); i++) {
            if (p == null) {
                r.add(new ArrayList<>());
                continue;
            }
            char c = searchWord.charAt(i);
            int ci = c - 'a';
            TrieNode next = p.children[ci];
            if (next == null) {
                r.add(new ArrayList<>());
            } else {
                List<String> ri = new ArrayList<>();
                int size = next.top3.size();
                while (size > 0) {
                    ri.add(next.top3.poll());
                    size--;
                }
                Collections.reverse(ri);
                r.add(ri);
            }
            p = next;
        }
        return r;
    }
}
