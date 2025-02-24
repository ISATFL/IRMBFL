//import com.csvreader.CsvWriter;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.nio.charset.Charset;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * @author yuelei
// * @TIME 2022/2/10 - 19:53
// * @DESCRIPTION
// **/
//public class IRBL_Time {
//    public static void main(String args[]) throws IOException {
//        String project="Math";
//        Integer a[] = {2,4,6,7,10,11,12,14,15,16,17,18,20,26,27,30,31,32,37,39,43,44,46,47,48,49,50,51,52,56,57,58,59,60,61,62,71,74,102,103,104};
//        //Integer a[] = {5,8,12,31};//Mockito
//        //Integer a[] = {4,8,9,13,15,23}; //Time
//        //Integer a[] ={4,9,62,83};//Closure
//        //Integer a[] = {2,8,9,10,15,17,19,24,34,36,43,45,60,64};//Lang
//        String time_file="D:\\SoftWare\\BugLocator\\IRBLTime\\all\\"+project+"_time.csv";
//        CsvWriter csvWriter=new CsvWriter(time_file,',', Charset.forName("gbk"));
//        List<Integer> list = Arrays.asList(a);
//        for(int version=1;version<=106;version++){
//            if(list.contains(version)){
//                continue;
//            }
//            String err_path="D:\\SoftWare\\BugLocator\\IRBLTime\\"+project+"\\"+"Mockito"+"-"+version+".txt";
//            String time=getTime(err_path);
//            String arr[]=time.split("m");
//            if(arr.length>1){
//                double minutes=Double.valueOf(arr[0]);
//                String arr_1[]=arr[1].split("s");
//                double seconds=Double.valueOf(arr_1[0]);
//                double all_time=minutes*60+seconds;
//                String result[]=new String[1];
//                result[0]=String.valueOf(all_time);
//                csvWriter.writeRecord(result);
//            }else {
//                String arr_1[]=arr[0].split("s");
//                String result[]=new String[1];
//                result[0]=String.valueOf(arr_1[0]);
//                csvWriter.writeRecord(result);
//            }
//
//        }
//        csvWriter.close();
//    }
//    static String getTime(String path) throws IOException {
//        BufferedReader br=new BufferedReader(new FileReader(path));
//        String time=br.readLine();
//        return time;
//    }
//}
