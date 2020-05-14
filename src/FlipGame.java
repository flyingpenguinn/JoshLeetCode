import java.util.ArrayList;
import java.util.List;
/*
LC#293
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

Example:

Input: s = "++++"
Output:
[
  "--++",
  "+--+",
  "++--"
]
Note: If there is no valid move, return an empty list [].
 */
public class FlipGame {
    public List<String> generatePossibleNextMoves(String s) {
        char[] cs=s.toCharArray();
        int n=cs.length;
        List<String> r=new ArrayList<>();
        for(int i=0;i+1<n;i++){
            if(cs[i]=='+' && cs[i+1]=='+'){
                cs[i]='-';
                cs[i+1]='-';
                r.add(new String(cs));
                cs[i]='+';
                cs[i+1]='+';
            }
        }
        return r;
    }
}

