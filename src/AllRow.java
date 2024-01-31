import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;

/**
 * @author yuelei
 * @TIME 2021/10/10 - 12:52
 * @DESCRIPTION
 **/
public class AllRow {
    public int getAllRow(int version,String method) throws IOException {
        int allNum = 0;
        BufferedReader br = new BufferedReader(new FileReader("D:\\SoftWare\\BugLocator\\codePath\\"+method+"\\"+method + version + ".txt"));
        String line = null;
        while ((line = br.readLine()) != null) {
//            String fileName = line.substring(0, line.lastIndexOf("."));
//            String arr[] = fileName.split("\\.");
//            String name = StringUtils.join(Arrays.asList(arr), "\\");
//            String path1 = "E:\\Defect4j(code)\\"+method+"\\"+method+"-" + version + "\\src\\main\\java\\" + name + ".java";
//            String path2 = "E:\\Defect4j(code)\\"+method+"\\"+method+"-" + version + "\\src\\java\\" + name + ".java";
//            File file1 = new File("E:\\Defect4j(code)\\"+method+"\\"+method+"-" + version + "\\src\\main\\java\\" + name + ".java");
//            System.out.println(name);
//            String args[] = name.split("\\\\");
//            if (file1.exists()) {
                FileReader fileReader = new FileReader(line);
                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
                lineNumberReader.skip(Long.MAX_VALUE);
                int lines = lineNumberReader.getLineNumber() + 1;
                fileReader.close();
                lineNumberReader.close();
                allNum=allNum+lines;
//
//            } else {
//                File file2=new File(path2);
//                FileReader fileReader = new FileReader(file2);
//                LineNumberReader lineNumberReader = new LineNumberReader(fileReader);
//                lineNumberReader.skip(Long.MAX_VALUE);
//                int lines = lineNumberReader.getLineNumber() + 1;
//                fileReader.close();
//                lineNumberReader.close();
//                allNum=allNum+lines;
//            }
        }
        return allNum;
    }
}
