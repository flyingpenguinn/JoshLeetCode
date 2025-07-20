package base;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
    public static List<Integer> toArrayList(int[] array) {
        List<Integer> li = new ArrayList<>();
        for (int input : array) {
            li.add(input);
            ;
        }
        return li;
    }

    public static List<List<Integer>> toArrayList(int[][] array) {
        List<List<Integer>> r = new ArrayList<>();
        for (int[] input : array) {
            List<Integer> inner = toArrayList(input);
            r.add(inner);
        }
        return r;
    }

    public static List<List<Integer>> readAsList(String arrayStr) {
        int[][] r = read(arrayStr);
        List<List<Integer>> rl = new ArrayList<>();
        for (int[] ri : r) {
            List<Integer> li = new ArrayList<>();
            for (int i : ri) {
                li.add(i);
            }
            rl.add(li);
        }
        return rl;
    }


    public static List<List<Integer>> readAsListUneven(String arrayStr) {
        ArrayList<String> inner = getInnerStrings(arrayStr);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < inner.size(); i++) {
            String[] split = inner.get(i).split(",");
            List<Integer> rr = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                String tm = split[j].trim();
                if (!tm.isEmpty()) {
                    rr.add(Integer.valueOf(tm));
                }

            }
            result.add(rr);
        }
        return result;
    }


    public static List<List<String>> readAsListUnevenString(String arrayStr) {
        ArrayList<String> inner = getInnerStrings(arrayStr);

        List<List<String>> result = new ArrayList<>();
        for (int i = 0; i < inner.size(); i++) {
            String[] split = inner.get(i).split(",");
            List<String> rr = new ArrayList<>();
            for (int j = 0; j < split.length; j++) {
                String tm = split[j].trim();
                rr.add(tm);

            }
            result.add(rr);
        }
        return result;
    }

    public static int[][] read(String arrayStr) {
        arrayStr = arrayStr.trim();
        ArrayList<String> inner = getInnerStrings(arrayStr);


        int[][] result = new int[inner.size()][];
        for (int i = 0; i < result.length; i++) {
            String[] split = inner.get(i).split(",");
            result[i] = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                String v = split[j].trim();
                if (!v.isEmpty()) {
                    result[i][j] = Integer.valueOf(v);
                }
            }
        }
        return result;
    }

    public static long[][] readlong(String arrayStr) {
        arrayStr = arrayStr.trim();
        ArrayList<String> inner = getInnerStrings(arrayStr);


        long[][] result = new long[inner.size()][];
        for (int i = 0; i < result.length; i++) {
            String[] split = inner.get(i).split(",");
            result[i] = new long[split.length];
            for (int j = 0; j < split.length; j++) {
                String v = split[j].trim();
                if (!v.isEmpty()) {
                    result[i][j] = Long.valueOf(v);
                }
            }
        }
        return result;
    }

    public static long[] read1dlong(String arraStr) {
        arraStr = arraStr.trim();
        if (arraStr.startsWith("[")) {
            arraStr = arraStr.substring(1, arraStr.length() - 1);
        }
        String[] str = arraStr.split(",");
        long[] r = new long[str.length];
        for (int i = 0; i < str.length; i++) {
            String v = str[i].trim();
            if (!v.isEmpty()) {
                r[i] = Long.valueOf(v);
            }
        }
        return r;
    }

    public static int[] read1d(String arraStr) {
        arraStr = arraStr.trim();
        if (arraStr.startsWith("[")) {
            arraStr = arraStr.substring(1, arraStr.length() - 1);
        }
        String[] str = arraStr.split(",");
        int[] r = new int[str.length];
        for (int i = 0; i < str.length; i++) {
            String v = str[i].trim();
            if (!v.isEmpty()) {
                r[i] = Integer.valueOf(v);
            }
        }
        return r;
    }

    public static int[][] readUnEven(String arrayStr) {
        ArrayList<String> inner = getInnerStrings(arrayStr);

        int len = inner.get(0).split(",").length;
        int[][] result = new int[inner.size()][];
        for (int i = 0; i < result.length; i++) {
            String[] split = inner.get(i).split(",");
            int[] rr = new int[split.length];
            for (int j = 0; j < split.length; j++) {
                String tm = split[j].trim();
                if (!tm.isEmpty()) {
                    rr[j] = Integer.valueOf(tm);
                } else {
                    rr = new int[0];
                }
            }
            result[i] = rr;
        }
        return result;
    }

    private static ArrayList<String> getInnerStrings(String arrayStr) {
        String arrays = arrayStr.substring(1, arrayStr.length() - 1);
        ArrayList<String> inner = new ArrayList<>();
        int start = 0;
        while (true) {
            int starting = arrays.indexOf("[", start);
            if (starting == -1) {
                break;
            }
            int ending = arrays.indexOf("]", start);
            String chop = arrays.substring(starting + 1, ending);
            inner.add(chop);
            start = ending + 1;
        }
        return inner;
    }

    public static char[][] readAsChar(String arrayStr) {
        ArrayList<String> inner = getInnerStrings(arrayStr);

        int len = inner.get(0).split(",").length;
        char[][] result = new char[inner.size()][len];
        for (int i = 0; i < result.length; i++) {
            String[] split = inner.get(i).split(",");
            for (int j = 0; j < split.length; j++) {
                result[i][j] = split[j].charAt(0);
            }
        }
        return result;
    }


    int[] list2Array(List<Integer> r) {
        int[] ar = new int[r.size()];
        for (int i = 0; i < r.size(); i++) {
            ar[i] = r.get(i);
        }
        return ar;
    }

    List<Integer> array2list(int[] a) {
        List<Integer> r = new ArrayList<>();
        for (int ai : a) {
            r.add(ai);
        }
        return r;
    }

}
