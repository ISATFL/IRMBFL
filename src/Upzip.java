import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.GZIPInputStream;

/**
 * @author yuelei
 * @TIME 2021/10/24 - 13:31
 * @DESCRIPTION
 **/
public class Upzip {
    public static void main(String args[]) {
        for(int i=1;i<=1;i++){
            String sourcedir="E:\\D4j(1.4)\\Math\\"+i+"\\32h-killmap-files.tar.gz";
            String ouputfile = "";
            try {
                //建立gzip压缩文件输入流
                FileInputStream fin = new FileInputStream(sourcedir);
                //建立gzip解压工作流
                GZIPInputStream gzin = new GZIPInputStream(fin);
                //建立解压文件输出流
                ouputfile = sourcedir.substring(0, sourcedir.lastIndexOf('.'));
                ouputfile = ouputfile.substring(0, ouputfile.lastIndexOf('.'));
                FileOutputStream fout = new FileOutputStream(ouputfile);

                int num;
                byte[] buf = new byte[1024];

                while ((num = gzin.read(buf, 0, buf.length)) != -1) {
                    fout.write(buf, 0, num);
                }
                gzin.close();
                fout.close();
                fin.close();
            } catch (Exception ex) {
                System.err.println(ex.toString());
            }
            System.out.println(i);
        }

    }
}
