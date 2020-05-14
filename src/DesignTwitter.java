import java.util.*;

/*
LC#355
Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to see the 10 most recent tweets in the user's news feed. Your design should support the following methods:

postTweet(userId, tweetId): Compose a new tweet.
getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent.
follow(followerId, followeeId): Follower follows a followee.
unfollow(followerId, followeeId): Follower unfollows a followee.
Example:

Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
twitter.getNewsFeed(1);

// User 1 follows user 2.
twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
twitter.getNewsFeed(1);

// User 1 unfollows user 2.
twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
twitter.getNewsFeed(1);
 */
public class DesignTwitter {
    public static void main(String[] args) {
        Twitter twitter = new Twitter();

// User 1 posts a new tweet (id = 5).
        twitter.postTweet(1, 5);

// User 1's news feed should return a list with 1 tweet id -> [5].
        System.out.println(twitter.getNewsFeed(1));

// User 1 follows user 2.
        twitter.follow(1, 2);

// User 2 posts a new tweet (id = 6).
        twitter.postTweet(2, 6);

// User 1's news feed should return a list with 2 tweet ids -> [6, 5].
// Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
        System.out.println(twitter.getNewsFeed(1));

// User 1 unfollows user 2.
        twitter.unfollow(1, 2);

// User 1's news feed should return a list with 1 tweet id -> [5],
// since user 1 is no longer following user 2.
        System.out.println(twitter.getNewsFeed(1));
    }
}

class Twitter {
    int k = 10;
    Map<Integer, LinkedList<int[]>> tm = new HashMap<>();
    Map<Integer, Set<Integer>> fm = new HashMap<>();

    public Twitter() {
    }

    int seq = 0;

    public void postTweet(int userId, int tweetId) {
        LinkedList<int[]> cur = tm.getOrDefault(userId, new LinkedList<>());
        cur.add(new int[]{seq++, tweetId});
        tm.put(userId, cur);
        if (cur.size() > k) {
            cur.poll();
        }
    }

    public List<Integer> getNewsFeed(int userId) {
        // must be a min heap if we want to get rid of old tweets!
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        Set<Integer> other = fm.getOrDefault(userId, new HashSet<>());
        other.add(userId);
        for (int f : other) {
            LinkedList<int[]> tweets = tm.getOrDefault(f, new LinkedList<>());
            ListIterator<int[]> it = tweets.listIterator(tweets.size());
            int rem = k;
            while (it.hasPrevious() && rem > 0) {
                int[] tweet = it.previous();
                pq.offer(tweet);
                if (pq.size() > k) {
                    pq.poll();
                }
                rem--;
            }
        }
        List<Integer> r = new ArrayList<>();
        while (!pq.isEmpty()) {
            r.add(pq.poll()[1]);
        }
        Collections.reverse(r);
        return r;
    }


    public void follow(int followerId, int followeeId) {
        Set<Integer> set = fm.getOrDefault(followerId, new HashSet<>());
        set.add(followeeId);
        fm.put(followerId, set);
    }


    public void unfollow(int followerId, int followeeId) {
        Set<Integer> set = fm.getOrDefault(followerId, new HashSet<>());
        if (!set.isEmpty()) {
            set.remove(followeeId);
        }
        fm.put(followerId, set);
    }
}