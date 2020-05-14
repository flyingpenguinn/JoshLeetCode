import java.util.Base64;

public class EncodeAndDecodeTinyUrl {

    private Base64.Encoder encoder = Base64.getEncoder();
    private Base64.Decoder decoder = Base64.getDecoder();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        byte[] encoded = encoder.encode(longUrl.getBytes());
        String rt = new String(encoded);
        return rt;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return new String(decoder.decode(shortUrl.getBytes()));
    }

    public static void main(String[] args) {
        EncodeAndDecodeTinyUrl eadt = new EncodeAndDecodeTinyUrl();
        String result = eadt.decode(eadt.encode("https://leetcode.com/problems/design-tinyurl"));
        System.out.println("===result====" + result);
    }
}
