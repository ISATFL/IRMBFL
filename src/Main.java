import SBFL.SBuggyLines;
import SBFL.Sentence;
import entity.*;

import java.io.*;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/9/28 - 21:23
 * @DESCRIPTION
 **/
public class Main {
    public static void main(String args[]) throws IOException {
        String method = "Lang";
        int Arr[] = {2};//59,61,64,66,75过大
        for (int i = 1; i <= 2; i++) {
            int numFlag = 0;
            for (int j = 0; j < Arr.length; j++) {
                if (i == Arr[j]) {
                    numFlag++;
                }
            }
            if (numFlag != 0) {
                continue;
            }
            File file = new File("data\\D4J(1.4)\\"+method+"\\"+i+"\\killmaps\\"+method+"\\"+i+"\\mutants.log");
            if (!file.exists()) {
                continue;
            }
            FileFilter fileFilter = new FileFilter();
            System.out.println("********"+method+"-"+i+"版本********\n");
            System.out.println("********获取变异体信息********\n");
            //获取所有变异体信息：编号、对应语句、类
            ArrayList<Mutants> allFileList = fileFilter.getAllMutants("data\\D4J(1.4)\\"+method+"\\"+i+"\\killmaps\\"+method+"\\"+i+"\\mutants.log");

            ArrayList<KillMap> allkillMapsList = fileFilter.getKillMap("data\\D4J(1.4)\\"+method+"\\"+i+"\\killmaps\\"+method+"\\"+i+"\\killmap.csv", allFileList);
            ArrayList<Element> allelementArrayList = fileFilter.getElement(allFileList, allkillMapsList);
            ArrayList<ArrayList<Element>> allclassifyList = fileFilter.Classify(allelementArrayList);
            Metall allmetall = new Metall();
            System.out.println("********计算代码可疑度********");
            ArrayList<Statement> allstatementArrayList = fileFilter.getFinalResult(allmetall.calculate(allclassifyList));
            Sort allsort = new Sort();
            allstatementArrayList = allsort.Bubble(allstatementArrayList);
            /**
             * 写入信息检索前的MBFL值
             */
            System.out.println("********保存MBFL可疑度列表********");
            String MBFL_Path = "data\\BugLocator\\MBFL\\" + method + "\\MB_" + method + i + ".txt";
            // 创建 File 对象
            File MBFL_Dir = new File("data\\BugLocator\\MBFL\\" + method);
            // 如果目录不存在，则创建
            if (!MBFL_Dir.exists()) {
                MBFL_Dir.mkdirs();
            }
            BufferedWriter MBFL_wr = new BufferedWriter(new FileWriter(MBFL_Path));
            for (int number = 0; number < allstatementArrayList.size(); number++) {
                String line = String.valueOf(number + 1) + "#" + allstatementArrayList.get(number).getPath() + "#" + allstatementArrayList.get(number).getRow() + "#" + allstatementArrayList.get(number).getSus();
                MBFL_wr.write(line);
                MBFL_wr.newLine();
            }
            MBFL_wr.close();

            /**
             * 写入信息检索后
             */
            for (int fileNum = 1; fileNum <= 20; fileNum++) {
                ArrayList<Sentence> filerSentences = new ArrayList<>();
                BugFile bugFile = new BugFile();
                System.out.println("********获取前"+fileNum+"个可疑文件********");
                ArrayList<String> fileList = bugFile.getFileList(fileNum, "data\\BugLocator\\IRBL\\" + method + "\\IR_" + method + i + ".txt");
                String IRMBFL_Path = "data\\BugLocator\\IRMBFL\\" + method + "\\" + method + "_" + fileNum + "\\MB_" + method + i + ".txt";
                // 创建 File 对象
                File IRMBFL_Dir = new File("data\\BugLocator\\IRMBFL\\" + method + "\\" + method + "_" + fileNum);
                // 如果目录不存在，则创建
                if (!IRMBFL_Dir.exists()) {
                    IRMBFL_Dir.mkdirs();
                }
                BufferedWriter IRMB_wr = new BufferedWriter(new FileWriter(IRMBFL_Path));
                int flag = 0;
                System.out.println("********计算并保存使用信息检索后代码可疑列表********");
                for (int number = 0; number < allstatementArrayList.size(); number++) {
                    for (int bugNum = 0; bugNum < fileList.size(); bugNum++) {
                        if (fileList.get(bugNum).equals(allstatementArrayList.get(number).getPath())) {
                            String line = String.valueOf(flag + 1) + "#" + allstatementArrayList.get(number).getPath() + "#" + allstatementArrayList.get(number).getRow() + "#" + allstatementArrayList.get(number).getSus();
                            IRMB_wr.write(line);
                            IRMB_wr.newLine();
                            flag++;
                            Sentence filterSentence = new Sentence();
                            filterSentence.setMethod(allstatementArrayList.get(number).getPath());
                            filterSentence.setRow(allstatementArrayList.get(number).getRow());
                            filterSentence.setSus(allstatementArrayList.get(number).getSus());
                            filerSentences.add(filterSentence);
                        }
                    }
                }
                IRMB_wr.close();

                /**
                 * 计算变异的评价指标
                 */
            BufferedReader pre_br = new BufferedReader(new FileReader("data\\BugLocator\\LOC\\" + method + "\\" + method + i + ".txt"));
            int pre_allNum = Integer.parseInt(pre_br.readLine());
            SBuggyLines pre_buggyLines = new SBuggyLines();
            ArrayList<Bug> pre_trueBug = pre_buggyLines.getTrueBug(method + "-" + i);
            double pre_exam = 0;
            for (int j = 0; j < allstatementArrayList.size(); j++) {
                for (int k = 0; k < pre_trueBug.size(); k++) {
                    if ((allstatementArrayList.get(j).getPath().contains(pre_trueBug.get(k).getBugPath())) &&
                            (allstatementArrayList.get(j).getRow() == pre_trueBug.get(k).getBugRow())) {
                        // 创建 File 对象
                        File E_bw_Dir = new File("data\\BugLocator\\MBFL\\" + method + "\\Einspect@n");
                        // 如果目录不存在，则创建
                        if (!E_bw_Dir.exists()) {
                            E_bw_Dir.mkdirs();
                        }
                        BufferedWriter E_bw = new BufferedWriter(new FileWriter("data\\BugLocator\\MBFL\\" + method + "\\Einspect@n\\rank" + i + ".txt"));
                        E_bw.write(String.valueOf(j + 1));
                        E_bw.close();
                        pre_exam = (double) (j + 1) / (double) pre_allNum;
                        // 创建 File 对象
                        File exam_bw_Dir = new File("data\\BugLocator\\MBFL\\" + method + "\\EXAM");
                        // 如果目录不存在，则创建
                        if (!exam_bw_Dir.exists()) {
                            exam_bw_Dir.mkdirs();
                        }
                        BufferedWriter exam_bw = new BufferedWriter(new FileWriter("data\\BugLocator\\MBFL\\" + method + "\\EXAM\\EXAM_" + method + i + ".txt"));
                        exam_bw.write(String.format("%.6f", pre_exam));
                        exam_bw.close();
                        break;
                    }
                }
                if (pre_exam != 0) {
                    break;
                }
            }
            System.out.println(i);
                /**
                 * 计算信息检索后的变异评价指标
                 */
//                BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\" + method + "\\" + method + i + ".txt"));
//                int allNum = Integer.parseInt(br.readLine());
//
//                SBuggyLines buggyLines = new SBuggyLines();
//                ArrayList<Bug> trueBug = buggyLines.getTrueBug(method + "-" + i);
//                double exam = 0;
//                for (int j = 0; j < filerSentences.size(); j++) {
//                    for (int k = 0; k < trueBug.size(); k++) {
//                        if ((filerSentences.get(j).getMethod().contains(trueBug.get(k).getBugPath())) &&
//                                (filerSentences.get(j).getRow() == trueBug.get(k).getBugRow())) {
//                            BufferedWriter E_bw = new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\" + method + "\\Einspect_" + fileNum + "\\ranked" + i + ".txt"));
//                            E_bw.write(String.valueOf(j + 1));
//                            E_bw.close();
//                            exam = (double) (j + 1) / (double) allNum;
//                            BufferedWriter exam_bw = new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\" + method + "\\EXAM_" + fileNum + "\\EXAM_" + method + i + ".txt"));
//                            exam_bw.write(String.format("%.6f", exam));
//                            exam_bw.close();
//                            break;
//                        }
//                    }
//                    if (exam != 0) {
//                        break;
//                    }
//                }
//                System.out.println("TOP" + fileNum + "结束");
            }


            /**
             * 2
             */
//           BuggyLines buggyLines=new BuggyLines();
//           ArrayList<Bug> trueBug=buggyLines.getTrueBug(method+"-"+i);
//           double exam=0;
//           BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\" + method + "\\" + method + i + ".txt"));
//           int allNum = Integer.parseInt(br.readLine());
//           for(int j=0;j<allstatementArrayList.size();j++){
//               for(int k=0;k<trueBug.size();k++){
//                   if((allstatementArrayList.get(j).getPath().contains(trueBug.get(k).getBugPath()))
//                           &&(allstatementArrayList.get(j).getRow()==trueBug.get(k).getBugRow())){
//                       exam=(double)(j+1)/(double)allNum;
//
//                       BufferedWriter bw_e =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\Einspect_5\\ranked"+i+".txt"));
//                       bw_e.write(String.valueOf((j+1)));
//                       bw_e.close();
//                       BufferedWriter bw_exam=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\EXAM_5\\EXAM_Lang"+i+".txt"));
//                       bw_exam.write(String.format("%.6f",exam));
//                       bw_exam.close();
//
//                       BufferedWriter bw_e =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\MBFL\\"+method+"\\Einspect@n\\ranked"+i+".txt"));
//                       bw_e.write(String.valueOf((j+1)));
//                       bw_e.close();
//                       BufferedWriter bw_exam=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\MBFL\\"+method+"\\EXAM\\EXAM_"+method+i+".txt"));
//                       bw_exam.write(String.format("%.6f",exam));
//                       bw_exam.close();
//                       break;
//                   }
//               }
//               if(exam!=0){
//                   break;
//               }
//           }
//           BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\MBFL\\"+method+"\\MB_"+method+i+".txt"));
//           for(int j=0;j<allstatementArrayList.size();j++){
//               bw.write((j+1)+"#"+allstatementArrayList.get(j).getPath()+"#"+allstatementArrayList.get(j).getRow()+"#"+allstatementArrayList.get(j).getSus());
//               bw.newLine();
//           }
//           bw.close();
            /**
             * 对于不同文件数量下的killmap的结果进行统计
             */

//           BugFile bugFile=new BugFile();
//           FileFilter fileFilter=new FileFilter();
//           BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\"+method+"\\"+method+i+".txt"));
//           int allNum=Integer.parseInt(br.readLine());
//           br.close();
//           ArrayList<Mutants> allFileList=fileFilter.getAllMutants("E:\\Killmap\\LangAfter-"+fileNum+"\\Lang-"+i+"\\mutants.log");
//           ArrayList<KillMap> allkillMapsList=fileFilter.getKillMap("E:\\Killmap\\LangAfter-"+fileNum+"\\Lang-"+i+"\\killmap",allFileList);
//           ArrayList<Element> allelementArrayList=fileFilter.getElement(allFileList,allkillMapsList);
//           ArrayList<ArrayList<Element>> allclassifyList=fileFilter.Classify(allelementArrayList);
//           Metall allmetall=new Metall();
//           ArrayList<Statement> allstatementArrayList=fileFilter.getFinalResult(allmetall.calculate(allclassifyList));
//           Sort allsort=new Sort();
//           allstatementArrayList=allsort.Bubble(allstatementArrayList);
//           BuggyLines buggyLines=new BuggyLines();
//           ArrayList<Bug> trueBug=buggyLines.getTrueBug(method+"-"+i);
//           double exam=0;
//           for(int j=0;j<allstatementArrayList.size();j++){
//               for(int k=0;k<trueBug.size();k++){
//                   if((allstatementArrayList.get(j).getPath().contains(trueBug.get(k).getBugPath()))
//                           &&(allstatementArrayList.get(j).getRow()==trueBug.get(k).getBugRow())){
//                       exam=(double)(j+1)/(double)allNum;
//                       BufferedWriter bw_e =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\Einspect_"+fileNum+"\\ranked"+i+".txt"));
//                       bw_e.write(String.valueOf((j+1)));
//                       bw_e.close();
//                       BufferedWriter bw_exam=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\EXAM_"+fileNum+"\\EXAM_"+method+i+".txt"));
//                       bw_exam.write(String.format("%.6f",exam));
//                       bw_exam.close();
//                       break;
//                   }
//               }
//               if(exam!=0){
//                   break;
//               }
//           }
////            /**
////             * 1
////             */
//           BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\"+method+"_"+fileNum+"\\MB_"+method+i+".txt"));
//           for(int j=0;j<allstatementArrayList.size();j++){
//               bw.write((j+1)+"#"+allstatementArrayList.get(j).getPath()+"#"+allstatementArrayList.get(j).getRow()+"#"+allstatementArrayList.get(j).getSus());
//               bw.newLine();
//           }
//           bw.close();
//
//           ArrayList<String> fileList=bugFile.getFileList(10,"D:\\SoftWare\\BugLocator\\IRBL\\Lang\\IR_Lang"+i+".txt");
//           ArrayList<Mutants> finalFileList=fileFilter.getFileFilter("E:\\D4j(1.4)\\Lang\\"+i+"\\killmaps\\Lang\\"+i+"\\mutants.log",fileList);
//           ArrayList<KillMap> killMapsList=fileFilter.getKillMap("E:\\D4j(1.4)\\Lang\\"+i+"\\killmaps\\Lang\\"+i+"\\killmap.csv",finalFileList);
//           ArrayList<Element> elementArrayList=fileFilter.getElement(finalFileList,killMapsList);
//           ArrayList<ArrayList<Element>> classifyList=fileFilter.Classify(elementArrayList);
//           Metall metall=new Metall();
//           ArrayList<Statement> statementArrayList=fileFilter.getFinalResult(metall.calculate(classifyList));
//           Sort sort=new Sort();
//           statementArrayList=sort.Bubble(statementArrayList);


            /**
             * 计算exam
             */
//           BuggyLines buggyLines=new BuggyLines();
//           ArrayList<Bug> trueBug=buggyLines.getTrueBug("Lang-"+i);
//           double exam=0;
//           for(int j=0;j<statementArrayList.size();j++){
//               for(int k=0;k<trueBug.size();k++){
//                   if((statementArrayList.get(j).getPath().equals(trueBug.get(k).getBugPath()))
//                           &&(statementArrayList.get(j).getRow()==trueBug.get(k).getBugRow())){
//                       exam=(double)(j+1)/(double)allNum;
//                       BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\EXAM_10\\EXAM_Lang"+i+".txt"));
//                       bw.write(String.format("%.6f",exam));
//                       bw.close();
//                       break;
//                   }
//               }
//               if(exam!=0){
//                   break;
//               }
//           }


/**
 * 写入信息检索+变异后的结果
 */
//           BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\IRMB_Lang_10\\IRMB_Lang"+i+".txt"));
//           for(int j=0;j<statementArrayList.size();j++){
//               bw.write((j+1)+"#"+statementArrayList.get(j).getPath()+"#"+statementArrayList.get(j).getRow()+"#"+statementArrayList.get(j).getSus());
//               bw.newLine();
//           }
//           bw.close();


            /**
             * MBFL
             */
//
            /**
             * IRMBFL
             */


        }
//
    }
    }

