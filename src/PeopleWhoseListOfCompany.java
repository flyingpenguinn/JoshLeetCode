import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
LC#1452
Given the array favoriteCompanies where favoriteCompanies[i] is the list of favorites companies for the ith person (indexed from 0).

Return the indices of people whose list of favorite companies is not a subset of any other list of favorites companies. You must return the indices in increasing order.



Example 1:

Input: favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
Output: [0,1,4]
Explanation:
Person with index=2 has favoriteCompanies[2]=["google","facebook"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] corresponding to the person with index 0.
Person with index=3 has favoriteCompanies[3]=["google"] which is a subset of favoriteCompanies[0]=["leetcode","google","facebook"] and favoriteCompanies[1]=["google","microsoft"].
Other lists of favorite companies are not a subset of another list, therefore, the answer is [0,1,4].
Example 2:

Input: favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
Output: [0,1]
Explanation: In this case favoriteCompanies[2]=["facebook","google"] is a subset of favoriteCompanies[0]=["leetcode","google","facebook"], therefore, the answer is [0,1].
Example 3:

Input: favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
Output: [0,1,2,3]


Constraints:

1 <= favoriteCompanies.length <= 100
1 <= favoriteCompanies[i].length <= 500
1 <= favoriteCompanies[i][j].length <= 20
All strings in favoriteCompanies[i] are distinct.
All lists of favorite companies are distinct, that is, If we sort alphabetically each list then favoriteCompanies[i] != favoriteCompanies[j].
All strings consist of lowercase English letters only.
 */
public class PeopleWhoseListOfCompany {
    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        List<Set<String>> list = new ArrayList<>();
        for (List<String> f : favoriteCompanies) {
            list.add(new HashSet<>(f));
        }
        List<Integer> r = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Set<String> cur = list.get(i);
            boolean out = false;
            for (int j = 0; j < list.size(); j++) {
                if (j == i) {
                    continue;
                }
                if (issubset(cur, list.get(j))) {
                    out = true;
                    break;
                }
            }
            if (!out) {
                r.add(i);
            }
        }
        return r;
    }

    private boolean issubset(Set<String> s1, Set<String> s2) {
        if (s1.size() >= s2.size()) {
            return false;
        }
        for (String s : s1) {
            if (!s2.contains(s)) {
                return false;
            }
        }
        return true;
    }
}
