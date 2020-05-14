import java.util.HashMap;
import java.util.Map;

/*\
LC#677
Implement a MapSum class with insert, and sum methods.

For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.

For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.

Example 1:
Input: insert("apple", 3), Output: Null
Input: sum("ap"), Output: 3
Input: insert("app", 2), Output: Null
Input: sum("ap"), Output: 5
 */
public class MapSumPairs {
    // prefix, so trie
    class MapSum {
        class TrieNode {
            TrieNode[] ch = new TrieNode[26];
            int sum = 0;

            private void insert(String s, int i, int oldv, int nv) {
                this.sum -= oldv;
                this.sum += nv;
                if (i == s.length()) {
                    return;
                }
                int index = s.charAt(i) - 'a';
                TrieNode next = ch[index];
                if (next == null) {
                    next = new TrieNode();
                    ch[index] = next;
                }
                next.insert(s, i + 1, oldv, nv);
            }

            int getsum(String s, int i) {
                if (i == s.length()) {
                    return this.sum;
                }
                int index = s.charAt(i) - 'a';
                TrieNode next = ch[index];
                if (next == null) {
                    // not found
                    return 0;
                }
                return next.getsum(s, i + 1);
            }
        }

        TrieNode root = new TrieNode();
        Map<String, Integer> map = new HashMap<>();

        public MapSum() {

        }

        public void insert(String key, int val) {
            int oldv = map.getOrDefault(key, 0);
            root.insert(key, 0, oldv, val);
            map.put(key, val);
        }

        public int sum(String prefix) {
            return root.getsum(prefix, 0);
        }
    }
}
