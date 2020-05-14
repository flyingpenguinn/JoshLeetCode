package concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

interface HtmlParser {
    // Return a list of all urls from a webpage of given url.
    // This is a blocking call, that means it will do HTTP request and return when this request is finished.
    public List<String> getUrls(String url);
}

class HtmlParserImpl implements HtmlParser {

    @Override
    public List<String> getUrls(String url) {
        try {
            Thread.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (url.equals("http://news.yahoo.com/news/topics/")) {
            return List.of("http://news.yahoo.com", "http://news.yahoo.com/news", "http://news.google.com");
        }
        if (url.equals("http://news.yahoo.com/news")) {
            return List.of("http://news.google.com");
        }
        if (url.equals("http://news.yahoo.com")) {
            return List.of("http://news.yahoo.com/us");
        }
        return new ArrayList<>();
    }
}

public class WebCrawlerMultiThread {
    public static void main(String[] args) {
        System.out.println(new WebCrawler().crawl("http://news.yahoo.com/news/topics/", new HtmlParserImpl()));
    }
}

class WebCrawler {

    private ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();

    class Crawler implements Runnable {
        // only craw one url
        String url;

        public Crawler(String url) {
            this.url = url;
        }

        @Override
        public void run() {
            map.putIfAbsent(url, true);
            //     System.out.println("crawling..." + url + " " + Thread.currentThread());
            List<String> urls = htmlParser.getUrls(url);
            List<Thread> ts = new ArrayList<>();
            for (String u : urls) {
                String uhost = gethost(u);
                if (uhost.equals(host) && !map.containsKey(u)) {
                    Thread t = new Thread(new Crawler(u));
                    ts.add(t);
                    t.start();
                }
            }
            for (Thread t : ts) {
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // assuming thread safe
    private HtmlParser htmlParser;

    private String host = "";
    private String protocol = "http://";

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.host = gethost(startUrl);
        this.htmlParser = htmlParser;

        Thread t = new Thread(new Crawler(startUrl));
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(map.keySet());
    }

    private String gethost(String startUrl) {
        int start = protocol.length();
        int end = startUrl.indexOf("/", start);
        return startUrl.substring(start, end == -1 ? startUrl.length() : end);
    }
}

// TLE for no reason
class WebCrawlerThreadPool {

    private ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
    // assuming thread safe
    private HtmlParser htmlParser;

    private String host = "";
    private String protocol = "http://";

    class CrawlAction implements Callable<Integer> {
        String url;

        public CrawlAction(String url) {
            this.url = url;
        }

        @Override
        public Integer call() throws Exception {
            //    System.out.println(url);
            List<String> urls = htmlParser.getUrls(url);
            List<CrawlAction> actions = new ArrayList<>();
            for (String u : urls) {
                if (map.containsKey(u)) {
                    continue;
                }

                if (u.startsWith(host)) {
                    map.putIfAbsent(u, true);
                    actions.add(new CrawlAction(u));
                }
            }
            pool.invokeAll(actions);
            return 0;
        }
    }

    ExecutorService pool = Executors.newFixedThreadPool(100);

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.pool = ForkJoinPool.commonPool();
        this.host = gethost(startUrl);
        this.htmlParser = htmlParser;
        map.put(startUrl, true);
        List<CrawlAction> actions = new ArrayList<>();
        actions.add(new CrawlAction(startUrl));
        try {
            pool.invokeAll(actions);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ArrayList<>(map.keySet());
    }

    private String gethost(String startUrl) {
        int start = protocol.length();
        int end = startUrl.indexOf("/", start);
        return startUrl.substring(0, end == -1 ? startUrl.length() : end);
    }
}

// TLE for no reason
class WebCrawlerForkJoin {

    private ConcurrentHashMap<String, Boolean> map = new ConcurrentHashMap<>();
    // assuming thread safe
    private HtmlParser htmlParser;

    private String host = "";
    private String protocol = "http://";

    class CrawlAction extends RecursiveAction {
        String url;

        public CrawlAction(String url) {
            this.url = url;
        }

        @Override
        protected void compute() {
            //    System.out.println(url);
            List<String> urls = htmlParser.getUrls(url);
            List<CrawlAction> actions = new ArrayList<>();
            for (String u : urls) {
                if (map.containsKey(u)) {
                    continue;
                }
                if (u.startsWith(host)) {
                    map.putIfAbsent(u, true);
                    actions.add(new CrawlAction(u));
                }
            }
            invokeAll(actions);
        }
    }

    ForkJoinPool pool = ForkJoinPool.commonPool();

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        this.pool = ForkJoinPool.commonPool();
        this.host = gethost(startUrl);
        this.htmlParser = htmlParser;
        map.put(startUrl, true);
        pool.invoke(new CrawlAction(startUrl));
        return new ArrayList<>(map.keySet());
    }

    private String gethost(String startUrl) {
        int start = protocol.length();
        int end = startUrl.indexOf("/", start);
        return startUrl.substring(0, end == -1 ? startUrl.length() : end);
    }
}