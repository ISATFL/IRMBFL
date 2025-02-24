//import com.csvreader.CsvWriter;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//
///**
// * @author yuelei
// * @TIME 2021/11/22 - 19:29
// * @DESCRIPTION
// **/
//
//public class AllCsv {
//    public static void main(String args[]) throws IOException {
//        String method = "Closure";
//        String TOP_N_EXAM_path = "D:\\SoftWare\\BugLocator\\Result\\" + method + "\\IRMBFL\\ALL\\TopN_EXAM.csv";
//        CsvWriter csvWriter_exm = new CsvWriter(TOP_N_EXAM_path, ',', Charset.forName("gbk"));
//        String[] header = {"Top-n","IRBL","MBFL","IRMBFL"};
//        csvWriter_exm.writeRecord(header);
//        String MB_exm="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRMBFL\\EXAM_MBFL.csv";
//        double mb_exam_ave=getAveEXAM(MB_exm);
//        ArrayList<String> noEXAMList=new ArrayList<>();
//        noEXAMList=getNoEXAM(MB_exm);
//        String IR_exm = "D:\\SoftWare\\BugLocator\\Result\\" + method + "\\IRBL\\EXAM_IRBL.csv";
//        double ir_exam_ave=getIB_AveEXAM(IR_exm,noEXAMList);
//        for (int f = 1; f <= 20; f++) {
//            BufferedReader IB_ein = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\Result\\" + method + "\\IRSBFL\\File" + f + "\\Einspect@n.csv"));
//            String IB_exm="D:\\SoftWare\\BugLocator\\Result\\" + method + "\\IRMBFL\\File" + f + "\\EXAM_MBFL.csv";
//            double ib_exam_ave=getIB_AveEXAM(IB_exm,noEXAMList);
//            String [] result={String.valueOf(f),String.format("%.8f",ir_exam_ave),String.format("%.8f",mb_exam_ave),String.format("%.8f",ib_exam_ave)};
//            csvWriter_exm.writeRecord(result);
//
//        }
//        csvWriter_exm.close();
//    }
//
//    static double getAveEXAM(String path) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        double time=1;
//        String line=null;
//        double sum=0;
//        double allNum=0;
//        while((line=br.readLine())!=null){
//            if(time==1){
//                time++;
//                continue;
//
//            }
//            String arr[]= line.split(",");
//            if(arr.length!=2){
//                continue;
//            }
//            sum=sum+Double.valueOf(arr[1]);
//            time++;
//            allNum++;
//        }
//        br.close();
//        return sum/allNum;
//    }
//    static double getIB_AveEXAM(String path,ArrayList<String> noEXAMList) throws IOException {
//        BufferedReader br = new BufferedReader(new FileReader(path));
//        double time=1;
//        String line=null;
//        double sum=0;
//        double allNum=0;
//        while((line=br.readLine())!=null){
//            if(time==1){
//                time++;
//                continue;
//
//            }
//            String arr[]= line.split(",");
//            if(arr.length!=2){
//                continue;
//            }
//            if(noEXAMList.contains(arr[0])){
//                continue;
//            }
//            sum=sum+Double.valueOf(arr[1]);
//            time++;
//            allNum++;
//        }
//        br.close();
//        return sum/allNum;
//    }
//    static ArrayList<String> getNoEXAM(String path) throws IOException {
//        ArrayList<String> noEXAMList=new ArrayList<>();
//        BufferedReader br =new BufferedReader(new FileReader(path));
//        String line=null;
//        while((line=br.readLine())!=null){
//            String arr[]=line.split(",");
//            if(arr.length<2){
//                noEXAMList.add(arr[0]);
//            }
//        }
//        br.close();
//        return noEXAMList;
//    }
//
//}