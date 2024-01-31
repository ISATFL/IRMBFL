import entity.Statement;

import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 21:19
 * @DESCRIPTION
 **/
public class Sort {
    public ArrayList<Statement> Bubble(ArrayList<Statement> statementArrayList){
        for(int i=0;i<statementArrayList.size()-1;i++){
            for(int j=0;j<statementArrayList.size()-1;j++){
                if(statementArrayList.get(j).getSus()<statementArrayList.get(j+1).getSus()){
                    Statement statement=statementArrayList.get(j);
                    statementArrayList.set(j,statementArrayList.get(j+1));
                    statementArrayList.set(j+1,statement);
                }
            }
        }
        return statementArrayList;
    }
}
