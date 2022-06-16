import base.ArrayUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckContradictions {

    private Map<String, Map<String, Double>> g = new HashMap<>();

    private Map<String, Double> vm = new HashMap<>();
    private boolean res = false;

    public boolean checkContradictions(List<List<String>> equations, double[] values) {
        for (int i = 0; i < equations.size(); ++i) {
            List<String> eq = equations.get(i);
            String v0 = eq.get(0);
            String v1 = eq.get(1);
            double v = values[i];
            if (g.containsKey(v0) && g.get(v0).containsKey(v1)) {
                double other = g.get(v0).get(v1);
                if (!doubleEq(other, v)) {
                    return true;
                }
            }
            add(v0, v1, v);
            add(v1, v0, 1.0 / v);
        }
        for (String s : g.keySet()) {

            if (vm.getOrDefault(s, 0.0) == 0.0) {

                dfs(s, 1.0);
                if (res) {
                    return true;
                }
            }
        }

        return false;
    }

    private void dfs(String s, double v) {
        if (res) {
            return;
        }

        vm.put(s, v);
        for (String ne : g.getOrDefault(s, new HashMap<>()).keySet()) {

            double nev = g.get(s).get(ne);
            double nv = v / nev;
            double should = vm.getOrDefault(ne, 0.0);

            if (should != 0.0) {

                if (!doubleEq(nv, should)) {
                    res = true;
                    return;
                } else {
                    continue;
                }
            } else {
                // visit even if it's 2
                dfs(ne, nv);
            }
        }
    }

    private boolean doubleEq(double nv, double should) {
        return Math.abs(nv - should) < 1e-4;
    }

    private void add(String v0, String v1, double v) {
        g.computeIfAbsent(v0, k -> new HashMap<>()).put(v1, v);
    }

    public static void main(String[] args) {

        System.out.println(new CheckContradictions().checkContradictions(ArrayUtils.readAsListUnevenString("[[tkyuh,jhvia],[dvt,cavbo],[mae,zwhbd],[fdvah,jzyr],[yodz,lh],[ixcj,fnxqg],[obj,foud],[wjisq,kf],[pgno,cx],[cou,ct],[zwhbd,qqwh],[eqe,nk],[r,e],[gaffq,qrqlc],[qaui,d],[obj,pgpmb],[vzeun,xrggz],[qqwh,fowl],[nk,nir],[jziyw,gbdhd],[fnxqg,oervw],[bx,zwhbd],[elwdm,feuv],[gd,gowfg],[hsgii,lku],[g,fdkr],[cna,sqeun],[jziyw,tkyuh],[jlcaj,ev],[eyfue,zk],[cavbo,tm],[gmsm,ixcj],[czc,cuckh],[ggu,vvbl],[tzmmg,ztxjs],[mkj,xb],[pzcwm,wjisq],[ccjjg,lyyy],[bbkbj,qqwh],[foud,vei],[qsqg,fowl],[ev,gd],[nk,d],[hxvfd,ccjjg],[vz,cwifa],[urcd,n],[oby,jxd],[jxd,veqgp],[fg,kt],[pbow,gnq],[yq,e],[hsgii,yolc],[x,x],[wzqq,oby],[pf,vrjt],[fowl,nir],[knp,xoo],[nsc,tkyuh],[fjgn,gaffq],[qrqlc,lbkxy],[ctygp,i],[xbie,im],[mhm,gqiu],[h,pgpmb],[sckn,nk],[sdy,d],[qqwh,dz],[mae,pzcwm],[vzeun,gbdhd],[gbdhd,h],[mkj,rwyz],[ucnkn,cxixj],[skq,wsmtz],[tdbj,ehep],[cxixj,n],[sqeun,lyyy],[lbkxy,hqakq],[cd,cujil],[anl,dz],[jhvia,elwdm],[oervw,vr],[n,knp],[cd,os],[lig,sxbq],[iad,jziyw],[foud,g],[nk,zzzfs],[qqwh,hsgii],[gmsm,urcd],[mae,foud],[yq,mrqlz],[wdz,gaffq],[wx,iwqii],[kmb,yan],[cuckh,k],[hqakq,obj],[rpj,wci],[cou,szow],[lbkxy,oep],[xyj,ztxjs],[rz,eyfue],[wjisq,e],[eq,fg],[dz,gbdhd],[du,qqwh],[rpyj,tkyuh],[os,kq],[frze,pbow],[jxd,lh],[ztxjs,lyyy],[qrqlc,ggu],[selet,hsgii],[clg,a],[iwqii,kt],[jziyw,gd],[r,dvt],[anl,atd],[ts,qsqg],[mgkiw,c],[yan,mae],[z,skq],[qrqlc,iljli],[qbogh,mhm],[feuv,qxt],[sqeun,wsmtz],[cxixj,fnxqg],[os,vrjt],[ucnkn,qsqg],[qg,vubtz],[veqgp,hodfs],[frze,vz],[e,s],[gqiu,ixcj],[jzyr,mgkiw],[hsgii,hwoi],[g,mgkiw],[jxd,g],[qoqod,jziyw],[k,zhd],[iwqii,ot],[pgno,lku],[eyfue,hqakq],[z,yq],[zwhbd,vybf],[yq,gsogg],[zk,xbie],[ch,tht],[ch,foud],[xrggz,frze],[d,u],[tzmmg,qoqod],[hqakq,iad],[cs,jziyw],[wdz,kt],[ggu,im],[xoo,hqakq],[cke,pbow],[bx,gowfg],[ju,hwoi],[cs,vubtz],[thxp,iad],[ts,yolc],[cna,rpj],[rpyj,gqiu],[xrggz,pq],[f,s],[oep,w],[hsgii,g],[w,lig],[ztxjs,rpyj],[fk,ucnkn],[fowl,zwhbd],[lyyy,vbevu],[i,n],[gsogg,rpj],[fdkr,gsogg],[qsqg,qg],[mkj,d],[nk,hsgii],[pgpmb,vubtz],[vn,zk],[yodz,whpm],[qoqod,qoqod],[x,jlcaj],[l,pgno],[eqe,gqiu],[kq,yq],[pbow,gqiu],[tht,ev],[zwhbd,sxbq],[cxixj,dz],[y,jlcaj],[kmb,gd],[kkpy,jxd],[hxvfd,xr],[zhd,vybf],[hqakq,anl],[cou,tnxgm],[dvt,h],[os,tdbj],[d,iljli],[pq,anl],[tzmmg,yolc],[hwoi,cmmup],[wjisq,s],[l,wjisq],[gv,k],[obj,oz],[jxd,rpj],[qms,anl],[anl,mae],[nk,sdy],[dz,ooyt],[yq,xrggz],[fl,tdbj],[mgkiw,rwyz],[nir,selet],[yodz,pxwio],[pbow,pxwio],[ehep,evn],[o,xoo],[a,yodz],[keq,fg],[ct,k],[ggu,ot],[roh,kq],[vnfxm,vnfxm],[czc,thxp],[gd,os],[u,f],[ju,nir],[l,cwifa],[d,w],[z,im],[atd,oervw],[yolc,fl],[o,tzmmg],[pxwio,gd],[atd,gmsm],[r,gnq],[cmmup,eqe],[ct,uavb],[qaui,sqeun],[bx,ctygp],[cx,xr],[ju,eeew],[hwoi,cwifa],[cuckh,selet],[eqe,selet],[ooyt,pbow],[jyydr,yodz],[lku,foud],[z,w],[du,lbkxy],[qxt,sdy],[feuv,skq],[hwoi,sdy],[vubtz,rz],[lbkxy,lbkxy],[r,urcd],[motk,vbevu],[vnfxm,jlcaj],[qqwh,oby],[eyyrp,elwdm],[a,tht],[e,kmb],[lyyy,selet],[zzzfs,nsc],[ccjjg,vzeun],[jxd,tkyuh],[urcd,cx],[n,hqakq],[pf,k],[u,whpm],[b,xr],[tkyuh,fl],[pzcwm,motk],[xoo,vei],[qms,ev],[yan,jlcaj],[cx,s],[pxwio,zhd],[kq,gnq],[jxd,tkyuh],[n,cxixj],[xyj,lbkxy],[foud,nir],[vz,skq],[cmmup,hqakq],[a,iljli],[vei,eyyrp],[fl,dsc],[gv,du],[x,ot],[ixcj,xbie],[gqiu,rz],[wzqq,j],[xrggz,lbkxy],[ts,bbkbj],[dvt,feuv],[u,vz],[f,mhm],[xb,cuckh],[ztxjs,iljli],[czc,iwqii],[gv,jlcaj],[mwdzr,eyyrp],[rpj,vr],[j,qg],[urcd,sqeun],[os,k],[mkj,l],[tnxgm,kt],[pbow,v],[hqakq,y],[rwyz,obj],[rpyj,ixcj],[fdkr,gnq],[cs,ts],[c,vei],[d,rpyj],[qms,ct],[xoo,oervw],[hsgii,vybf],[cx,yolc],[dvt,cwifa],[lku,kkpy],[zhd,qbogh],[jziyw,x],[anl,j],[ooyt,knp],[yq,sqeun],[roh,qoqod],[vubtz,yan],[hwoi,zwhbd],[ztxjs,sxbq],[pq,hsgii],[xrggz,czc],[veqgp,nsc],[cuckh,jyydr],[jziyw,gd],[zzzfs,pq],[jhvia,b],[hodfs,selet],[nk,zzzfs],[evn,roh],[jyydr,qaui],[eyyrp,cou],[pq,ccjjg],[ctygp,bx],[zk,y],[eqe,b],[f,e],[gowfg,gqiu],[z,vbevu],[vrjt,ev],[mkj,eyyrp],[pzcwm,rpj],[fk,qms],[pq,eyfue],[ixcj,h],[szow,cujil],[gv,b],[jzyr,gd],[yan,xr],[vrjt,jzyr],[ct,qg],[g,wzqq],[kkpy,zwhbd],[lh,urcd],[fdvah,cs],[knp,mgkiw],[kq,kkpy],[sckn,gnq],[selet,gaffq],[e,iljli],[jziyw,jhz],[dsc,jhvia],[wjisq,pq],[sdy,l],[vzeun,cd],[dz,oby],[sdy,lyyy],[vubtz,kmb],[sqeun,roh],[knp,ucnkn],[yq,z],[v,uavb],[hqshg,oervw],[mwdzr,jhz],[urcd,k],[uavb,tht],[fnxqg,ju],[vei,knp],[cd,qoqod],[mkj,motk],[a,lbkxy],[motk,dsc],[vz,mrqlz],[cou,bbkbj],[mae,mae],[bx,cd],[a,obj],[cavbo,lyyy],[xoo,jziyw],[dz,iljli],[yolc,bx],[wci,selet],[qxt,fk],[zwhbd,tzmmg],[s,eeew],[jyydr,tzmmg],[jxd,gowfg],[eq,fjgn],[du,cuckh],[czc,lbkxy],[elwdm,n],[sdy,pzcwm],[vzeun,gqiu],[uavb,dz],[xrggz,jlcaj],[lyyy,bbkbj],[sqeun,kmb],[wzqq,eyfue],[fgjlg,sxbq],[jhz,czc],[sqeun,mwdzr],[g,vzeun],[roh,w],[pxwio,fwb],[gnq,ccjjg],[s,h],[qms,qg],[yodz,z],[f,skq],[oz,eqe],[zhd,ev],[jzyr,yan],[tzmmg,mwdzr],[k,lbkxy],[fk,iad],[mgkiw,j],[l,vbevu],[gbdhd,fg],[cs,czc],[ztxjs,jhz],[c,fdvah],[selet,pq],[mgkiw,eqe],[lig,fk],[lh,zhd],[n,czc],[knp,cmmup],[dsc,lh],[dz,d]]"),
                new double[]{3.2, 16.0, 1.25, 20.0, 1.25, 6.25, 20.0, 0.2, 2.0, 6.25, 1.6, 12.5, 10.0, 15.625, 15.625, 20.0, 20.0, 4.0, 8.0, 16.0, 15.625, 6.25, 0.8, 0.05, 12.5, 12.5, 1.25, 12.5, 16.0, 2.0, 20.0, 1.25, 20.0, 2.5, 15.625, 15.625, 7.8125, 20.0, 16.0, 10.0, 15.625, 10.0, 15.625, 7.8125, 10.0, 6.25, 15.625, 10.0, 7.8125, 0.8, 7.8125, 0.02, 1, 12.5, 10.0, 16.0, 0.0625, 20.0, 16.0, 4.0, 3.125, 0.64, 12.5, 6.25, 10.0, 5.0, 16.0, 8.0, 16.0, 6.25, 20.0, 12.5, 10.0, 16.0, 6.25, 5.0, 6.25, 2.5, 16.0, 8.0, 12.5, 10.0, 2.0, 6.25, 20.0, 2.5, 6.25, 6.25, 4.0, 16.0, 1.6, 12.5, 5.0, 15.625, 0.5, 5.0, 16.0, 1.5625, 7.8125, 12.5, 20.0, 7.8125, 7.8125, 0.0128, 15.625, 4.0, 12.5, 3.125, 12.5, 15.625, 6.25, 20.0, 10.0, 20.0, 8.0, 15.625, 15.625, 15.625, 1.0, 1.25, 2.0, 16.0, 10.0, 12.5, 16.0, 1.953125, 16.0, 2.5, 12.5, 16.0, 8.0, 0.5, 8.0, 1.0, 12.5, 8.0, 6.25, 20.0, 5.0, 16.0, 8.0, 12.5, 7.8125, 7.8125, 20.0, 1.0, 2.0, 3.2, 16.0, 20.0, 0.00625, 0.008, 2.5, 10.0, 0.4096, 0.016, 3.2, 0.0512, 4.0, 16.0, 10.0, 7.629394531250003, 6.25, 1.25, 3.125, 6.25, 2.0, 3.2, 3.2, 0.025, 3.125, 0.15625, 1.0, 10.0, 0.04, 2.5, 0.0256, 16.0, 0.78125, 0.01024, 20.0, 10.0, 1, 20.0, 0.128, 0.078125, 5.0, 0.00390625, 0.002048, 6.4, 12.5, 12.5, 4.0, 10.0, 10.0, 8.0, 12.5, 16.0, 0.0033554431999999984, 16.0, 0.0004096, 1.25, 1.220703125, 6.25, 3.90625, 0.4096, 8.0, 12.5, 7.8125, 4.0, 0.5, 3.125, 8.0, 0.008192, 12.5, 0.9765625, 0.004882812499999992, 2.5, 6.25, 3.125, 12.5, 4.0, 0.8, 0.8, 0.0065536, 3.125, 1, 0.00524288, 4.8828125, 0.0078125, 3.2768, 4.294967296, 0.0016, 0.002048, 2.5, 5.12, 0.256, 0.00128, 0.02048, 0.4194304, 0.00131072, 20.0, 0.625, 2.56, 0.08192, 6.25, 4.194304, 1.28, 0.48828125, 0.1, 7.8125, 0.1024, 0.05, 0.2, 0.6103515625000036, 2.44140625, 0.32, 0.00390625, 1, 0.0524288, 7.8125, 8.0, 0.2048, 8.0, 0.625, 0.00010485760000000243, 0.0125, 0.00016, 0.01024, 0.05, 15.25878906250013, 0.01, 0.15258789062499875, 0.128, 20.0, 4.0, 0.8, 16.0, 0.04096, 0.0016, 6.25, 0.007629394531249939, 1.6384, 0.05, 0.16, 0.1, 8.0, 0.244140625, 0.00016384, 0.0064, 0.0064, 20.0, 4.194304, 4.096, 0.032, 0.0064, 16.0, 0.05, 3.8146972656247256, 0.008589934591999892, 0.0131072, 0.0002048, 0.002, 0.032, 0.0006710886399999693, 0.04194304, 15.625, 19.53125, 0.025, 3.90625, 0.244140625, 9.765625, 0.00041943040000002343, 15.625, 0.00064, 0.00256, 10.0, 0.04096, 3.2, 0.5, 0.00032, 0.6103515625000471, 7.8125, 0.78125, 0.0064, 0.8589934592000125, 0.004096, 0.00065536, 0.0025, 0.262144, 0.625, 0.16, 0.00032, 4.8828125, 0.008, 2.5, 7.8125, 0.04768371582033264, 0.00025, 16.777216, 8.0, 0.016, 0.15625, 0.000610351562500084, 6.25, 0.005, 1.953125, 0.04768371582032429, 1.5625, 0.390625, 0.004, 0.125, 12.5, 9.765625, 6.25, 0.00128, 0.065536, 6.25, 7.629394531250928, 0.008, 3.2, 16.383999999985793, 5.24288, 0.00064, 4.0, 20.0, 0.042949672959991476, 0.0008192, 1.953125, 0.032, 0.00064, 2.0, 0.0512, 12.8, 0.00065536, 0.00020971519999885495, 20.0, 0.04, 0.0256, 3.051757812517442, 19.5312499997769, 0.0128, 1.0, 0.3125, 0.4, 0.00128, 0.128, 0.01525878906250322, 12.5, 7.8125, 0.09536743164063521, 1.6384, 7.81249999998306, 1.0, 0.00256, 0.4096, 0.0016, 4.0, 1.5258789062506244, 2.5600000000135736, 1, 2.5, 0.05, 0.004096, 0.00256, 0.0032, 0.8, 0.00078125, 0.0008, 0.16384, 0.9999999999626419, 0.025, 0.0016, 4.768371582030386, 3.8146972656237352, 1.048576, 1.953125, 0.16, 3.90624999984952, 5.242879999969724, 0.0025, 0.0025, 0.00512, 0.390625, 1.5625, 0.04768371582030098, 0.0065536, 0.00064, 0.1, 8.0, 0.12207031250424083, 0.01048576, 0.0262144, 0.125, 0.4096000000131133, 0.128, 0.016777216, 0.0025, 0.32000000001686246, 0.10485760001125022, 0.01953125, 0.0016384, 0.3276799999717009, 0.0001024, 2.3841857909672783, 0.16, 0.05, 2.55999999981493, 0.004, 0.0512, 0.015258789062500241, 0.0015258789063564088, 6.103515625812575, 3.125000000034011, 7.812499999256332}));
    }
}
