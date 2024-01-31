package SBFL;

import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/11/8 - 10:05
 * @DESCRIPTION
 **/
public class Dstar {
    ArrayList<Integer> EFArray=new ArrayList<>();
    ArrayList<Integer> EPArray=new ArrayList<>();
    ArrayList<Integer> NFArray=new ArrayList<>();//未执行到且不通过的数组nf
    ArrayList<Integer> NPArray=new ArrayList<>();//未执行到且通过的数组np
    public ArrayList<Double> getSuspicion(ArrayList<Integer> EFArray,ArrayList<Integer> EPArray,ArrayList<Integer> NFArray,ArrayList<Integer> NPArray){
        ArrayList<Double> SuspicionArray=new ArrayList<>();
        double Sus=0;
        for(int i=0;i<EFArray.size();i++){
            double ef=EFArray.get(i);
            double ep=EPArray.get(i);
            double nf=NFArray.get(i);
            double np=NPArray.get(i);
            Sus=(ef*ef)/(ep+nf);
//            Sus=ef/Math.sqrt((ef+nf)*(ef+ep));
            if(Double.isNaN(Sus)){
                System.out.println("b");
                Sus=0;
            }
            if(Double.isInfinite(Sus)){
                System.out.println("a");
                Sus=0;
            }
            SuspicionArray.add(Sus);
        }
        return SuspicionArray;
    }
}
