import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class getTF {

    public Map<String, Map<String, Set<String>>> getTF()throws  Exception{


        String filePath = "E:\\testwindows";
        Map<String, Map<String,String >> fileread2map = OperateFile.readFileByLine(filePath);
        Map<String, Map<String, Set<String>>> tfMap = new HashMap<>();

        for(Map.Entry<String, Map<String, String>> tempClass :fileread2map.entrySet()){

            String className = tempClass.getKey();
            Map<String,Set<String>> wordMap = new HashMap<>();

            for(Map.Entry<String, String> tempdoc :tempClass.getValue().entrySet()){

                String [] txtArray =  tempdoc.getValue().split(" ");
                Set<String> wordset = new HashSet<>();

                for (String tempstring : txtArray){
                    wordset.add(tempstring);
                }

                wordMap.put(tempdoc.getKey(),wordset);
//                System.out.println(tempClass.getKey()+"#"+tempdoc.getKey()+":"+tempdoc.getValue());
            }

            tfMap.put(className,wordMap);
        }
        return  tfMap;
    }

}
