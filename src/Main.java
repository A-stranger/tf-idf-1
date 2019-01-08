import org.junit.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
public class Main {


    public static void main(String[] args) throws IOException {


        String filePath = "E:\\testwindows";
//        String filePath = "E:\\1\\SplitData\\1\\train1";

        try {
            Map<String, Map<String, String>> fileread2map = operateGetFileMap.readFileByLine(filePath);
            Map<String, Map<String, Map<String, Double>>> tf = operateGetTF.getTF(fileread2map);
            Map<String,Double> idf =  operateGetIDF.getIDF(fileread2map);

            //tf*idf
            for(Entry<String,Map<String, Map<String, Double>>> classEntry : tf.entrySet()){

                for (Entry<String, Map<String, Double>> docEntry: classEntry.getValue().entrySet()){

                    for(Entry<String,Double> wordEntry : docEntry.getValue().entrySet()){

                        String theword = wordEntry.getKey();
                        Double theidf = idf.get(theword);
                        Double thetf = wordEntry.getValue();
                        Double thetfidf = thetf*theidf;
                        tf.get(classEntry.getKey()).get(docEntry.getKey()).put(theword,thetfidf);
                        System.out.println(thetfidf+"--"+thetf+"--"+theidf+"--"+theword);

                    }

                }
            }

            //Sort





            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }

//        for(Entry<String, Map<String, String>> tempClass :fileread2map.entrySet()){
//
//            for(Entry<String, String> tempdoc :tempClass.getValue().entrySet()){
//
//                System.out.println(tempClass.getKey()+"#"+tempdoc.getKey()+":"+tempdoc.getValue());
//
//            }
//        }
    }


    @Test
    public void test(){
        System.out.println(5/6.2);
    }



}