import entity.Element;

import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 16:25
 * @DESCRIPTION
 **/
public class Metall {
    public ArrayList<ArrayList<Element>> calculate(ArrayList<ArrayList<Element>> classifyList){
        for(int i=0;i<classifyList.size();i++){
            for(int j=0;j<classifyList.get(i).size();j++){
                double res=result(classifyList.get(i).get(j).getPassed(),classifyList.get(i).get(j).getFailed(),classifyList.get(i).get(j).getTotalFailed());
                if(res!=0.0&&!Double.isNaN(res)){
//                    System.out.println(classifyList.get(i).get(j).getPath()+classifyList.get(i).get(j).getRow());
//                    System.out.println(classifyList.get(i).get(j).getNumber());
                }
                classifyList.get(i).get(j).setResult(res);
            }
        }
        return classifyList;
    }
    public double result(int passed,int failed,int totalFailed){
        double met=(Double.valueOf(failed))/(Double.valueOf(totalFailed)*(Double.valueOf(failed)+Double.valueOf(passed)));

        return met;
    }
}
