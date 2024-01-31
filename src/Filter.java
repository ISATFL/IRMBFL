import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author yuelei
 * @TIME 2021/10/24 - 12:42
 * @DESCRIPTION
 **/
public class Filter {
    public static void main(String args[]) throws IOException {
//        Integer arr[] = {1,2,5,9,11,12,16,17}; //Chart
//        Integer arr[] = {2,4,6,7,10,11,12,14,15,16,17,18,20,26,27,30,31,32,37,39,43,44,46,47,48,49,50,51,52,56,57,58,59,60,61,62,71,74,102,103,104};
//        Integer arr[] = {5,8,12,31};//Mockito
//        Integer arr[] = {4,8,9,13,15,23}; //Time
        Integer arr[] ={4,9,62,83};
        List<Integer> list = Arrays.asList(arr);
        for(int i=62;i<=83;i++) {
            if (!list.contains(i)) {
                continue;
            }
            BugFile bugFile=new BugFile();
            System.out.println(i);
            ArrayList<String> fileList=bugFile.getFileList(4,"D:\\SoftWare\\BugLocator\\IRBL\\Closure\\IR_Closure"+i+".txt");
            String line=null;
            String method=null;
            BufferedReader br = new BufferedReader(new FileReader("D:\\folder\\class\\"+i+".src"));
            BufferedWriter bw =new BufferedWriter(new FileWriter("D:\\folder\\finclass\\"+i+".src"));
            BufferedReader br_test = new BufferedReader(new FileReader("D:\\folder\\test\\"+i));
            BufferedWriter bw_test =new BufferedWriter(new FileWriter("D:\\folder\\fintest\\"+i));
            ArrayList<String> mun_method=new ArrayList<>();
            ArrayList<String> test_method=new ArrayList<>();
            ArrayList<String> all_test = new ArrayList<>();
            while((line=br.readLine())!=null){
                mun_method.add(line);
            }
            while ((line=br_test.readLine())!=null){
                test_method.add(line);
            }
            br.close();
            br_test.close();
            for(int j=0;j<fileList.size();j++){
                for(int num=0;num<mun_method.size();num++){
                    if(fileList.get(j).equals(mun_method.get(num))){
                        method=mun_method.get(num);
                        all_test.add(method);
                        bw.write(method);
                        bw.newLine();
                    }
                }

            }
            for(int num=0;num<test_method.size();num++){
                for(int j=0;j<all_test.size();j++){
                    String str = all_test.get(j)+"Test";
                    if(test_method.get(num).equals(str)){
                        method=test_method.get(num);
                        bw_test.write(method);
                        bw_test.newLine();
                    }
                }
            }
            bw.close();
            bw_test.close();
            BufferedReader r=new BufferedReader(new FileReader("D:\\folder\\finclass\\"+i+".src"));
            BufferedWriter w=new BufferedWriter(new FileWriter("D:\\folder\\class\\"+i+".src"));
            BufferedReader r_test=new BufferedReader(new FileReader("D:\\folder\\fintest\\"+i));
            BufferedWriter w_test=new BufferedWriter(new FileWriter("D:\\folder\\test\\"+i));
            String newLine=null;
            int flag_1=0;
            while((newLine=r.readLine())!=null){
                if(!newLine.equals("")){
                    if(flag_1!=0){
                        w.newLine();
                    }
                    w.write(newLine);
                }
                flag_1++;
            }
            int flag_2=0;
            while((newLine=r_test.readLine())!=null){
                if(!newLine.equals("")){
                    if(flag_2!=0){
                        w_test.newLine();
                    }
                    w_test.write(newLine);
                }
                flag_2++;
            }
            r.close();
            w.close();
            r_test.close();
            w_test.close();
        }
    }
}
