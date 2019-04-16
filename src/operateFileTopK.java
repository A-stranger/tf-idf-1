import java.util.*;

public class operateFileTopK {

    /**
    * @Description: get file tf*idf Map
    * @Param: []
    * @return: java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.Double>>
    * @Author: Qingyuan
    * @Date: 2019/4/16
    */
    public static Map<String, Map<String,Double>> getFileTFIDF(){
        String filePath = "E:\\1\\SplitData\\1\\train";

        Map<String, Map<String, Map<String, Double>>> tf = null;
        Map<String, Map<String,Double>> classTopK = new HashMap<>();
        try {
            Map<String, Map<String, String>> fileread2map = file2Map.readFileByLine(filePath,50);
            tf = getTF.getTF(fileread2map);
            Map<String, Double> idf = getIDF.getIDF(fileread2map);

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

                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classTopK;
    }

    public static Map<String, List<Map.Entry<String, Double>>> getFileTopK(Map<String, Map<String,Double>> classTopK){

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
//            System.out.println(list_Data.subList(0,1000));
            classTopKMap.put(tempClass.getKey(),list_Data.subList(0,1500));
        }
        return classTopKMap;
    }

}
