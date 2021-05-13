package crap;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Basics {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()){
            if(it.next() == 1){
                it.remove();
            }
        }
    }
}
