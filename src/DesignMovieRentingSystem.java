import java.util.*;
import javafx.util.Pair;

public class DesignMovieRentingSystem {
    static class MovieRentingSystem {
        static class Sm{
            int shop;
            int movie;
            public Sm(int shop, int movie){
                this.shop = shop;
                this.movie = movie;
            }

            @Override
            public boolean equals(Object o) {
                if (this == o) return true;
                if (o == null || getClass() != o.getClass()) return false;
                Sm sm = (Sm) o;
                return shop == sm.shop &&
                        movie == sm.movie;
            }

            @Override
            public int hashCode() {
                return Objects.hash(shop, movie);
            }
        }

        private final Map<Sm, Integer> smp = new HashMap<>(); // sm to price

        class SmComparator implements Comparator<Sm>{
            @Override public int compare(Sm x, Sm y){
                if(!smp.get(x).equals(smp.get(y))){
                    // hash map results can't be using equals!
                    return Integer.compare(smp.get(x), smp.get(y));
                }else if(x.shop != y.shop){
                    return Integer.compare(x.shop, y.shop);
                }else{
                    return Integer.compare(x.movie, y.movie);
                }
            }
        }

       private final Map<Integer, TreeSet<Sm>> avail = new HashMap<>();
        private final TreeSet<Sm> rented = new TreeSet<>(new SmComparator());


        public MovieRentingSystem(int n, int[][] entries) {
            for(int[] e: entries){
                int shop = e[0];
                int movie = e[1];
                int price = e[2];
                Sm ent = new Sm(shop, movie);
                smp.put(ent, price);
            }
            for(int[] e: entries){
                int shop = e[0];
                int movie = e[1];
                Sm ent = new Sm(shop, movie);
                avail.computeIfAbsent(movie, k-> new TreeSet<>(new SmComparator())).add(ent);
            }
        }

        public List<Integer> search(int movie) {
            TreeSet<Sm> avails = avail.get(movie);
            List<Integer> res = new ArrayList<>();
            if(avails!= null){
                for(Sm a: avails){
                    res.add(a.shop);
                    if(res.size()==5){
                        break;
                    }
                }
            }
            return res;
        }

        public void rent(int shop, int movie) {
            Sm ent = new Sm(shop, movie);
            avail.get(movie).remove(ent);
            rented.add(ent);
        }

        public void drop(int shop, int movie) {
            Sm ent = new Sm(shop, movie);
            rented.remove(ent);
            avail.computeIfAbsent(movie, k-> new TreeSet<>(new SmComparator())).add(ent);
        }

        public List<List<Integer>> report() {
            List<List<Integer>> res = new ArrayList<>();
            for(Sm s: rented){
                List<Integer> ans = new ArrayList<>();
                ans.add(s.shop);
                ans.add(s.movie);
                res.add(ans);
                if(res.size()==5){
                    break;
                }
            }
            return res;
        }
    }
}
