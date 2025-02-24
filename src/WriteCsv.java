//import com.csvreader.CsvWriter;
//
//import java.io.*;
//import java.nio.charset.Charset;
//import java.util.ArrayList;
//
///**
// * @author yuelei
// * @TIME 2021/10/10 - 20:40
// * @DESCRIPTION
// **/
//public class WriteCsv {
//    public static void main(String args[]) throws IOException {
//        String method="Time";
//
//        String filePath_e="D:\\SoftWare\\BugLocator\\Result\\Lang\\File1\\Einspect@n_num1.csv";
//        CsvWriter csvWriter_e=new CsvWriter(filePath_e,',', Charset.forName("gbk"));
//        String[] headers_e={"Method","Einspect@1","Einspect@3","Einspect@5","Einspect@10"};
//        csvWriter_e.writeRecord(headers_e);
//        for(int num=0;num<3;num++){
//            if(num==0){
//                method="MBFL";
//            }
//            if(num==1){
//                method="IRBL";
//            }
//            if(num==2){
//                method="IRMBFL";
//            }
//            String result_e[]=new String[5];
//            int e1=0,e3=0,e5=0,e10=0;
//            for(int i=1;i<=65;i++){
//                if(i==2){
//                    continue;
//                }
//                String path="D:\\SoftWare\\BugLocator\\"+method+"\\Lang\\Einspect\\ranked"+i+".txt";
//                if(method.equals("IRMBFL")){
//                    path="D:\\SoftWare\\BugLocator\\"+method+"\\Lang\\Einspect_5\\ranked"+i+".txt";
//                }
//                File file=new File(path);
//                if(method.equals("IRMBFL")&&(!file.exists())){
//                    path="D:\\SoftWare\\BugLocator\\IRBL\\Lang\\Einspect\\ranked"+i+".txt";
//                }
//                if(method.equals("MBFL")&&(!file.exists())){
//                    continue;
//                }
//                BufferedReader br =new BufferedReader(new FileReader(path));
//                String r=br.readLine();
//                br.close();
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
//            }
//            result_e[0]= method;
//            result_e[1]=String.valueOf(e1);
//            result_e[2]=String.valueOf(e3);
//            result_e[3]=String.valueOf(e5);
//            result_e[4]=String.valueOf(e10);
//            csvWriter_e.writeRecord(result_e);
//            /**
//             *
//             */
//
//        }
//        csvWriter_e.close();
//
//
//        String filePath="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\File10\\Lang_Result_10.csv";
//        CsvWriter csvWriter=new CsvWriter(filePath,',', Charset.forName("gbk"));
//        String[] headers={"Version","MBFL-EXAM","MBFL-TIME","IRBL-EXAM","IRBL-Time","IR+MBFL-EXAM","IR+MBFL-TIME"};
//        csvWriter.writeRecord(headers);
//        for(int i=1;i<=65;i++){
//            if(i==2){
//                continue;
//            }
//            String result[] = new String[7];
//            result[0]="Lang"+i;
//
//            File mb_file=new File("D:\\SoftWare\\BugLocator\\MBFL\\Lang\\EXAM\\EXAM_Lang"+i+".txt");
//            if(mb_file.exists()){
//                BufferedReader mb_br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\MBFL\\Lang\\EXAM\\EXAM_Lang"+i+".txt"));
//                result[1]=mb_br.readLine();
//                mb_br.close();
//            }else {
//                result[1]=null;
//            }
//
//            File mb_err=new File("E:\\Killmap\\Lang\\Lang-"+i+"\\err.txt");
//            if(mb_err.exists()){
//                String time=getTime("E:\\Killmap\\Lang\\Lang-"+i+"\\err.txt");
//                result[2]=time;
//
//            }else{
//                result[2]=null;
//            }
//            BufferedReader ir_br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRBL\\Lang\\EXAM\\EXAM_Lang"+i+".txt"));
//            result[3]=ir_br.readLine();
//            ir_br.close();
//
//            BufferedReader ir_time=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRBLTime\\Lang\\Lang-"+i+".txt"));
//            result[4]= ir_time.readLine();
//            ir_time.close();
//
//            File file=new File("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\EXAM_10\\EXAM_Lang"+i+".txt");
//            if(file.exists()){
//                BufferedReader irmb_br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRMBFL\\Lang\\EXAM_10\\EXAM_Lang"+i+".txt"));
//                result[5]=irmb_br.readLine();
//                irmb_br.close();
//            }
//            else {
//                BufferedReader irmb_br=new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\IRBL\\Lang\\EXAM\\EXAM_Lang"+i+".txt"));
//                result[5]=irmb_br.readLine();
//                irmb_br.close();
//            }
//
//            if(mb_err.exists()){
//                String time=getTime("E:\\Killmap\\LangAfter\\Lang-"+i+"\\err.txt");
//                result[6]=time;
//
//            }else{
//                result[6]=null;
//            }
//            csvWriter.writeRecord(result);
//            }
//        csvWriter.close();
//
//
//    }
//    static String getTime(String path) throws IOException {
//        BufferedReader mb_err_br=new BufferedReader(new FileReader(path));
//        String line=null;
//        ArrayList<String> arr=new ArrayList<>();
//        while((line=mb_err_br.readLine())!=null){
//            arr.add(line);
//        }
//        String str=arr.get(arr.size()-3);
//        String strs[]=str.split("\t");
//        mb_err_br.close();
//        return strs[1];
//    }
//}
