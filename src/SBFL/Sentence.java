package SBFL;

/**
 * @author yuelei
 * @TIME 2021/11/8 - 10:41
 * @DESCRIPTION
 **/
public class Sentence {
    int row;
    String method;
    double sus;
    public Sentence(){

    }
    public void setRow(int row) {
        this.row = row;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public void setSus(double sus) {
        this.sus = sus;
    }

    public int getRow() {
        return row;
    }

    public String getMethod() {
        return method;
    }

    public double getSus() {
        return sus;
    }
}
