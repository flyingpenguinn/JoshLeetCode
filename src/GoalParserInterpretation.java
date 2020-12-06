public class GoalParserInterpretation {
    public String interpret(String command) {
        String res = "";
        int i = 0;
        while (i < command.length()) {
            if (command.startsWith("G", i)) {
                res += "G";
                i++;
            } else if (command.startsWith("()", i)) {
                res += "o";
                i += 2;
            } else {
                res += "al";
                i += 4;
            }
        }
        return res;
    }
}
