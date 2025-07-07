import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponCodeValidator {
    static class CodeAndBi {
        String code;
        String bi;

        public CodeAndBi(String code, String bi) {
            this.code = code;
            this.bi = bi;
        }
    }

    private final Map<String, Integer> validBis = new HashMap<>();

    {
        validBis.put("electronics", 1);
        validBis.put("grocery", 2);
        validBis.put("pharmacy", 3);
        validBis.put("restaurant", 4);
    }

    public List<String> validateCoupons(String[] code, String[] b, boolean[] isa) {
        int cn = code.length;
        List<CodeAndBi> res = new ArrayList<>();
        for (int i = 0; i < cn; ++i) {
            if (!isa[i]) {
                continue;
            }
            if (!validBis.containsKey(b[i])) {
                continue;
            }
            if (!valid(code[i])) {
                continue;
            }
            res.add(new CodeAndBi(code[i], b[i]));
        }
        res.sort((x, y) -> {
            if (!x.bi.equals(y.bi)) {
                int bindexx = validBis.get(x.bi);
                int bindexy = validBis.get(y.bi);
                return Integer.compare(bindexx, bindexy);
            } else {
                return x.code.compareTo(y.code);
            }
        });
        List<String> cres = new ArrayList<>();
        for(CodeAndBi cb: res){
            cres.add(cb.code);
        }
        return cres;
    }

    private boolean valid(String s) {
        if(s.isEmpty()){
            return false;
        }
        for(char c: s.toCharArray()){
            if(Character.isLetterOrDigit(c)){
                continue;
            }
            if(c=='_'){
                continue;
            }
            return false;
        }
        return true;
    }
}
