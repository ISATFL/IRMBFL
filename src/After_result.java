import SBFL.SBuggyLines;
import SBFL.Sentence;
import entity.Bug;
import entity.Statement;

import java.io.*;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2022/3/21 - 19:37
 * @DESCRIPTION
 **/
public class After_result {
    public static void main(String args[]) throws IOException {
        String method = "Closure";
        for(int fileNum=1;fileNum<=20;fileNum++){
            for(int number=1;number<=133;number++){
                String fileName="D:\\SoftWare\\BugLocator\\MBFL\\"+method+"\\MB_"+method+number+".txt";
                File isFile = new File(fileName);
                if(!isFile.exists()){
                    continue;
                }
                if(number==63||number==93){
                    continue;
                }
                BufferedReader b =new BufferedReader(new FileReader(fileName));
                String line_n = null;
                ArrayList<Statement> allList=new ArrayList<>();
                while((line_n=b.readLine())!=null){
                    String arr[]=line_n.split("#");
                    Statement statement=new Statement();
                    statement.setSort(Integer.parseInt(arr[0]));
                    statement.setPath(arr[1]);
                    statement.setRow(Integer.parseInt(arr[2]));
                    statement.setSus(Double.valueOf(arr[3]));
                    allList.add(statement);
                }
                ArrayList<Sentence> filerSentences = new ArrayList<>();
                BugFile bugFile = new BugFile();
                ArrayList<String> fileList = bugFile.getFileList(fileNum, "D:\\SoftWare\\BugLocator\\IRBL\\" + method + "\\IR_" + method + number  + ".txt");
                String IRMBFL_Path = "D:\\SoftWare\\BugLocator\\IRMBFL\\" + method + "\\" + method + "_" + fileNum + "\\MB_" + method + number + ".txt";
                BufferedWriter IRMB_wr = new BufferedWriter(new FileWriter(IRMBFL_Path));
                int flag = 0;
                for (int n = 0; n < allList.size(); n++) {
                    for (int bugNum = 0; bugNum < fileList.size(); bugNum++) {
                        if (fileList.get(bugNum).equals(allList.get(n).getPath())) {
                            String line = String.valueOf(flag + 1) + "#" + allList.get(n).getPath() + "#" + allList.get(n).getRow() + "#" + allList.get(n).getSus();
                            IRMB_wr.write(line);
                            IRMB_wr.newLine();
                            flag++;
                            Sentence filterSentence = new Sentence();
                            filterSentence.setMethod(allList.get(n).getPath());
                            filterSentence.setRow(allList.get(n).getRow());
                            filterSentence.setSus(allList.get(n).getSus());
                            filerSentences.add(filterSentence);
                        }
                    }
                }
                IRMB_wr.close();
                BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\" + method + "\\" + method + number + ".txt"));
                int allNum = Integer.parseInt(br.readLine());
                SBuggyLines buggyLines = new SBuggyLines();
                ArrayList<Bug> trueBug = buggyLines.getTrueBug(method + "-" + number);
                double exam = 0;
                for (int j = 0; j < filerSentences.size(); j++) {
                    for (int k = 0; k < trueBug.size(); k++) {
                        if ((filerSentences.get(j).getMethod().contains(trueBug.get(k).getBugPath())) &&
                                (filerSentences.get(j).getRow() == trueBug.get(k).getBugRow())) {
                            BufferedWriter E_bw = new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\" + method + "\\Einspect_" + fileNum + "\\ranked" + number + ".txt"));
                            E_bw.write(String.valueOf(j + 1));
                            E_bw.close();
                            exam = (double) (j + 1) / (double) allNum;
                            BufferedWriter exam_bw = new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\" + method + "\\EXAM_" + fileNum + "\\EXAM_" + method + number + ".txt"));
                            exam_bw.write(String.format("%.6f", exam));
                            exam_bw.close();
                            break;
                        }
                    }
                    if (exam != 0) {
                        break;
                    }
                }

            }
            System.out.println("TOP" + fileNum + "结束");
        }


    }


}
