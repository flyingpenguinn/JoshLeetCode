import java.util.*;

public class RemoveSubfoldersFromFileSystem {
    public List<String> removeSubfolders(String[] folder) {
        Set<String> folders = new HashSet();
        Arrays.sort(folder, (a, b) -> Integer.compare(a.length(), b.length()));
        for (String f : folder) {
            StringBuilder sb = new StringBuilder();
            boolean had = false;
            for (int i = 0; i < f.length(); i++) {
                char c = f.charAt(i);
                if (c == '/') {
                    if (folders.contains(sb.toString())) {
                        had = true;
                        break;
                    }
                }
                sb.append(c);
            }
            if (!had) {
                folders.add(f);
            }
        }
        return new ArrayList<>(folders);
    }

    public static void main(String[] args) {
        String[] input = {"/a/b/c","/a/b/ca","/a/b/d"};
        System.out.println(new RemoveSubfoldersFromFileSystem().removeSubfolders(input));
    }
}
