import com.qingyuan.tfidf.IDF;
import com.qingyuan.tfidf.TF;
import com.qingyuan.tfidf.file2Map;
import org.junit.Test;

import java.util.*;

public class getClassTopK {

    public static Map<String, Map<String,Double>> getClassTopKMap(Map<String, Map<String, Map<String, Double>>> tf, Map<String, Double> idf){
        String filePath = "E:\\1\\SplitData\\1\\train";
        Map<String, Map<String,Double>> classTopK = new HashMap<>();
        try {
          //  Map<String, Map<String, String>> fileread2map = com.qingyuan.tfidf.file2Map.readFileByLine(filePath,50);
          //  Map<String, Map<String, Map<String, Double>>> tf = com.qingyuan.tfidf.TF.getTF(fileread2map);
          //  Map<String, Double> idf = com.qingyuan.tfidf.IDF.getIDF(fileread2map);

            //tf*idf
            for (Map.Entry<String, Map<String, Map<String, Double>>> classEntry : tf.entrySet()) {

                System.out.println(classEntry.getKey());
                for (Map.Entry<String, Map<String, Double>> docEntry : classEntry.getValue().entrySet()) {

                    for (Map.Entry<String, Double> wordEntry : docEntry.getValue().entrySet()) {

                        String theword = wordEntry.getKey();
                        Double thetf = wordEntry.getValue();
                        Double theidf = idf.get(theword);
                        Double thetfidf = thetf * theidf;
                        tf.get(classEntry.getKey()).get(docEntry.getKey()).put(theword, thetfidf);

                        if (classTopK.get(classEntry.getKey())!=null){
                            if (classTopK.get(classEntry.getKey()).get(theword)!=null) {
                                Double temp= classTopK.get(classEntry.getKey()).get(theword);
                                classTopK.get(classEntry.getKey()).put(theword,temp+thetfidf);
                            }else {
                                classTopK.get(classEntry.getKey()).put(theword,thetfidf);
                            }
                        }else{
                            Map<String,Double> wordtfidf = new HashMap<>();
                            wordtfidf.put(theword,thetfidf);
                            classTopK.put(classEntry.getKey(),wordtfidf);
                        }
//                        System.out.println(thetfidf + "--" + thetf + "--" + theidf + "--" + theword);
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classTopK;
    }

    public static Map<String,List<Map.Entry<String, Double>>> getClassTopK(Map<String, Map<String,Double>> classTopK){

        Map<String,List<Map.Entry<String, Double>>> classTopKMap = new HashMap<>();
        List<Map.Entry<String, Double>> list_Data = null;
        for(Map.Entry<String,Map<String,Double>> tempClass : classTopK.entrySet()){

            list_Data = new ArrayList<Map.Entry<String, Double>>(tempClass.getValue().entrySet());
            Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
                public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                    if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            });
            System.out.println(list_Data.subList(0,1000));
            classTopKMap.put(tempClass.getKey(),list_Data.subList(0,1500));
        }
        return classTopKMap;
    }

    @Test
    public void test(){


        file2Map f2m = new file2Map();
        TF tf = new TF();
        IDF idf = new IDF();
        try{

            Map<String,Map<String,String>> fmap =  f2m.readFileByLine("E:\\testwindows",50);
            Map<String, Map<String, Map<String,Double>>> thetf =  tf.getTF(fmap);
            Map<String,Double> theidf = idf.getIDF(fmap);
            Map<String, Map<String,Double>> first = getClassTopKMap(thetf,theidf);
            Map<String,List<Map.Entry<String, Double>>> list_Data = getClassTopK(first);

            for (Map.Entry<String,List<Map.Entry<String, Double>>> tempclass: list_Data.entrySet()){
                String classname = tempclass.getKey();
                double average =0.0;
                double sum = 0.0;
                for (Map.Entry<String, Double> tempwords :tempclass.getValue()){

                    double temp = tempwords.getValue();
                    sum +=temp;
                }
                average = sum/500;
                System.out.println(classname+":"+sum+":"+average);
            }

        }catch (Exception exception){
            exception.printStackTrace();
        }










    }
}