package SBFL;//package SBFL;
//
//import entity.Bug;
//
//import java.io.*;
//import java.util.ArrayList;
///**
// * @author yuelei
// * @TIME 2021/11/8 - 10:12
// * @DESCRIPTION
// **/
//public class SBFL {
//    public static void main(String args[]) throws IOException {
//        String method="Time";
//        for(int f=1;f<=20;f++){
//            int fileNum=f;
//            int Arr[]={21};
//            for(int i=1;i<=27;i++){
//                int numflag=0;
//                for(int j=0;j<Arr.length;j++){
//                    if(i==Arr[j]){
//                        numflag++;
//                    }
//                }
//                if(numflag!=0){
//                    continue;
//                }
//                String matrixPath="E:\\D4j(1.4)\\"+method+"\\"+i+"\\gzoltars\\"+method+"\\"+i+"\\matrix";
//                String spectraPath="E:\\D4j(1.4)\\"+method+"\\"+i+"\\gzoltars\\"+method+"\\"+i+"\\spectra";
//                MS ms=new MS(matrixPath);
//                SP sp=new SP(spectraPath);
//                ArrayList<ArrayList<Integer>> matrix=ms.getMS();
//                ArrayList<String> re=ms.getRE();
//                SBElement sbElement=new SBElement(matrix,re);
//                ArrayList<Integer> EFArray = new ArrayList<>();
//                ArrayList<Integer> EPArray = new ArrayList<>();
//                ArrayList<Integer> NFArray = new ArrayList<>();//未执行到且不通过的数组nf
//                ArrayList<Integer> NPArray = new ArrayList<>();//未执行到且通过的数组np
//                EFArray = sbElement.getA_ef();
//                EPArray = sbElement.getA_ep();
//                NFArray = sbElement.getA_nf();
//                NPArray = sbElement.getA_np();
//                ArrayList<Double> susList=new ArrayList<>();
//                Dstar dstar=new Dstar();
//                susList=dstar.getSuspicion(EFArray,EPArray,NFArray,NPArray);
//                ArrayList<String> methodList=new ArrayList<>();
//                ArrayList<Integer> rowList=new ArrayList<>();
//                methodList=sp.getMethod();
//                rowList=sp.getRow();
//                ArrayList<Sentence> sentences=new ArrayList<>();
//                for(int j=0;j<rowList.size();j++){
//                    Sentence s=new Sentence();
//                    s.setRow(rowList.get(j));
//                    s.setMethod(methodList.get(j));
//                    s.setSus(susList.get(j));
//                    sentences.add(s);
//                }
//                SBSort sort=new SBSort(sentences);
//                sentences= sort.BubbleSort();
//                /**
//                 * 写入信息检索前的SBFL结果
//                 //             */
//            String SBFL_Path="D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\SB_"+method+i+".txt";
//            BufferedWriter SBFL_wr=new BufferedWriter(new FileWriter(SBFL_Path));
//            for(int number=0;number<sentences.size();number++){
//                String line=String.valueOf(number+1)+"#"+sentences.get(number).getMethod()+"#"+sentences.get(number).getRow()+"#"+sentences.get(number).getSus();
//                SBFL_wr.write(line);
//                SBFL_wr.newLine();
//            }
//            SBFL_wr.close();
//
////                /**
////                 * 筛选文件数量,写入IRSBFL的值
////                 */
////                SBBugFile bugFile=new SBBugFile();
////                ArrayList<Sentence> filerSentences=new ArrayList<>();
////                ArrayList<String> fileList=bugFile.getFileList(fileNum,"D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\IR_"+method+i+".txt");
////                String IRSBFL_Path="D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\"+method+"_"+fileNum+"\\SB_"+method+i+".txt";
////                BufferedWriter IRSB_wr=new BufferedWriter(new FileWriter(IRSBFL_Path));
////                int flag=0;
////                for(int number=0;number<sentences.size();number++){
////                    for(int bugNum=0;bugNum<fileList.size();bugNum++){
////                        if(fileList.get(bugNum).equals(sentences.get(number).getMethod())){
////                            String line=String.valueOf(flag+1)+"#"+sentences.get(number).getMethod()+"#"+sentences.get(number).getRow()+"#"+sentences.get(number).getSus();
////                            IRSB_wr.write(line);
////                            IRSB_wr.newLine();
////                            flag++;
////                            Sentence filterSentence=new Sentence();
////                            filterSentence.setMethod(sentences.get(number).getMethod());
////                            filterSentence.setRow(sentences.get(number).getRow());
////                            filterSentence.setSus(sentences.get(number).getSus());
////                            filerSentences.add(filterSentence);
////                        }
////                    }
////                }
////                IRSB_wr.close();
//
//                /**
//                 * 计算SBFL评价指标
//                 */
//            BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\"+method+"\\"+method+i+".txt"));
//            int allNum=Integer.parseInt(br.readLine());
//
//            SBuggyLines buggyLines=new SBuggyLines();
//            ArrayList<Bug> trueBug=buggyLines.getTrueBug(method+"-"+i);
//            double exam=0;
//            for(int j=0;j<sentences.size();j++){
//                for(int k=0;k<trueBug.size();k++){
//                    if((sentences.get(j).getMethod().contains(trueBug.get(k).getBugPath()))&&
//                            (sentences.get(j).getRow()==trueBug.get(k).getBugRow())){
//                        BufferedWriter E_bw=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\Einspect@n\\rank"+i+".txt"));
//                        E_bw.write(String.valueOf(j+1));
//                        E_bw.close();
//                        exam=(double)(j+1)/(double)allNum;
//                        BufferedWriter exam_bw=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\EXAM\\EXAM_"+method+i+".txt"));
//                        exam_bw.write(String.format("%.6f",exam));
//                        exam_bw.close();
//                        break;
//                    }
//                }
//                if(exam!=0){
//                    break;
//                }
//            }
//            System.out.println(i);
//                /**
//                 * 计算IRSBFL评价指标
////                 */
////                BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\LOC\\"+method+"\\"+method+i+".txt"));
////                int allNum=Integer.parseInt(br.readLine());
////
////                SBuggyLines buggyLines=new SBuggyLines();
////                ArrayList<Bug> trueBug=buggyLines.getTrueBug(method+"-"+i);
////                double exam=0;
////                for(int j=0;j<filerSentences.size();j++){
////                    for(int k=0;k<trueBug.size();k++){
////                        if((filerSentences.get(j).getMethod().contains(trueBug.get(k).getBugPath()))&&
////                                (filerSentences.get(j).getRow()==trueBug.get(k).getBugRow())){
////                            BufferedWriter E_bw=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\Einspect_"+fileNum+"\\ranked"+i+".txt"));
////                            E_bw.write(String.valueOf(j+1));
////                            E_bw.close();
////                            exam=(double)(j+1)/(double)allNum;
////                            BufferedWriter exam_bw=new BufferedWriter(new FileWriter("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\EXAM_"+fileNum+"\\EXAM_"+method+i+".txt"));
////                            exam_bw.write(String.format("%.6f",exam));
////                            exam_bw.close();
////                            break;
////                        }
////                    }
////                    if(exam!=0){
////                        break;
////                    }
////                }
////                System.out.println(i);
////            }
////            System.out.println("TOP"+fileNum+"结束");
//        }
//
//    }
//}
