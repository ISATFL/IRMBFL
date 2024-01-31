package entity;

/**
 * @author yuelei
 * @TIME 2021/9/28 - 20:56
 * @DESCRIPTION 变异体基本三元组
 **/
public class Element {
    /**
     *变异体被多少失败测试用例杀死
     */
    private int failed=0;
    /**
     * 变异体被多少通过的测试用例杀死
     */
    private int passed=0;
    /**
     * 原始程序失败用例总数
     */
    private double result=0;

    public double getResult() {
        return result;
    }

    public void setResult(double result) {
        this.result = result;
    }

    private int totalFailed=0;

   private int number;
   private String path;
   private int row;

    public int getFailed() {
        return failed;
    }

    public void setFailed(int failed) {
        this.failed = failed;
    }

    public int getPassed() {
        return passed;
    }

    public void setPassed(int passed) {
        this.passed = passed;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }
}
