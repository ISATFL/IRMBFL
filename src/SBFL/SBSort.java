package SBFL;

import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/11/8 - 10:44
 * @DESCRIPTION
 **/
public class SBSort {
    ArrayList<Sentence> sentences=new ArrayList<>();
    public SBSort(ArrayList<Sentence> sentences){
        this.sentences=sentences;
    }
    public ArrayList<Sentence> BubbleSort(){
        for(int i=0;i<sentences.size()-1;i++){
            for(int j=0;j<sentences.size()-1;j++){
                if((sentences.get(j).getSus()<sentences.get(j+1).getSus())){
                    Sentence s=new Sentence();
                    s=sentences.get(j);
                    sentences.set(j,sentences.get(j+1));
                    sentences.set(j+1,s);
                }
            }
        }
        return sentences;
    }
}
