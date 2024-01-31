package entity;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 10:27
 * @DESCRIPTION
 **/
public class Mutants {
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

    int number;
    String path;
    int row;
}
