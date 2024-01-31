package SBFL;

import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/11/8 - 10:08
 * @DESCRIPTION
 **/
public class SBElement {
    ArrayList<ArrayList<Integer>> MS = new ArrayList<>();
    ArrayList<String> RE = new ArrayList<>();
    ArrayList<Integer> EFArray = new ArrayList<>();//执行到且不通过的数组ef
    ArrayList<Integer> EPArray = new ArrayList<>();//执行到且通过的数组ep
    ArrayList<Integer> NFArray = new ArrayList<>();//未执行到且不通过的数组nf
    ArrayList<Integer> NPArray = new ArrayList<>();//未执行到且通过的数组np

    public SBElement(ArrayList<ArrayList<Integer>> MS, ArrayList<String> RE) {
        this.MS = MS;
        this.RE = RE;
    }

    public void setRE(ArrayList<String> RE) {
        this.RE = RE;
    }

    public void setMS(ArrayList<ArrayList<Integer>> MS) {
        this.MS = MS;
    }

    public ArrayList<ArrayList<Integer>> getMS() {
        return MS;
    }

    public ArrayList<String> getRE() {
        return RE;
    }

    public ArrayList<Integer> getA_ef() {//计算测试用例执行到语句Si且测试用例不通过的数量；
        for (int i = 0; i < MS.get(0).size(); i++) {
            EFArray.add(0);
            EPArray.add(0);
            NFArray.add(0);
            NPArray.add(0);
        }
        for (int i = 0; i < MS.size(); i++) {
            for (int j = 0; j < MS.get(i).size(); j++) {
                if ((MS.get(i).get(j) == 1) && (RE.get(i).equals("-"))) {
                    int num = EFArray.get(j);
                    num++;
                    EFArray.set(j, num);
                }
                if ((MS.get(i).get(j) == 1) && (RE.get(i).equals("+"))) {
                    int num = EPArray.get(j);
                    num++;
                    EPArray.set(j, num);
                }
                if ((MS.get(i).get(j) == 0) && (RE.get(i).equals("-"))) {
                    int num = NFArray.get(j);
                    num++;
                    NFArray.set(j, num);
                }
                if ((MS.get(i).get(j) == 0) && (RE.get(i).equals("+"))) {
                    int num = NPArray.get(j);
                    num++;
                    NPArray.set(j, num);
                }
            }
        }
        setEPArray(EPArray);
        setNFArray(NFArray);
        setNPArray(NPArray);
        return EFArray;
    }

    public void setEPArray(ArrayList<Integer> EPArray) {
        this.EPArray = EPArray;
    }

    public ArrayList<Integer> getA_ep() {//计算测试用例执行到语句Si且测试用例通过的数量；
        return EPArray;
    }

    public void setNFArray(ArrayList<Integer> NFArray) {
        this.NFArray = NFArray;
    }

    public ArrayList<Integer> getA_nf() {//计算测试用未例执行到语句Si且测试用例通过的数量；
        return NFArray;
    }

    public void setNPArray(ArrayList<Integer> NPArray) {
        this.NPArray = NPArray;
    }

    public ArrayList<Integer> getA_np() {//计算测试用未例执行到语句Si且测试用例未通过的数量；
        return NPArray;
    }
}
