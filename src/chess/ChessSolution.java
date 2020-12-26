package chess;

/*
 * Click `Run` to execute the snippet below!
 */

/*
 * To execute Java, please define "static void main" on a class
 * named Solution.
 *
 * If you need more classes, simply define them inline.
 */


class ChessSolution {
    // telling the first step in a chess puzzle. if we want to know later moves we will have to input the board at that time...
    private final int[][] RDIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    private final int[][] BDIRS = {{-1, 1}, {-1, -1}, {1, -1}, {1, 1}};
    private final int[][] KDIRS = concat(RDIRS, BDIRS);
    private final int[][] NDIRS = {{-1, 2}, {-1, -2}, {1, -2}, {1, 2}, {-2, 1}, {-2, -1}, {2, -1}, {2, 1}};
    private final int BOARD = 8;

    private boolean canmove(char[][] a, int i, int j, int turn) {
        // turn==0 goes lower case,
        // turn==1 goes upper case
        char c = a[i][j];
        if (turn == 0 && Character.isLowerCase(c)) {
            return true;
        } else return turn == 1 && Character.isUpperCase(c);
    }

    public boolean solve(char[][] a, int t, int depth, int limit) {

        boolean check1 = checkmate(a, 1);
        if (check1 && t == 0) {
            return true;
        }

        if (depth == limit * 2) {
            return false;
        }
        int m = a.length;
        int n = a[0].length;
        boolean nonkingmovable = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canmove(a, i, j, t)) {
                    char old = a[i][j];

                    for (int ni = 0; ni < m; ni++) {
                        for (int nj = 0; nj < n; nj++) {
                            if (ni == i && nj == j) {
                                continue;
                            }
                            if (sameturn(a[ni][nj], old)) {
                                continue;
                            }
                            if (Character.toLowerCase(old) == 'k' && attacked(a, ni, nj, t)) {
                                continue;
                            }
                            if (moveorattack(a, ni, nj, i, j, t, true)) {

                                if (Character.toLowerCase(old) != 'k') {
                                    nonkingmovable = true;
                                }
                                char oldn = a[ni][nj];
                                a[ni][nj] = upgrade(ni, old, t);
                                a[i][j] = '#';

                                if (t == 0 && checkmate(a, 0)) {
                                    a[ni][nj] = oldn;
                                    a[i][j] = old;
                                    continue;
                                }
                                // found a place to move: can't be attacked, and won't checkmate
                                String toadd = old + "-> (" + makemove(ni, nj) + ")";
                                boolean cur = solve(a, t ^ 1, depth + 1, limit);
                                a[ni][nj] = oldn;
                                a[i][j] = old;
                                if (t == 1 && !cur) {
                                    return false;

                                } else if (cur && t == 0) {
                                    if (depth == 0) {
                                        // we can only output the first move because later ones may vary. it's a tree rather than a list of string
                                        System.out.println(toadd);
                                    }
                                    return true;
                                }
                            }
                        }
                    }

                }

            }
        }
        if (t == 1) {
            // other than king nothing is movable, we must check if we checkmate. otherwise it's a draw
            if (!nonkingmovable) {
                return check1;
            }
            // otherwise after movable things move it's a checkmate, we return true
            return true;
        }
        return false;
    }

    private boolean checkmate(char[][] a, int turn) {
        int ki = -1;
        int kj = -1;
        int m = a.length;
        int n = a[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (canmove(a, i, j, turn)) {
                    char c = Character.toLowerCase(a[i][j]);
                    if (c == 'k') {
                        ki = i;
                        kj = j;
                        break;
                    }
                }
                if(ki!= -1){
                    break;
                }
            }
        }
        return attacked(a, ki, kj, turn);
    }


    private char upgrade(int ni, char old, int t) {
        if (ni == 0 && Character.toLowerCase(old) == 'p') {
            return t == 0 ? 'q' : 'Q';
        }
        return old;
    }

    private boolean sameturn(char c, char d) {
        return (Character.isLowerCase(c) && Character.isLowerCase(d)) || (Character.isUpperCase(c) && Character.isUpperCase(d));
    }

    private String makemove(int ni, int nj) {
        return (char) ('A' + nj) + "," + (8 - ni);
    }


    private boolean attacked(char[][] a, int i, int j, int t) {
        // whether piece at i, j can be attacked by sth in ot turn
        int ot = t ^ 1;
        int m = a.length;
        int n = a[0].length;
        for (int oi = 0; oi < m; oi++) {
            for (int oj = 0; oj < n; oj++) {
                if (!canmove(a, oi, oj, ot)) {
                    // piece not of type = ot
                    continue;
                }
// some other turn piececan attack this place
                if (moveorattack(a, i, j, oi, oj, ot, false)) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean moveorattack(char[][] a, int i, int j, int oi, int oj, int ot, boolean ismove) {
        // whether piece at oi/oj can move to or attack i, j pos
        // for pawn move and attack are different. for all others it's the same

        char op = Character.toLowerCase(a[oi][oj]);
        if (op == 'p') {
            int pdir = ot == 0 ? -1 : 1;
            if (ismove) {
                // rook move forward
                if (a[i][j] == '#') {
                    return (i == oi + pdir && j == oj);
                } else {
                    return attackedbyrook(i, j, oi, oj, pdir);
                }
            } else {
                // but attack diagonally
                return attackedbyrook(i, j, oi, oj, pdir);
            }
        } else if (op == 'n') {
            // can be attacked by a night
            return attackedbynight(i, j, oi, oj);

        } else if (op == 'r') {
            // can be attacked by a rook
            return attackedbyrook(a, i, j, oi, oj);
        } else if (op == 'b') {
            return attackedbybishop(a, i, j, oi, oj);
        } else if (op == 'q') {
            return attackedbyqueen(a, i, j, oi, oj);
        } else if (op == 'k') {
            return attackedbyking(a, i, j, oi, oj);
        }
        return false;
    }

    private boolean attackedbyrook(int i, int j, int oi, int oj, int pdir) {
        return i == oi + pdir && (j == oj - 1 || j == oj + 1);
    }

    private boolean attackedbyqueen(char[][] a, int i, int j, int oi, int oj) {
        // queen is rook + bishop
        return attackedbyrook(a, i, j, oi, oj) || attackedbybishop(a, i, j, oi, oj);
    }

    private boolean attackedbyking(char[][] a, int i, int j, int oi, int oj) {
        // king is rook or bishop with one move
        for (int[] d : KDIRS) {
            if (path(a, i, j, oi, oj, d[0], d[1], 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean attackedbynight(int i, int j, int oi, int oj) {
        for (int[] d : NDIRS) {
            int noi = oi + d[0];
            int noj = oj + d[1];
            if (noi == i && noj == j) {
                return true;
            }
        }
        return false;
    }

    private int[][] concat(int[][] a, int[][] b) {
        int[][] c = new int[a.length + b.length][2];
        int ci = 0;
        for (int[] ai : a) {
            c[ci++] = ai;
        }
        for (int[] bi : b) {
            c[ci++] = bi;
        }
        return c;
    }

    private boolean path(char[][] a, int i, int j, int oi, int oj, int di, int dj, int limit) {
        int m = a.length;
        int n = a[0].length;
        int moved = 0;
        for (int ki = oi + di, kj = oj + dj; ki < m && kj < n && ki >= 0 && kj >= 0; ki += di, kj += dj) {
            if (moved == limit) {
                break;
            }
            moved++;
            int state = state(a, ki, kj, i, j);
            if (state == 1) {
                // now meet this i, j an we know between them it's empty space
                return true;
            } else if (state == 2) {
                // bumped into sth else
                return false;
            }
        }
        return false;
    }


    private boolean attackedbybishop(char[][] a, int i, int j, int oi, int oj) {
        for (int[] d : BDIRS) {
            if (d[0] * d[1] == 1 && i - j != oi - oj) {
                // 2,3-> 3,4. or 1,2
                continue;
            }
            if (d[0] * d[1] == -1 && i + j != oi + oj) {
                // 2,3-> 1,4 or 3,2
                continue;
            }
            if (path(a, i, j, oi, oj, d[0], d[1], BOARD)) {
                return true;
            }
        }
        return false;
    }

    private boolean attackedbyrook(char[][] a, int i, int j, int oi, int oj) {
        if (oi != i && oj != j) {
            return false;
        }
        for (int[] d : RDIRS) {
            if (d[0] == 0 && i != oi) {
                continue;
            }
            if (d[1] == 0 && j != oj) {
                continue;
            }
            if (path(a, i, j, oi, oj, d[0], d[1], BOARD)) {
                return true;
            }
        }
        return false;
    }


    private int state(char[][] a, int ki, int kj, int i, int j) {
        // if we bump into anything in moving but we are not at i or j yet then we can't move onto i, j
        if (ki == i && kj == j) {
            return 1;
        } else if (a[ki][kj] != '#') {
            return 2;
        }
        return 0;
    }

    public static void main(String[] args) {
        String[][] inputs = {
                {"#####K##", "##R##P##", "#####p##", "####n###", "########", "#QP#####", "#p##r###", "#k####r#"}, // 4075 n-> (D,7)
                {"K###N###", "p#######", "npk###n#", "#p######", "########", "########", "########", "########"}, // 417  p-> (B,7)
                {"####b###", "####p###", "###PKp##", "###B#r##", "##R#n#P#", "##P#q#b#", "####n###", "####k###"}, // club problem  q-> (C,5)

                {"###n####", "KPk#####", "pn######", "#p######", "########", "########", "########", "########"}, // 418  n-> (C,8)

                {"####b###", "####p###", "###PKp##", "##RB#r##", "####n#P#", "##P###b#", "####n###", "####k###"}, // club problem after black rook taking queen  n-> (D,4)

                {"R####B#R", "PPPBKbPP", "Q#N###N#", "#####P##", "##np####", "#qp#####", "pp###ppp", "rnb##rk#"},  //1054  b-> (G,5)
                {"RN####NR", "PPPQ##BK", "#######P", "#####B#p", "####nbq#", "###b####", "ppp###p#", "r###k##r"}, // 4069  q-> (G,6)
                {"R####R#K", "P####P#P", "#PP##p##", "Q#P#Pn#P", "##p#####", "p#p##p##", "########", "######rk"}, // 4087 n-> (H,6)


        };
        int[] limits = {
                3, 2, 2, 2, 2, 2, 3, 3
        };
        for (int k = 0; k < inputs.length; k++) {
            String[] input = inputs[k];
            ChessSolution sol = new ChessSolution();
            int m = input.length;
            int n = input[0].length();
            char[][] a = new char[m][n];
            for (int i = 0; i < m; i++) {
                a[i] = input[i].toCharArray();
            }
            System.out.println("problem " + k);
            sol.solve(a, 0, 0, limits[k]);
        }
    }
}

