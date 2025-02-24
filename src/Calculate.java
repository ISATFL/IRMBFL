
import entity.Bug;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author yuelei
 * @TIME 2021/10/8 - 10:07
 * @DESCRIPTION
 **/
public class Calculate {
    public static void main(String args[]) throws IOException {
        String method="Mockito";
        int Arr[]={};
        for(int i=1;i<=38;i++){
            int flagnum=0;
            for(int j=0;j<Arr.length;j++){
                if(i==Arr[j]){
                    flagnum++;
            }
            }
            if(flagnum!=0){
                continue;
            }
            AllRow allRow=new AllRow();
            int numb=allRow.getAllRow(i,method);
            BufferedWriter b=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\LOC\\"+method+"\\"+method+i+".txt"));
            b.write(String.valueOf(numb));
            b.close();

            BuggyLines buggyLines=new BuggyLines();
            ArrayList<Bug> trueBug=buggyLines.getTrueBug(method+"-"+i);
            BugFile bugFile=new BugFile();
            int flag=0;
            int rowNumber=0;
            int allNumber=0;
            BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\"+method+"\\"+method+i+".txt"));
            allNumber=Integer.parseInt(br.readLine());
            br.close();
            File file=new File("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\IR_"+method+i+".txt");
            FileReader fileReader = new FileReader(file);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int fileNum = lineNumberReader.getLineNumber();
            fileReader.close();
            lineNumberReader.close();
            ArrayList<String> fileList=bugFile.getFileList(fileNum,"D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\IR_"+method+i+".txt");
            for(int j=0;j<fileList.size();j++){
                for(int k=0;k<trueBug.size();k++){
                    if(fileList.get(j).equals(trueBug.get(k).getBugPath())){
                        flag=j;
                        rowNumber=trueBug.get(k).getBugRow();
                        break;
                    }
                }
                if(rowNumber!=0){
                    break;
                }
            }
            ArrayList<Integer> methodRow=new ArrayList<>();
            for(int j=0;j<fileList.size();j++){
                int num=getMethodNum(i,fileList.get(j));
                methodRow.add(num);
            }
            for(int j=0;j<=flag-1;j++){
                    rowNumber=rowNumber+methodRow.get(j);
            }
            double exam =(double)rowNumber/(double)allNumber;
            BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\Einspect@n\\ranked"+i+".txt"));
            bw.write(String.valueOf(rowNumber));
            BufferedWriter bw_e=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\EXAM\\EXAM_"+method+i+".txt"));
            bw_e.write(String.format("%.6f",exam));
            bw.close();
            bw_e.close();
        }

    }
    public static int getMethodNum(int number,String fileName) throws IOException {
        String arr[]=fileName.split("\\.");
        String name= String.join("\\", arr);
        String path1="E:\\Defect4j(code)\\Mockito\\mockito-"+number+"\\src\\"+name+".java";
        String path2="E:\\Defect4j(code)\\Mockito\\mockito-"+number+"\\src\\"+name+".java";
        File file1 =new File("E:\\Defect4j(code)\\Mockito\\mockito-"+number+"\\src\\"+name+".java");
        if(file1.exists()){
            FileReader fileReader = new FileReader(file1);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int num = lineNumberReader.getLineNumber() + 1;
            fileReader.close();
            lineNumberReader.close();
            return num;
        }else {
            File file2=new File(path2);
            FileReader fileReader = new FileReader(file2);
            LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
            lineNumberReader.skip(Long.MAX_VALUE);
            int num = lineNumberReader.getLineNumber() + 1;
            fileReader.close();
            lineNumberReader.close();
            return num;
        }

    }
}
