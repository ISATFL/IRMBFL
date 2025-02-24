import java.util.List;
import entity.Bug;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yuelei
 * @TIME 2021/10/8 - 9:25
 * @DESCRIPTION
 **/
public class BuggyLines {
    public ArrayList<Bug> getTrueBug(String fileName) throws IOException {
        ArrayList<Bug> bugArrayList=new ArrayList<>();
        String path="D:\\SoftWare\\BugLocator\\buggy-lines\\"+fileName+".buggy.lines";
        BufferedReader br =new BufferedReader(new FileReader(path));
        String line=null;
        while((line=br.readLine())!=null){
            String args[]=line.split("#");
            String arr[]=args[0].split("\\.");
            String prePath=arr[0];
            String pathArr[]=prePath.split("/");
            String finalPath = String.join(".", pathArr);
            Bug bug=new Bug();
            bug.setBugPath(finalPath);
            bug.setBugRow(Integer.parseInt(args[1]));
            bugArrayList.add(bug);
        }
        return bugArrayList;
    }
}
