/*
LC#1410
HTML entity parser is the parser that takes HTML code as input and replace all the entities of the special characters by the characters itself.

The special characters and their entities for HTML are:

Quotation Mark: the entity is &quot; and symbol character is ".
Single Quote Mark: the entity is &apos; and symbol character is '.
Ampersand: the entity is &amp; and symbol character is &.
Greater Than Sign: the entity is &gt; and symbol character is >.
Less Than Sign: the entity is &lt; and symbol character is <.
Slash: the entity is &frasl; and symbol character is /.
Given the input text string to the HTML parser, you have to implement the entity parser.

Return the text after replacing the entities by the special characters.



Example 1:

Input: text = "&amp; is an HTML entity but &ambassador; is not."
Output: "& is an HTML entity but &ambassador; is not."
Explanation: The parser will replace the &amp; entity by &
Example 2:

Input: text = "and I quote: &quot;...&quot;"
Output: "and I quote: \"...\""
Example 3:

Input: text = "Stay home! Practice on Leetcode :)"
Output: "Stay home! Practice on Leetcode :)"
Example 4:

Input: text = "x &gt; y &amp;&amp; x &lt; y is always false"
Output: "x > y && x < y is always false"
Example 5:

Input: text = "leetcode.com&frasl;problemset&frasl;all"
Output: "leetcode.com/problemset/all"


Constraints:

1 <= text.length <= 10^5
The string may contain any possible characters out of all the 256 ASCII characters.
 */
public class HtmlEntityParser {
    public String entityParser(String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < text.length()) {
            if (text.startsWith("&quot;", i)) {
                sb.append("\"");
                i += "&quot;".length();
            } else if (text.startsWith("&apos;", i)) {
                sb.append("'");
                i += "&apos;".length();
            } else if (text.startsWith("&amp;", i)) {
                sb.append("&");
                i += "&amp;".length();
            } else if (text.startsWith("&gt;", i)) {
                sb.append(">");
                i += "&gt;".length();
            } else if (text.startsWith("&lt;", i)) {
                sb.append("<");
                i += "&lt;".length();
            } else if (text.startsWith("&frasl;", i)) {
                sb.append("/");
                i += "&frasl;".length();
            } else {
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
