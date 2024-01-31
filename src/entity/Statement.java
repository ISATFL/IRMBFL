package entity;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 20:46
 * @DESCRIPTION
 **/
public class Statement {
    String path;
    double sus;
    int row=0;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    int sort;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSus() {
        return sus;
    }

    public void setSus(double sus) {
        this.sus = sus;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }
}
