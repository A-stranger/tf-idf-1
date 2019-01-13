import org.junit.Test;

import javax.sound.midi.SysexMessage;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Main {


    public static void main(String[] args) throws IOException {


//        String filePath = "E:\\testwindows";
        String filePath = "E:\\1\\SplitData\\1\\train1";

        try {
            Map<String, Map<String, String>> fileread2map = operateGetFileMap.readFileByLine(filePath);
            Map<String, Map<String, Map<String, Double>>> tf = operateGetTF.getTF(fileread2map);
            Map<String, Double> idf = operateGetIDF.getIDF(fileread2map);

            //tf*idf
            for (Entry<String, Map<String, Map<String, Double>>> classEntry : tf.entrySet()) {

                System.out.println(classEntry.getKey());
                for (Entry<String, Map<String, Double>> docEntry : classEntry.getValue().entrySet()) {

                    for (Entry<String, Double> wordEntry : docEntry.getValue().entrySet()) {

                        String theword = wordEntry.getKey();
                        Double thetf = wordEntry.getValue();
                        Double theidf = idf.get(theword);
                        Double thetfidf = thetf * theidf;
                        tf.get(classEntry.getKey()).get(docEntry.getKey()).put(theword, thetfidf);
//                        System.out.println(thetfidf + "--" + thetf + "--" + theidf + "--" + theword);
                    }

//                    for(Entry<String,Double> wordEntry : docEntry.getValue().entrySet()){


                    List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(docEntry.getValue().entrySet());
                    Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
                        public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                            if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
                                return 1;
                            } else {
                                return -1;
                            }
                        }
                    });

                    System.out.println(list_Data);
//                    }


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
    public void test() {
        System.out.println(5 / 6.2);
        Map<String, Double> map_Data = new HashMap();
        map_Data.put("A", 98.2d);
        map_Data.put("B", 50.4d);
        map_Data.put("C", 50.2d);
        map_Data.put("D", 25.4d);
        map_Data.put("E", 85.9d);
        System.out.println(map_Data);
        List<Map.Entry<String, Double>> list_Data = new ArrayList<Map.Entry<String, Double>>(map_Data.entrySet());
        Collections.sort(list_Data, new Comparator<Map.Entry<String, Double>>() {
            public int compare(Map.Entry<String, Double> o1, Map.Entry<String, Double> o2) {
                if (o2.getValue() != null && o1.getValue() != null && o2.getValue().compareTo(o1.getValue()) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        System.out.println(list_Data);

    }


}