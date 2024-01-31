import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2022/2/10 - 19:53
 * @DESCRIPTION
 **/
public class MBFL_IRMBFL_Time {
    public static void main(String args[]) throws IOException {
        String project="Time";
        String time_file="D:\\SoftWare\\BugLocator\\af_IRMBFLTime\\"+project+"_time.csv";
        CsvWriter csvWriter=new CsvWriter(time_file,',', Charset.forName("gbk"));
        for(int version=1;version<=27;version++){
            System.out.println(version);
            String err_path="D:\\AfterKillMap\\"+project+"\\"+project+"-"+version+"\\err.txt";
            File file =new File(err_path);
            if(!file.exists()){
                continue;
            }
            String time=getTime(err_path);
            if(time==null){
                continue;
            }
            String arr[]=time.split("m");
            double minutes=Double.valueOf(arr[0]);
            String arr_1[]=arr[1].split("s");
            double seconds=Double.valueOf(arr_1[0]);
            double all_time=minutes*60+seconds;
            String result[]=new String[2];
            result[0]=String.valueOf(version);
            result[1]=String.valueOf(all_time);
            csvWriter.writeRecord(result);
        }
        csvWriter.close();
    }
    static String getTime(String path) throws IOException {
        BufferedReader mb_err_br=new BufferedReader(new FileReader(path));
        String line=null;
        ArrayList<String> arr=new ArrayList<>();
        while((line=mb_err_br.readLine())!=null){
            arr.add(line);
        }
        String str=arr.get(arr.size()-3);
        if(!str.contains("real")){
            return null;
        }
        String strs[]=str.split("\t");
        mb_err_br.close();
        return strs[1];
    }
}
