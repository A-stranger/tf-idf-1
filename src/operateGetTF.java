import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.*;

public class operateGetTF {

    /*
    * 返回 Map<类String, Map<文档名String, Map<词String,词频float>>>
    *
    * */
    public static Map<String, Map<String, Map<String,Float>>> getTF(Map<String,Map<String,String>> originMap)throws  Exception{


//        String filePath = "E:\\testwindows";


        Map<String, Map<String, Map<String,Float>>> tfMap = new HashMap<>();
        for(Map.Entry<String, Map<String, String>> tempClass :originMap.entrySet()){


            Map<String,Map<String,Float>> docWord = new HashMap<>();
            for(Map.Entry<String, String> tempdoc :tempClass.getValue().entrySet()){


                //txt <word,Freq>
                String [] txtArray =  tempdoc.getValue().split(" ");
                float txtlength = txtArray.length;
                Map<String,Float> wordFreq = new HashMap<>();
                for (String tempstring : txtArray){

                    if (wordFreq.containsKey(tempstring)){
                        wordFreq.put(tempstring,wordFreq.get(tempstring)+1.0f);
                    }else{
                        wordFreq.put(tempstring,1.0f);
                    }
                }

                Iterator<Map.Entry<String, Float>> iter = wordFreq.entrySet().iterator();
                while(iter.hasNext())
                {
                    Map.Entry<String, Float> entry = (Map.Entry<String, Float>)iter.next();
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
