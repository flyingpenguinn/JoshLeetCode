import java.util.*;

public class TextEditor {
    // cursor is at the top of left
    private ArrayDeque<Character> left = new ArrayDeque<>();
    private ArrayDeque<Character> right = new ArrayDeque<>();

    public TextEditor() {

    }

    public void addText(String text) {
        for (int i = 0; i < text.length(); ++i) {
            char c = text.charAt(i);
            left.offerLast(c);
        }
    }

    public int deleteText(int k) {
        int deleted = 0;
        while (!left.isEmpty() && k > 0) {
            left.pollLast();
            --k;
            ++deleted;
        }
        return deleted;
    }

    public String cursorLeft(int k) {
        while (!left.isEmpty() && k > 0) {
            char ltop = left.pollLast();
            right.offerFirst(ltop);
            --k;
        }
        Deque<Character> dq = new ArrayDeque<>();
        return getString(10);
    }

    public String cursorRight(int k) {
        while (!right.isEmpty() && k > 0) {
            char rtop = right.pollFirst();
            left.offerLast(rtop);
            --k;
        }
        return getString(10);
    }

    protected String getString(int limit) {
        Deque<Character> dq = new ArrayDeque<>();
        while (!left.isEmpty() && limit > 0) {
            dq.offerLast(left.pollLast());
            --limit;
        }
        StringBuilder sb = new StringBuilder();
        while (!dq.isEmpty()) {
            char c = dq.pollLast();
            left.offerLast(c);
            sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        TextEditor textEditor = new TextEditor(); // The current text is "|". (The '|' character represents the cursor)
        textEditor.addText("leetcode"); // The current text is "leetcode|".
        System.out.println(textEditor.deleteText(4)); // return 4
        // The current text is "leet|".
        // 4 characters were deleted.
        textEditor.addText("practice"); // The current text is "leetpractice|".
        System.out.println(textEditor.cursorRight(3)); // return "etpractice"
        // The current text is "leetpractice|".
        // The cursor cannot be moved beyond the actual text and thus did not move.
        // "etpractice" is the last 10 characters to the left of the cursor.
        System.out.println(textEditor.cursorLeft(8)); // return "leet"
        // The current text is "leet|practice".
        // "leet" is the last min(10, 4) = 4 characters to the left of the cursor.
        System.out.println(textEditor.deleteText(10)); // return 4
        // The current text is "|practice".
        // Only 4 characters were deleted.
        System.out.println(textEditor.cursorLeft(2)); // return ""
        // The current text is "|practice".
        // The cursor cannot be moved beyond the actual text and thus did not move.
        // "" is the last min(10, 0) = 0 characters to the left of the cursor.
        System.out.println(textEditor.cursorRight(6)); // return "practi"
        // The current text is "practi|ce".
        // "practi" is the last min(10, 6) = 6 characters to the left of the cursor.
    }

}

/**
 * Your TextEditor object will be instantiated and called as such:
 * TextEditor obj = new TextEditor();
 * obj.addText(text);
 * int param_2 = obj.deleteText(k);
 * String param_3 = obj.cursorLeft(k);
 * String param_4 = obj.cursorRight(k);
 */
