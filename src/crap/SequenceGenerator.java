package crap;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class SequenceGenerator {
    // limit is the overall number
    // group is odd/even or other division
    // span is the gap between each number in the output
    private List<Integer> generate(int limit, int group, int span) {
        List<Integer> r = new ArrayList<Integer>();
        for (int i = 1; i <= group; i++) {
            for (int j = i; j <= span; j += group) {
                for (int k = j; k <= limit; k += span) {
                    r.add(k);
                    System.out.print(k + ",");
                }
            }
        }
        System.out.println();
        return r;
    }

    public static void main(String[] args) {
        SequenceGenerator sg = new SequenceGenerator();
        check(sg.generate(100, 11, 12), 100);
        check(sg.generate(100, 23, 34), 100);
        check(sg.generate(100000, 23, 34), 100000);
        check(sg.generate(10, 2, 3), 10);
        check(sg.generate(20, 3, 5), 20);
    }

    private static void check(List<Integer> r, int num) {
        verify(r.size(), num);
        verify(r.stream().distinct().collect(toList()).size(), num);
    }

    private static void verify(int size, int num) {
        if (size != num) {
            throw new IllegalStateException();
        }
    }
}
