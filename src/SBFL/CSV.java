//package SBFL;
//
//import com.csvreader.CsvWriter;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.charset.Charset;
//
///**
// * @author yuelei
// * @TIME 2021/11/8 - 16:38
// * @DESCRIPTION
// **/
//public class CSV {
//    public static void main(String args[]) throws IOException {
//        String method="Lang";
//        int Arr[]={21};
//        for(int f=1;f<=20;f++){
//            int fileNum=f;
//            String filePath_exam="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRSBFL\\File"+fileNum+"\\EXAM_SBFL.csv";
//            CsvWriter csvWriter_exam=new CsvWriter(filePath_exam,',', Charset.forName("gbk"));
//            String[] headers_exam={"Version","EXAM"};
//            csvWriter_exam.writeRecord(headers_exam);
//            String result_exam[]=new String[2];
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
//                File file=new File("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\EXAM_"+fileNum+"\\EXAM_"+method+i+".txt");
//                if(file.exists()) {
//                    BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\EXAM_"+fileNum+"\\EXAM_"+method + i + ".txt"));
//                    result_exam[1] = br.readLine();
//                }else {
//                    File file_em=new File("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\EXAM\\EXAM_"+method+i+".txt");
//                    if(file_em.exists()) {
//                        BufferedReader irmb_br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRBL\\" + method + "\\EXAM\\EXAM_"+method + i + ".txt"));
//                        result_exam[1] = irmb_br.readLine();
//                        irmb_br.close();
//                    }else {
//                        result_exam[1]=null;
//                    }
//                }
//                result_exam[0]=method+i;
//                csvWriter_exam.writeRecord(result_exam);
//            }
//            csvWriter_exam.close();
//
//
//            String filePath="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRSBFL\\File"+fileNum+"\\Einspect@n.csv";
//            CsvWriter csvWriter=new CsvWriter(filePath,',', Charset.forName("gbk"));
//            String[] headers={"Method","Einspect@1","Einspect@3","Einspect@5","Einspect@10"};
//            csvWriter.writeRecord(headers);
//            String result[]=new String[5];
//            int e1=0,e3=0,e5=0,e10=0;
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
//                String r=null;
//                File file=new File("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\Einspect_"+fileNum+"\\ranked"+i+".txt");
//                if(!file.exists()){
//                    File file_ir=new File("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\Einspect@n"+fileNum+"\\ranked"+i+".txt");
//                    if(file_ir.exists()){
//                        BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRBL\\"+method+"\\Einspect@n"+fileNum+"\\ranked"+i+".txt"));
//                        r=br.readLine();
//                        br.close();
//                    }
//                    else {
//                        continue;
//                    }
//                    continue;
//                }else {
//                    BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRSBFL\\"+method+"\\Einspect_"+fileNum+"\\ranked"+i+".txt"));
//                    r=br.readLine();
//                    br.close();
//                }
//
//
//                int rnk=Integer.parseInt(r);
//                if((rnk==1)&&(rnk!=0)){
//                    e1++;
//                }
//                if((rnk<=3)&&(rnk!=0)){
//                    e3++;
//                }
//                if((rnk<=5)&&(rnk!=0)){
//                    e5++;
//                }
//                if((rnk<=10)&&(rnk!=0)){
//                    e10++;
//                }
//
//            }
//            result[0]="IRSBFL";
//            result[1]=String.valueOf(e1);
//            result[2]=String.valueOf(e3);
//            result[3]=String.valueOf(e5);
//            result[4]=String.valueOf(e10);
//            csvWriter.writeRecord(result);
//            csvWriter.close();
//
//
//            /**
//             * SBFL
//             */
////        String filePath_exam="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRSBFL\\EXAM_SBFL.csv";
////        CsvWriter csvWriter_exam=new CsvWriter(filePath_exam,',', Charset.forName("gbk"));
////        String[] headers_exam={"Version","EXAM"};
////        csvWriter_exam.writeRecord(headers_exam);
////        String result_exam[]=new String[2];
////        for(int i=1;i<=38;i++){
////            int numflag=0;
////            for(int j=0;j<Arr.length;j++){
////                if(i==Arr[j]){
////                    numflag++;
////                }
////            }
////            if(numflag!=0){
////                continue;
////            }
////            File file=new File("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\EXAM\\EXAM_"+method+i+".txt");
////            if(file.exists()) {
////                BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\EXAM\\EXAM_"+method + i + ".txt"));
////                result_exam[1] = br.readLine();
////            }else {
////                    result_exam[1]=null;
////            }
////            result_exam[0]=method+i;
////            csvWriter_exam.writeRecord(result_exam);
////            }
////        csvWriter_exam.close();
////
////
////        String filePath="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRSBFL\\Einspect@n.csv";
////        CsvWriter csvWriter=new CsvWriter(filePath,',', Charset.forName("gbk"));
////        String[] headers={"Method","Einspect@1","Einspect@3","Einspect@5","Einspect@10"};
////        csvWriter.writeRecord(headers);
////        String result[]=new String[5];
////        int e1=0,e3=0,e5=0,e10=0;
////        for(int i=1;i<=38;i++){
////            int numflag=0;
////            for(int j=0;j<Arr.length;j++){
////                if(i==Arr[j]){
////                    numflag++;
////                }
////            }
////            if(numflag!=0){
////                continue;
////            }
////            String r=null;
////            File file=new File("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\Einspect@n\\rank"+i+".txt");
////            if(!file.exists()){
////                continue;
////            }else {
////                BufferedReader br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\SBFL\\"+method+"\\Einspect@n\\rank"+i+".txt"));
////                r=br.readLine();
////                br.close();
////            }
////
////
////            int rnk=Integer.parseInt(r);
////            if((rnk==1)&&(rnk!=0)){
////                    e1++;
////                }
////            if((rnk<=3)&&(rnk!=0)){
////                    e3++;
////                }
////            if((rnk<=5)&&(rnk!=0)){
////                    e5++;
////                }
////            if((rnk<=10)&&(rnk!=0)){
////                    e10++;
////                }
////
////        }
////        result[0]="SBFL";
////        result[1]=String.valueOf(e1);
////        result[2]=String.valueOf(e3);
////        result[3]=String.valueOf(e5);
////        result[4]=String.valueOf(e10);
////        csvWriter.writeRecord(result);
////        csvWriter.close();
//        }
//
//    }
//
//}
//
