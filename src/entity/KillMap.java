package entity;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 10:23
 * @DESCRIPTION
 **/
public class KillMap {
    int muNumber;
    String result;
    int totalFailed;
    public int getMuNumber() {
        return muNumber;
    }

    public void setMuNumber(int muNumber) {
        this.muNumber = muNumber;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }
}
