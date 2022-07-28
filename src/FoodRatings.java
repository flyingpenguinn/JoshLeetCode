import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class FoodRatings {

    private static class Food implements Comparable<Food> {
        String name;
        int rating;

        public Food(String name, int rating) {
            this.name = name;
            this.rating = rating;
        }

        @Override
        public int compareTo(Food other) {
            if (this.rating != other.rating) {
                return Integer.compare(other.rating, this.rating);
            } else {
                return this.name.compareTo(other.name);
            }
        }
    }

    private Map<String, TreeSet<Food>> fm = new HashMap<>();
    private Map<String, Integer> rm = new HashMap<>();
    private Map<String, String> tm = new HashMap<>();

    public FoodRatings(String[] foods, String[] cuisines, int[] ratings) {
        int n = foods.length;
        for (int i = 0; i < n; ++i) {
            String type = cuisines[i];
            fm.computeIfAbsent(type, k -> new TreeSet<>()).add(new Food(foods[i], ratings[i]));
            rm.put(foods[i], ratings[i]);
            tm.put(foods[i], type);
        }
    }

    public void changeRating(String food, int newRating) {

        int oldScore = rm.get(food);
        String type = tm.get(food);
        TreeSet<Food> tset = fm.get(type);
        tset.remove(new Food(food, oldScore));
        tset.add(new Food(food, newRating));
        rm.put(food, newRating);

    }

    public String highestRated(String cuisine) {
        return fm.get(cuisine).first().name;
    }
}

/**
 * Your FoodRatings object will be instantiated and called as such:
 * FoodRatings obj = new FoodRatings(foods, cuisines, ratings);
 * obj.changeRating(food,newRating);
 * String param_2 = obj.highestRated(cuisine);
 */

