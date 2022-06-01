public class ApplyDiscountPrices {
    public String discountPrices(String sentence, int discount) {
        String[] ss = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s: ss){
            if(s.startsWith("$")){
                String cs = s.substring(1);
                try{
                    Double db = Double.parseDouble(cs);
                    db *= (1.0-discount/100.0);
                    sb.append("$"+String.format("%.2f",db));
                    sb.append(" ");
                }catch(Exception e){
                    sb.append(s);
                    sb.append(" ");
                }
            }else{
                sb.append(s);
                sb.append(" ");
            }
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }
}
