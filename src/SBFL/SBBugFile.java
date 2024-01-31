package SBFL;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/9/28 - 21:09
 * @DESCRIPTION 提取IRBL定位出的错误文件
 **/
public class SBBugFile {

    ArrayList<String> getFileList(int fileNum, String Path) throws IOException {
        ArrayList<String> fileNameList= new ArrayList<>();
        BufferedReader br =new BufferedReader(new FileReader(Path));
        String line ="";
        int i=1;
        while(i<=fileNum){
            line=br.readLine();
            String arr[]=line.split(",");
            String file =arr[1];
            String fileArr[]=file.split("\\.");
            String finalName="";
            for(int k=0;k<fileArr.length-1;k++){
                finalName=finalName+fileArr[k];
                if(k!=fileArr.length-2){
                    finalName=finalName+".";
                }

            }
            fileNameList.add(finalName);
            i++;
        }
        br.close();
        return fileNameList;
    }
}
