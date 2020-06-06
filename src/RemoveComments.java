import java.util.ArrayList;
import java.util.List;

/**
 * LC#722
 * <p>
 * https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {
    // only thing we need to remember is whether we are in block comment
    // if it's // stop current line and move on to next
    // for // think of a finishing sign before lineend so we got an lineend
    // if we are not in blcok comment add current line because it finished otherwise keep adding till we meet an lineend
    public List<String> removeComments(String[] a) {
        List<String> r = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        boolean bcomment = false;
        for (int i = 0; i < a.length; i++) {
            String line = a[i];
            for (int j = 0; j < line.length(); j++) {
                if (!bcomment) {
                    if (line.startsWith("//", j)) {
                        break; // ending this line but note we are not in block comment
                    } else if (line.startsWith("/*", j)) {
                        bcomment = true;
                        j++; //   /*xxx => move over to x. avoid /*/
                    } else {
                        sb.append(line.charAt(j));
                    }
                } else {
                    if (line.startsWith("*/", j)) {
                        bcomment = false;
                        j++;
                    }
                    // else dont add anything
                }
            }
            if (!bcomment) {
                addtoresult(r, sb);
                sb = new StringBuilder();
            }
            // else do nothing wait for new stuff to match with
        }
        return r;
    }

    protected void addtoresult(List<String> r, StringBuilder sb) {
        String str = sb.toString();
        if (!str.isEmpty()) {
            r.add(str);
        }
    }

    public static void main(String[] args) {
        String[] source3 = {"a//*b/*/c", "blank", "d/*/e/*/f"};
        System.out.println(new RemoveComments().removeComments(source3));
        String[] source = {"/*Test program */", "int main()", "{ ", "  // variable declaration ", "int a, b, c;", "/* This is a test", "   multiline  ", "   comment for ", "   testing */", "a = b + c;", "}"};
        System.out.println(new RemoveComments().removeComments(source));

        String[] source2 = {"a/*comment", "line", "more_comment*/b"};
        System.out.println(new RemoveComments().removeComments(source2));


    }
}
