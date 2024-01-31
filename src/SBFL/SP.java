package SBFL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/11/8 - 10:29
 * @DESCRIPTION
 **/
public class SP {
    String file;
    ArrayList<String> methodList=new ArrayList<>();
    ArrayList<Integer> rowList=new ArrayList<>();
    public SP(String file){
        this.file=file;
    }
    public ArrayList<String> getMethod(){
        try{
            String line=null;
            BufferedReader sp=new BufferedReader(new FileReader(file));
            while((line=sp.readLine())!=null){
                String strs[]=line.split("#");
                methodList.add(strs[0]);
                int num=Integer.parseInt(strs[1]);
                rowList.add(num);
            }
        }catch (IOException e){
            System.out.print(e);
        }
        setRow(rowList);
        return methodList;
    }
    public void setRow(ArrayList<Integer> rowArray){
        this.rowList=rowArray;
    }
    public ArrayList<Integer> getRow(){
        return rowList;
    }
}
