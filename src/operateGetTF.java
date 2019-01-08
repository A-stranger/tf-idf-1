import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.*;

public class operateGetTF {

    /*
    * 返回 Map<类String, Map<文档名String, Map<词String,词频float>>>
    *
    * */
    public static Map<String, Map<String, Map<String,Double>>> getTF(Map<String,Map<String,String>> originMap)throws  Exception{


//        String filePath = "E:\\testwindows";


        Map<String, Map<String, Map<String,Double>>> tfMap = new HashMap<>();
        for(Map.Entry<String, Map<String, String>> tempClass :originMap.entrySet()){


            Map<String,Map<String,Double>> docWord = new HashMap<>();
            for(Map.Entry<String, String> tempdoc :tempClass.getValue().entrySet()){


                //txt <word,Freq>
                String [] txtArray =  tempdoc.getValue().split(" ");
                float txtlength = txtArray.length;
                Map<String,Double> wordFreq = new HashMap<>();
//                Set<String> wordSet = new HashSet<>();
                for (String tempstring : txtArray){

//                    wordSet.add(tempstring);
                    if (wordFreq.containsKey(tempstring)){
                        wordFreq.put(tempstring,wordFreq.get(tempstring)+1.0d);
                    }else{
                        wordFreq.put(tempstring,1.0d);
                    }
                }

                Iterator<Map.Entry<String, Double>> iter = wordFreq.entrySet().iterator();
                while(iter.hasNext())
                {
                    Map.Entry<String, Double> entry = (Map.Entry<String, Double>)iter.next();
                    wordFreq.put(entry.getKey(), entry.getValue()/txtlength);
                }

                //<docName,<word,Freq>>
                docWord.put(tempdoc.getKey(),wordFreq);

//                System.out.println(tempClass.getKey()+"#"+tempdoc.getKey()+":"+tempdoc.getValue());
            }

            //<ClassName,<docName,<word,Freq>>>
            tfMap.put(tempClass.getKey(),docWord);
        }
        return  tfMap;
    }

}
