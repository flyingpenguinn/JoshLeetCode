import java.util.*;

/*
LC#1348
Implement the class TweetCounts that supports two methods:

1. recordTweet(string tweetName, int time)

Stores the tweetName at the recorded time (in seconds).
2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, int endTime)

Returns the total number of occurrences for the given tweetName per minute, hour, or day (depending on freq) starting from the startTime (in seconds) and ending at the endTime (in seconds).
freq is always minute, hour or day, representing the time interval to get the total number of occurrences for the given tweetName.
The first time interval always starts from the startTime, so the time intervals are [startTime, startTime + delta*1>,  [startTime + delta*1, startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)> for some non-negative number i and delta (which depends on freq).


Example:

Input
["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
[[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]

Output
[null,null,null,null,[2],[2,1],null,[4]]

Explanation
TweetCounts tweetCounts = new TweetCounts();
tweetCounts.recordTweet("tweet3", 0);
tweetCounts.recordTweet("tweet3", 60);
tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.


Constraints:

There will be at most 10000 operations considering both recordTweet and getTweetCountsPerFrequency.
0 <= time, startTime, endTime <= 10^9
0 <= endTime - startTime <= 10^4
 */
public class TweetCountsPerFreq {
    // use buckets to simplify the projection!
    static class TweetCounts {
        Map<String, TreeMap<Integer, Integer>> map = new HashMap<>();

        public TweetCounts() {
            map = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            TreeMap<Integer, Integer> tm = map.getOrDefault(tweetName, new TreeMap<>());
            tm.put(time, tm.getOrDefault(time, 0) + 1);
            map.put(tweetName, tm);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            TreeMap<Integer, Integer> tm = map.getOrDefault(tweetName, new TreeMap<>());
            int delta = 0;
            if (freq.equals("minute")) {
                delta = 60;
            } else if (freq.equals("hour")) {
                delta = 3600;
            } else {
                delta = 3600 * 24;
            }
            List<Integer> r = new ArrayList<>();
            int i = 0;
            int[] buckets = new int[(endTime - startTime) / delta + 1];
            for (int k : tm.subMap(startTime, true, endTime, true).keySet()) {
                int index = (k - startTime) / delta;
                buckets[index] += tm.get(k);
            }
            for (int b : buckets) {
                r.add(b);
            }
            return r;
        }
    }

    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("t0", 857105800);
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "t0", 255428777, 255438544));

        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 10);                             // All tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59)); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60)); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
        tweetCounts.recordTweet("tweet3", 120);                            // All tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210));  // return [4]. The frequency is per hour (3600 seconds), so there is one interval of time: 1) [0, 211> - > 4 tweets.


        tweetCounts.recordTweet("tweet0", 13);
        tweetCounts.recordTweet("tweet1", 16);
        tweetCounts.recordTweet("tweet2", 12);
        tweetCounts.recordTweet("tweet3", 18);
        tweetCounts.recordTweet("tweet4", 82);
        tweetCounts.recordTweet("tweet3", 89);
        System.out.println(tweetCounts.getTweetCountsPerFrequency("day", "tweet0", 89, 9471)); // return [2]. The frequency is per minute (60 seconds), so there is one interval of time: 1) [0, 60> - > 2 tweets.
        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 13, 4024)); // return [2, 1]. The frequency is per minute (60 seconds), so there are two intervals of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.

    }
}
