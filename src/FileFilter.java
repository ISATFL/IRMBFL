import entity.Element;
import entity.KillMap;
import entity.Mutants;
import entity.Statement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author yuelei
 * @TIME 2021/9/29 - 9:47
 * @DESCRIPTION
 **/
public class FileFilter {
    public ArrayList<Mutants> getFileFilter(String path, ArrayList<String> fileList) throws IOException {
        ArrayList<Mutants> finalList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = br.readLine()) != null) {
            String arr[] = line.split(":");
            String fileName[] = arr[4].split("@");
            String name = fileName[0];
            String aee[] = name.split("\\$");
            name=aee[0];
            for (int i = 0; i < fileList.size(); i++) {
                if (name.equals(fileList.get(i))) {
                    Mutants mutants = new Mutants();
                    mutants.setNumber(Integer.parseInt(arr[0]));
                    mutants.setPath(name);
                    mutants.setRow(Integer.parseInt(arr[5]));
                    finalList.add(mutants);
                    break;
                }
            }
        }
        br.close();
        return finalList;
    }

    public ArrayList<KillMap> getKillMap(String path, ArrayList<Mutants> mutantList) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        int totalFailed=0;
        ArrayList<KillMap> mapList = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String arr[] = line.split(",");
            if((Integer.parseInt(arr[1])==0)&&(arr[3].equals("FAIL"))){
                totalFailed++;
            }
            for (int i = 0; i < mutantList.size(); i++) {
                if ((Integer.parseInt(arr[1]) == mutantList.get(i).getNumber()) || (Integer.parseInt(arr[1]) == 0)) {
                    KillMap killMap = new KillMap();
                    killMap.setMuNumber(Integer.parseInt(arr[1]));
                    killMap.setResult(arr[3]);
                    killMap.setTotalFailed(totalFailed);
                    mapList.add(killMap);
                    break;
                }
            }
        }
        br.close();
        return mapList;

    }
    public ArrayList<ArrayList<Element>> Classify(ArrayList<Element> muList){
        ArrayList<ArrayList<Element>> classifyList=new ArrayList<>();
        ArrayList<Element> oneList=new ArrayList<>();
        for(int i=0;i<muList.size();i++){
            if(oneList.size()==0){
                oneList.add(muList.get(i));
            }else {
                if((muList.get(i).getRow()==oneList.get(0).getRow())&&(muList.get(i).getPath().equals(oneList.get(0).getPath()))){
                    oneList.add(muList.get(i));
                }
                else {
                    ArrayList<Element> copyList= (ArrayList<Element>) oneList.clone();
                    classifyList.add(copyList);
                    oneList.clear();
                    oneList.add(muList.get(i));
                }
            }
        }
        return classifyList;
    }
    public ArrayList<Element> getElement(ArrayList<Mutants> mutantsArrayList, ArrayList<KillMap> killMapArrayList){
        ArrayList<Element> elementArrayList=new ArrayList<>();
        for(int i =0;i<mutantsArrayList.size();i++){
            int passed=0;
            int failed=0;
            int totalFailed=killMapArrayList.get(0).getTotalFailed();
            int number=mutantsArrayList.get(i).getNumber();
            String allResult="PASS";
            for(int j=0;j<killMapArrayList.size();j++){
                if(killMapArrayList.get(j).getMuNumber()==0){
                    allResult=killMapArrayList.get(j).getResult();
                }
                if(killMapArrayList.get(j).getMuNumber()==number){
                    if((!killMapArrayList.get(j).getResult().equals(allResult))&&(allResult.equals("PASS"))){
                        passed++;
                    }
                    if((!killMapArrayList.get(j).getResult().equals(allResult))&&(allResult.equals("FAIL"))){
                        failed++;
                    }
                }
            }
            Element element=new Element();
            element.setNumber(mutantsArrayList.get(i).getNumber());
            element.setPath(mutantsArrayList.get(i).getPath());
            element.setRow(mutantsArrayList.get(i).getRow());
            element.setTotalFailed(totalFailed);
            element.setFailed(failed);
            element.setPassed(passed);
            elementArrayList.add(element);
        }
        return elementArrayList;
    }
    public ArrayList<Statement> getFinalResult(ArrayList<ArrayList<Element>> elementList){
        ArrayList<Statement> statementArrayList=new ArrayList<>();

        for(int i=0;i<elementList.size();i++){
            double state_sus=0;
            for(int j=0;j<elementList.get(i).size();j++){
                state_sus=state_sus+elementList.get(i).get(j).getResult();
            }
            double sus=state_sus/elementList.get(i).size();
            if(Double.isNaN(sus)){
                sus=0;
            }
            if(sus!=0){
                System.out.println(i);
                System.out.println(elementList.get(i).get(0).getPath()+elementList.get(i).get(0).getRow());
                System.out.println(elementList.get(i).get(0).getResult());
            }
            Statement statement=new Statement();
            statement.setPath(elementList.get(i).get(0).getPath());
            statement.setSus(sus);
            statement.setRow(elementList.get(i).get(0).getRow());
            statementArrayList.add(statement);
        }
        return statementArrayList;
    }
    public ArrayList<Mutants> getAllMutants(String path) throws IOException {
        ArrayList<Mutants> finalList = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = null;
        while ((line = br.readLine()) != null) {
            String arr[] = line.split(":");
            String fileName[] = arr[4].split("@");
            String name = fileName[0];
            Mutants mutants = new Mutants();
            mutants.setNumber(Integer.parseInt(arr[0]));
            mutants.setPath(name);
            try {
                mutants.setRow(Integer.parseInt(arr[5]));
                finalList.add(mutants);
            }catch (Exception e){
                System.out.println(line);
            }
        }
        br.close();
        return finalList;
    }
}
