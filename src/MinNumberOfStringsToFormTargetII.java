import java.util.*;


class MinNumberOfStringsToFormTargetII {

    class AhoCorasick {
        int tot;
        int[][] ch;
        int[] dep;
        int[] fail;

        int newNode(int d) {
            int ret = tot++;
            Arrays.fill(ch[ret], 0);
            dep[ret] = d;
            fail[ret] = 0;
            return ret;
        }

        void reset(int size) {
            tot = 0;
            ch = new int[size + 10][26];
            dep = new int[size + 10];
            fail = new int[size + 10];
            newNode(0);
        }

        public AhoCorasick(int size) {
            reset(size);
        }

        void insert(String s) {
            int now = 0;
            for (int i = 0; i < s.length(); i++) {
                int c = s.charAt(i) - 'a';
                if (ch[now][c] == 0) {
                    ch[now][c] = newNode(dep[now] + 1);
                }
                now = ch[now][c];
            }
        }

        void buildFail(String s) {
            Deque<Integer> q = new ArrayDeque<>();
            for (int c = 0; c < 26; c++) {
                if (ch[0][c] > 0) {
                    q.offerLast(ch[0][c]);
                }
            }
            while (!q.isEmpty()) {
                int sn = q.pollFirst();
                for (int c = 0; c < 26; c++) {
                    int x = ch[sn][c], y = ch[fail[sn]][c];
                    if (x > 0) {
                        fail[x] = y;
                        q.offerLast(x);
                    } else {
                        ch[sn][c] = y;
                    }
                }
            }
        }
    }

    public class Solution {
        AhoCorasick ac;

        public int minValidStrings(String[] words, String target) {
            int tn = target.length();
            for(String w: words){
                tn += w.length();
            }
            ac = new AhoCorasick(tn);
            // 用 words 里的字符串构建 AC 自动机
            ac.reset(tn);
            for (String word : words) ac.insert(word);
            ac.buildFail(target);

            int n = target.length();
            long[] f = new long[n + 1];
            f[0] = 0;
            // 将 target 输入 AC 自动机，now 是 target 的第 i 个字符匹配的节点
            for (int i = 1, now = 0; i <= n; i++) {
                int c = target.charAt(i - 1) - 'a';
                now = ac.ch[now][c];
                // AC 自动机上匹配失败，返回无解
                if (now == 0) return -1;
                // 最后一次加上的前缀长度，就是当前节点的深度
                f[i] = f[i - ac.dep[now]] + 1;
            }
            return (int) f[n];
        }
    }


    public static void main(String[] args) {
        MinNumberOfStringsToFormTargetII app = new MinNumberOfStringsToFormTargetII();
        System.out.println(app.new Solution().minValidStrings(new String[]{"abc","aaaaa","bcdef"}, "aabcdabc"));
    }
}

