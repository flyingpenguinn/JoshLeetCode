import java.util.ArrayList;
import java.util.List;
/**
LC#722

https://leetcode.com/problems/remove-comments/
 */
public class RemoveComments {
    // /*, */, // , othres, commented or not, list all the possibilities
    public List<String> removeComments(String[] source) {
        List<String> r = new ArrayList<>();
        boolean commented = false;
        StringBuilder sb = new StringBuilder();
        for (String s : source) {
            int i = 0;
            while (i < s.length()) {
                if (s.startsWith("/*", i)) {
                    if (commented) {
                        i++;
                    } else {
                        commented = true;
                        i += 2; // skip the *
                    }
                } else if (s.startsWith("*/", i)) {
                    if (commented) {
                        commented = false;
                        i += 2;
                    } else {
                        // need to handle unmatched */ as normal chars
                        sb.append(s.charAt(i++));
                    }
                } else {
                    if (commented) {
                        i++;
                    } else if (s.startsWith("//", i)) {
                        break;
                    } else {
                        sb.append(s.charAt(i++));
                    }
                }
            }
            if (!commented && sb.length() > 0) {
                // use this to merge lines together
                r.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        return r;
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
