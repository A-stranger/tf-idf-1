import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.*;
public class operateGetIDF {

    /*
     * 某词的IDF = LOG(总文档数/包含该词的文档数) N/n
     *
     * 总文档数 类内文档总数 类内包含该词的文档数
     *
     * */

//            public static Map<String, Map<String, Map<String, Float>>> getIDF(Map<String, Map<String, String>> originMap)throws Exception {
    public static Map<String,Integer> getIDF(Map<String, Map<String, String>> originMap)throws Exception {

//        String filePath = "E:\\testwindows";

            Set<String> AllSet = new HashSet<>();
            Map<String, Map<String, Map<String, Float>>> tfMap = new HashMap<>();

            //   <className,<docName,Set<a,b,c,d,e>>>
            Map<String,Map<String,Set<String>>> classSetMap= new HashMap<>();
            for (Map.Entry<String, Map<String, String>> tempClass : originMap.entrySet()) {


                Set<String> classSet = new HashSet<>();
                Map<String, Map<String, Float>> docWord = new HashMap<>();
                //<docName,<a,b,c,d,e>>
                Map<String,Set<String>> txtSetMap = new HashMap<>();
                for (Map.Entry<String, String> tempdoc : tempClass.getValue().entrySet()) {

                    //txt <word,Freq>
                    String[] txtArray = tempdoc.getValue().split(" ");
                    float txtlength = txtArray.length;
                    Map<String, Float> wordFreq = new HashMap<>();
                    Set<String> txtSet = new HashSet<>();
                    for (String tempstring : txtArray) {
                        txtSet.add(tempstring);
                        if (wordFreq.containsKey(tempstring)) {
                            wordFreq.put(tempstring, wordFreq.get(tempstring) + 1.0f);
                        } else {
                            wordFreq.put(tempstring, 1.0f);
                        }
                    }
                    txtSetMap.put(tempdoc.getKey(),txtSet);

                    Iterator<Map.Entry<String, Float>> iter = wordFreq.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, Float> entry = (Map.Entry<String, Float>) iter.next();
                        wordFreq.put(entry.getKey(), entry.getValue() / txtlength);
                    }

                    //<docName,<word,Freq>>
                    docWord.put(tempdoc.getKey(), wordFreq);

//                System.out.println(tempClass.getKey()+"#"+tempdoc.getKey()+":"+tempdoc.getValue());
                }
                classSetMap.put(tempClass.getKey(),txtSetMap);
                //<ClassName,<docName,<word,Freq>>>
                tfMap.put(tempClass.getKey(), docWord);
            }


            //类内包含词的文档数，整个语料库中包含此词的文档数
                //<class,<word,num>>
                Map<String,Map<String,Integer>> classnum = new HashMap<>();
                //<word,num>
                Map<String,Integer> allnum = new HashMap<>();
                int fileCount = 0;
                for (Map.Entry<String,Map<String,Set<String>>> myEntry :classSetMap.entrySet()){

                    Map<String,Integer> classWN = new HashMap<>();
                    for (Map.Entry<String,Set<String>> mydocEntry : myEntry.getValue().entrySet()){
                        fileCount++;
                        Iterator<String> W= mydocEntry.getValue().iterator();
                        while (W.hasNext()){
                            String word = W.next();
                            if (classWN.containsKey(word)){
                                classWN.put(word,classWN.get(word)+1);
                            }else {
                                classWN.put(word,1);
                            }

                            if (allnum.containsKey(word)){
                                allnum.put(word,allnum.get(word)+1);
                            }else {
                                allnum.put(word,1);
                            }
                        }
                    }
                    classnum.put(myEntry.getKey(),classWN);
                }

//                return classnum;
                return allnum;
//            return tfMap;
        }


}
