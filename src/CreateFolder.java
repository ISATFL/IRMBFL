import java.io.File;

/**
 * @author yuelei
 * @TIME 2021/11/22 - 18:48
 * @DESCRIPTION
 **/
public class CreateFolder {
    public static void main(String args[]){
        String method="Time";
        for(int i=1;i<=20;i++){
            File method_file=new File("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\"+method+"_"+i);
            File einspect_file=new File("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\Einspect_"+i);
            File exam_file=new File("D:\\SoftWare\\BugLocator\\IRMBFL\\"+method+"\\EXAM_"+i);
            File result_file=new File("D:\\SoftWare\\BugLocator\\Result\\"+method+"\\IRMBFL\\File"+i);
            if(!method_file.exists()){
                method_file.mkdir();
            }
            if(!einspect_file.exists()){
                einspect_file.mkdir();
            }
            if(!exam_file.exists()){
                exam_file.mkdir();
            }
            if(!result_file.exists()){
                result_file.mkdir();
            }
        }
    }
}
