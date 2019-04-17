package com.qingyuan.tfidf;

import org.junit.Test;
import java.util.*;

public class TFIDF {

    /**
    * @Description: get file tf*idf Map
    * @Param: []
    * @return: java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.Double>>
    * @Author: Qingyuan
    * @Date: 2019/4/16
    */
    public static  Map<String, Map<String, Map<String, Double>>> getFileTFIDF(Map<String, Map<String, Map<String, Double>>> tf, Map<String, Double> idf){

//        String filePath = "E:\\1\\SplitData\\1\\train";
        String filePath = "E:\\testwindows";

        try {

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
        return tf;
    }


    /**
    * @Description: 输出某一类中所有文档的特征词
    * @Param: [tfidf]
    * @return: java.util.Map<java.lang.String,java.util.List<java.util.Map.Entry<java.lang.String,java.lang.Double>>>
     *     返回<docName,<No.1Word,tfidf>,<No.2Word,tfidf>,<No.3Word,tfidf>......<No.x,tfidf>>
    * @Author: Qingyuan
    * @Date: 2019/4/17
    */
    public static Map<String, List<Map.Entry<String, Double>>> getFileTopK(Map<String, Map<String,Double>> docTfIdf){

        Map<String,List<Map.Entry<String, Double>>> fileTopKMap = new HashMap<>();
        List<Map.Entry<String, Double>> list_Data = null;
        for(Map.Entry<String,Map<String,Double>> tempClass : docTfIdf.entrySet()){

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
            //System.out.println(list_Data.subList(0,200));
            System.out.println(list_Data);
            //fileTopKMap.put(tempClass.getKey(),list_Data.subList(0,200));
            fileTopKMap.put(tempClass.getKey(),list_Data);
        }
        return fileTopKMap;
    }

    /**
    * @Description: 调用getFileTopK,输出所有类中文档的特征词
    * @Param: [classTfIdf]
    * @return: void
    * @Author: Qingyuan
    * @Date: 2019/4/17
    */
    public static void  getAllFileTopK( Map<String, Map<String, Map<String, Double>>> classTfIdf){

       for(Map.Entry<String, Map<String, Map<String, Double>>> temp: classTfIdf.entrySet()){
           System.out.println(temp.getKey());
           getFileTopK(temp.getValue());
       }

    }


    @Test
    public void test(){

        try{

            //String filePath = "NewsData";
            Map<String,Map<String,String>> fmap =  file2Map.readFileByLine("E:\\NewsData",50);
            Map<String, Map<String, Map<String,Double>>> thetf =  TF.getTF(fmap);
            Map<String,Double> theidf = IDF.getIDF(fmap);
            Map<String, Map<String, Map<String, Double>>> tfidf = getFileTFIDF(thetf,theidf);
            getAllFileTopK(tfidf);
        }catch (Exception exception){
            exception.printStackTrace();
        }


    }

}
