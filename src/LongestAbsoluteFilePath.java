import java.util.*;

public class LongestAbsoluteFilePath {
    // need to manipulate the stack directly because we wnat to keep the root to node path in the stack and compare with the last node.
    // "compare with last" is not so easy in a direct recursion
    public int lengthLongestPath(String input) {
        String[] ss = input.split("\\n");
        // str len, level
        Deque<int[]> stack = new ArrayDeque<>();
        int stacklen = 0;
        int max = 0;
        for (String s : ss) {
            int curlevel = level(s);
            int ntlen = s.length() - curlevel;
            while (!stack.isEmpty() && stack.peek()[1] >= curlevel) {
                stacklen -= stack.pop()[0];
            }
            stack.push(new int[]{ntlen, curlevel});
            stacklen += ntlen;
            if (s.contains(".")) {
                // only handle files
                int curpath = stacklen + stack.size() - 1;
                max = Math.max(max, curpath);
            }
        }
        return max;
    }

    //  /t/ts.exe
    private int level(String s) {
        int i = 0;
        while (i < s.length() && s.charAt(i) == '\t') {
            i++;
        }
        return i;
    }

    public static void main(String[] args) {
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"));
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath("dir\n        file.txt"));
        System.out.println(new LongestAbsoluteFilePath().lengthLongestPath("dir\n    file.txt"));
    }
}


class Rubbish {
    private int longest = 0;

    public int lengthLongestPath(String input) {
        String[] inputs = input.split("\n");
        StringBuilder sb = null;
        List<String> normalizedInputs = new ArrayList<>();
        for (int i = 0; i < inputs.length; i++) {
            if (!inputs[i].startsWith("\t")) {
                if (sb != null) {
                    normalizedInputs.add(sb.toString());
                }
                sb = new StringBuilder();
                sb.append(inputs[i]);
            } else {
                sb.append("\n" + inputs[i]);
            }
        }
        if (sb != null) {
            normalizedInputs.add(sb.toString());
        }
        for (String inp : normalizedInputs) {
            dfs(inp, 0, 0);
        }
        return longest;
    }

    private void dfs(String input, int depth, int cur) {
        String root = findRoot(input, depth);
        String[] parts = root.split("\n");
        root = parts[parts.length - 1];
        cur += root.length();
        if (root.contains(".")) {
            longest = Math.max(longest, cur);
            return;
        }
        Collection<String> children = findChildren(input, depth);
        int max = 0;
        for (String s : children) {
            dfs(s, depth + 1, cur + 1);
        }

    }

    private Collection<String> findChildren(String input, int depth) {
        Collection<String> children = new ArrayList<>();
        StringBuilder childDelim = new StringBuilder("\n\t");
        for (int i = 0; i < depth; i++) {
            childDelim.append("\t");
        }
        StringBuilder realDelim = new StringBuilder("\n\t");
        for (int i = 0; i < depth; i++) {
            realDelim.append("\t");
        }
        String[] c = input.split(childDelim.toString());
        if (c.length == 0) {
            return children;
        } else {
            StringBuilder sb = null;
            for (int i = 1; i < c.length; i++) {
                if (!c[i].startsWith("\t")) {
                    if (sb != null) {
                        children.add(sb.toString());
                    }
                    sb = new StringBuilder();
                    sb.append(c[i]);
                } else {
                    sb.append(realDelim.toString() + c[i]);
                }
            }
            if (sb != null) {
                children.add(sb.toString());
            }
        }

        return children;
    }

    private String findRoot(String input, int depth) {
        StringBuilder delim = new StringBuilder("\n\t");
        for (int i = 0; i < depth; i++) {
            delim.append("\t");
        }
        int index = input.indexOf(delim.toString());
        if (index == -1) {
            index = input.length();
        }
        return input.substring(0, index);
    }
}