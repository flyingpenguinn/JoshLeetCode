/*
LC#800
In the following, every capital letter represents some hexadecimal digit from 0 to f.

The red-green-blue color "#AABBCC" can be written as "#ABC" in shorthand.  For example, "#15c" is shorthand for the color "#1155cc".

Now, say the similarity between two colors "#ABCDEF" and "#UVWXYZ" is -(AB - UV)^2 - (CD - WX)^2 - (EF - YZ)^2.

Given the color "#ABCDEF", return a 7 character color that is most similar to #ABCDEF, and has a shorthand (that is, it can be represented as some "#XYZ"

Example 1:
Input: color = "#09f166"
Output: "#11ee66"
Explanation:
The similarity is -(0x09 - 0x11)^2 -(0xf1 - 0xee)^2 - (0x66 - 0x66)^2 = -64 -9 -0 = -73.
This is the highest among any shorthand color.
Note:

color is a string of length 7.
color is a valid RGB color: for i > 0, color[i] is a hexadecimal digit from 0 to f
Any answer which has the same (highest) similarity as the best answer will be accepted.
All inputs and outputs should use lowercase letters, and the output is 7 characters.
 */
public class SimilarRGBColors {
    public String similarRGB(String col) {
        int n = col.length();
        StringBuilder sb = new StringBuilder("#");
        for (int i = 1; i < n; i += 2) {
            int cur = (tonum(col.charAt(i))) * 16 + tonum(col.charAt(i + 1));
            int mind = 1000;
            int min = -1;
            for (int j = 0; j < 16; j++) {
                int next = j * 16 + j;
                int d = Math.abs(next - cur);
                // System.out.println(cur+" "+next+" "+d+ " "+mind);
                if (d < mind) {
                    min = j;
                    mind = d;
                }
            }
            // System.out.println("min="+min);
            sb.append(tochar(min));
            sb.append(tochar(min));
        }
        return sb.toString();

    }

    int tonum(char c) {
        if (c <= '9') {
            return c - '0';
        } else {
            return 10 + (c - 'a');
        }
    }

    char tochar(int n) {
        return n <= 9 ? (char) (n + '0') : (char) (n - 10 + 'a');
    }
}
