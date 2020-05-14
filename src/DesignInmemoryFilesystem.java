import java.util.*;

public class DesignInmemoryFilesystem {

    static class FileSystem {

        class FileNode {
            String name;
            String content = "";// if folder this is null
            boolean isdir;
            Map<String, FileNode> under = new HashMap<>(); // if file this is null

            public FileNode(String name, boolean isdir) {
                this.name = name;
                this.isdir = isdir;
            }
        }

        FileNode root = new FileNode("", true);

        // whether we create and if we create, do we create as dir or file
        FileNode find(String path, boolean create, boolean isdir) {
            String[] p = path.split("/");
            FileNode f = root;
            for (int i = 1; i < p.length; i++) {
                FileNode cf = f.under.get(p[i]);
                if (cf == null) {
                    if (create) {
                        cf = new FileNode(p[i], isdir);
                        f.under.put(p[i], cf);
                    } else {
                        return null;
                    }
                }
                f = cf;
            }
            return f;
        }

        public List<String> ls(String path) {
            FileNode f = find(path, false, false);
            List<String> r = new ArrayList<>();
            if (!f.isdir) {
                // if ls a file, show the file name
                r.add(f.name);
                return r;
            }
            for (FileNode cf : f.under.values()) {
                // otherwise show the dir content
                r.add(cf.name);
            }
            Collections.sort(r);
            return r;
        }

        public void mkdir(String path) {
            find(path, true, true);
        }

        public void addContentToFile(String filePath, String content) {
            FileNode f = find(filePath, true, false);
            f.content += content;
        }

        public String readContentFromFile(String filePath) {
            FileNode f = find(filePath, false, false);
            if (f == null) {
                return null;
            }
            return f.content;
        }
    }

    public static void main(String[] args) {
        FileSystem fs = new FileSystem();
        System.out.println(fs.ls("/"));
        fs.mkdir("/a/b/c");
        fs.addContentToFile("/a/b/c/d", "hello");
        System.out.println(fs.ls("/"));
        System.out.println(fs.readContentFromFile("/a/b/c/d"));
        System.out.println(fs.ls("/a/b"));
        fs.mkdir("/a/b/d");
        fs.addContentToFile("/a/b/d/e", "fk");
        System.out.println(fs.readContentFromFile("/a/b/d/e"));
    }
}


