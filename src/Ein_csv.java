import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author yuelei
 * @TIME 2021/11/25 - 10:26
 * @DESCRIPTIONx
 **/
public class Ein_csv {
    public static void main(String args[]) throws IOException {
        String method="Closure";
        int [] nArr={1,3,5,10};
        for(int n=0;n< nArr.length;n++){
            String TOP_N_Ein_path="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRMBFL\\ALL\\TopN_Einspect@"+nArr[n]+".csv";
            CsvWriter csvWriter_ein=new CsvWriter(TOP_N_Ein_path,',', Charset.forName("gbk"));
            String [] header_ein={"TOP_N","IRBL","MBFL","IRMBFL"};
            String IR_path="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRBL\\Einspect@n.csv";
            String MB_path="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRMBFL\\Einspect@n.csv";
            csvWriter_ein.writeRecord(header_ein);
            int IR_Ein=getAllEinspect(IR_path,nArr[n]);
            int MB_Ein=getAllEinspect(MB_path,nArr[n]);
            for(int i=1;i<=20;i++){
                String IB_path="D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRMBFL\\File"+i+"\\Einspect@n.csv";
                int IB_Ein=getAllEinspect(IB_path,nArr[n]);
                String result[]={String.valueOf(i),String.valueOf(IR_Ein),String.valueOf(MB_Ein),String.valueOf(IB_Ein)};
                csvWriter_ein.writeRecord(result);
            }
            csvWriter_ein.close();
        }



    }
    static int getAllEinspect(String path,int n) throws IOException {
        int num=0;
        BufferedReader br=new BufferedReader(new FileReader(path));
        String line=null;
        int flag=0;
        while ((line=br.readLine())!=null){
            if(flag==0){
                flag++;
                continue;
            }
            String arr[]=line.split(",");
            if(n==1){
                num=Integer.parseInt(arr[1]);
            }
            if(n==3){
                num=Integer.parseInt(arr[2]);
            }
            if(n==5){
                num=Integer.parseInt(arr[3]);
            }
            if(n==10){
                num=Integer.parseInt(arr[4]);
            }
        }
        br.close();
        return num;
    }

}
