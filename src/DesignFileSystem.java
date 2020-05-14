import java.util.HashMap;
import java.util.Map;

/*

LC#1166

You are asked to design a file system which provides two functions:

createPath(path, value): Creates a new path and associates a value to it if possible and returns True. Returns False if the path already exists or its parent path doesn't exist.
get(path): Returns the value associated with a path or returns -1 if the path doesn't exist.
The format of a path is one or more concatenated strings of the form: / followed by one or more lowercase English letters. For example, /leetcode and /leetcode/problems are valid paths while an empty string and / are not.

Implement the two functions.

Please refer to the examples for clarifications.



Example 1:

Input:
["FileSystem","createPath","get"]
[[],["/a",1],["/a"]]
Output:
[null,true,1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/a", 1); // return true
fileSystem.get("/a"); // return 1
Example 2:

Input:
["FileSystem","createPath","createPath","get","createPath","get"]
[[],["/leet",1],["/leet/code",2],["/leet/code"],["/c/d",1],["/c"]]
Output:
[null,true,true,2,false,-1]
Explanation:
FileSystem fileSystem = new FileSystem();

fileSystem.createPath("/leet", 1); // return true
fileSystem.createPath("/leet/code", 2); // return true
fileSystem.get("/leet/code"); // return 2
fileSystem.createPath("/c/d", 1); // return false because the parent path "/c" doesn't exist.
fileSystem.get("/c"); // return -1 because this path doesn't exist.


Constraints:

The number of calls to the two functions is less than or equal to 10^4 in total.
2 <= path.length <= 100
1 <= value <= 10^9
NOTE: create method has been changed on August 29, 2019 to createPath. Please reset to default code definition to get new method signature.
 */
public class DesignFileSystem {

    static class FileSystem {

        private class FileNode {
            String name;
            int val = -1;

            public FileNode(String name, int val) {
                this.name = name;
                this.val = val;
            }

            Map<String, FileNode> children = new HashMap<>();

            boolean create(String[] paths, int val, int i) {
                if (paths[i].isEmpty()) {
                    return false;
                }
                int n = paths.length;
                FileNode c = children.get(paths[i]);
                if (c == null) {
                    if (i == n - 1) {
                        // last
                        children.put(paths[i], new FileNode(paths[i], val));
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    // last && path exists
                    if (i == n - 1) {
                        return false;
                    } else {
                        return c.create(paths, val, i + 1);
                    }
                }
            }

            int find(String[] paths, int i) {
                if (i == paths.length) {
                    return val;
                }
                FileNode next = children.get(paths[i]);
                if (next == null) {
                    return -1;
                }
                return next.find(paths, i + 1);
            }
        }

        FileNode root = new FileNode("-", 0);

        public FileSystem() {

        }

        public boolean createPath(String path, int value) {
            String[] paths = path.split("/");
            // from 1 because 0 is empty
            return root.create(paths, value, 1);
        }

        public int get(String path) {
            String[] paths = path.split("/");
            return root.find(paths, 1);

        }
    }

    public static void main(String[] args) {
        FileSystem fileSystem = new FileSystem();

        System.out.println(fileSystem.createPath("/leet", 1)); // return true
        System.out.println(fileSystem.createPath("/leet/code", 2)); // return true
        System.out.println(fileSystem.get("/leet/code")); // return 2
        System.out.println(fileSystem.createPath("/c/d", 1)); // return false because the parent path "/c" doesn't exist.
        System.out.println(fileSystem.get("/c")); // return -1 because this path doesn't exist.
    }
}

