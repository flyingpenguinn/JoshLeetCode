package concurrency;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
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
    // use fork join!
    private String host = "";

    private ConcurrentHashMap<String, Boolean> found = new ConcurrentHashMap<>();

    class CrawlAction extends RecursiveAction {

        private String url;
        private HtmlParser htmlParser;

        public CrawlAction(String url, HtmlParser htmlParser) {
            this.url = url;
            this.htmlParser = htmlParser;
        }

        @Override
        protected void compute() {
            List<CrawlAction> newTasks = new ArrayList<>();
            List<String> toCrawl = htmlParser.getUrls(url);
            if (toCrawl != null) {
                for (String newUrl : toCrawl) {
                    try {
                        URL u = new URL(newUrl);
                        if (!u.getHost().equalsIgnoreCase(host)) {
                            continue;
                        }
                    } catch (MalformedURLException e) {
                        // log errors
                    }
                    Boolean ov = found.put(newUrl, true);
                    // must use put to check otherwise two ifs may result in race condition
                    if (ov == null) {
                        newTasks.add(new CrawlAction(newUrl, htmlParser));
                    }
                }
            }
            invokeAll(newTasks);
        }
    }

    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        try {
            URL url = new URL(startUrl);
            host = url.getHost();
        } catch (
                MalformedURLException e) {
            throw new RuntimeException(e);
        }
        found.put(startUrl, true);
        ForkJoinPool.commonPool().invoke(new CrawlAction(startUrl, htmlParser));
        return new ArrayList<>(found.keySet());
    }
}