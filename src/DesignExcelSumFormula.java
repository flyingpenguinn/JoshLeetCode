import crap.Crap;

import java.util.ArrayList;
import java.util.List;

/*
LC#631
Your task is to design the basic function of Excel and implement the function of sum formula. Specifically, you need to implement the following functions:

Excel(int H, char W): This is the constructor. The inputs represents the height and width of the Excel form. H is a positive integer, range from 1 to 26. It represents the height. W is a character range from 'A' to 'Z'. It represents that the width is the number of characters from 'A' to W. The Excel form content is represented by a height * width 2D integer array C, it should be initialized to zero. You should assume that the first row of C starts from 1, and the first column of C starts from 'A'.


void Set(int row, char column, int val): Change the value at C(row, column) to be val.


int Get(int row, char column): Return the value at C(row, column).


int Sum(int row, char column, List of Strings : numbers): This function calculate and set the value at C(row, column), where the value should be the sum of cells represented by numbers. This function return the sum result at C(row, column). This sum formula should exist until this cell is overlapped by another value or another sum formula.

numbers is a list of strings that each string represent a cell or a range of cells. If the string represent a single cell, then it has the following format : ColRow. For example, "F7" represents the cell at (7, F).

If the string represent a range of cells, then it has the following format : ColRow1:ColRow2. The range will always be a rectangle, and ColRow1 represent the position of the top-left cell, and ColRow2 represents the position of the bottom-right cell.


Example 1:
Excel(3,"C");
// construct a 3*3 2D array with all zero.
//   A B C
// 1 0 0 0
// 2 0 0 0
// 3 0 0 0

Set(1, "A", 2);
// set C(1,"A") to be 2.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 0

Sum(3, "C", ["A1", "A1:B2"]);
// set C(3,"C") to be the sum of value at C(1,"A") and the values sum of the rectangle range whose top-left cell is C(1,"A") and bottom-right cell is C(2,"B"). Return 4.
//   A B C
// 1 2 0 0
// 2 0 0 0
// 3 0 0 4

Set(2, "B", 2);
// set C(2,"B") to be 2. Note C(3, "C") should also be changed.
//   A B C
// 1 2 0 0
// 2 0 2 0
// 3 0 0 6
Note:
You could assume that there won't be any circular sum reference. For example, A1 = sum(B1) and B1 = sum(A1).
The test cases are using double-quotes to represent a character.
Please remember to RESET your class variables declared in class Excel, as static/class variables are persisted across multiple test cases. Please see here for more details.
 */
public class DesignExcelSumFormula {
    // the oo way to model expression as value or pointer correctly
    static class Excel {

        interface Expression {
            int value();
        }

        class Value implements Expression {
            int v = 0;

            public Value(int v) {
                this.v = v;
            }

            @Override
            public int value() {
                return v;
            }
        }

        class Pointer implements Expression {
            List<int[]> list = new ArrayList<>();
            Expression[][] book = null;

            public Pointer(Expression[][] book) {
                this.book = book;
            }

            public void add(int[] hw) {
                list.add(hw);
            }

            @Override
            public int value() {
                int sum = 0;
                for (int[] l : list) {
                    sum += doget(l[0], l[1]);
                }
                return sum;
            }
        }

        Expression[][] book;

        public Excel(int h, char w) {
            book = new Expression[h + 1][w - 'A' + 1];
        }

        public void set(int r, char c, int v) {
            book[r][c - 'A'] = new Value(v);
        }


        public int get(int r, char c) {
            return doget(r, c - 'A');
        }

        private int doget(int r, int c) {
            Expression cell = book[r][c];
            return cell == null ? 0 : cell.value();
        }

        public int sum(int r, char c, String[] strs) {
            Pointer p = new Pointer(book);
            for (String cell : strs) {
                String[] sp = cell.split(":");
                if (sp.length == 1) {
                    int[] hw = parse(sp[0]);
                    p.add(hw);
                } else {
                    int[] hws = parse(sp[0]);
                    int[] hwe = parse(sp[1]);
                    for (int i = hws[0]; i <= hwe[0]; i++) {
                        for (int j = hws[1]; j <= hwe[1]; j++) {
                            p.add(new int[]{i, j});
                        }
                    }
                }
            }
            book[r][c - 'A'] = p;
            return p.value();
        }

        private int[] parse(String sp) {
            int w = sp.charAt(0) - 'A';
            int h = Integer.valueOf(sp.substring(1, sp.length())).intValue();
            return new int[]{h, w};
        }
    }
}
