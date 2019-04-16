import org.junit.Test;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.*;
public class getIDF {


    //idea: 总文档数 类内文档总数 类内包含该词的文档数

   /**
   * @Description: 由Map<类名,<文档名,文档内容> 生成 <词,idf>
    *     idf:log(N/n) N:总文档数；n:包含该词的文档数
    *     如果一个词只在少部分文档中出现，那么这个词就很有代表性；比如 “经济”、“政治”
    *     如果一个词在所有的文档中都出现，那么这个词就很普通，比如 “的”。
   * @Param: [originMap]
   * @return: java.util.Map<java.lang.String,java.lang.Double>
   * @Author: Qingyuan
   * @Date: 2019/4/16
   */
    public static Map<String,Double> getIDF(Map<String, Map<String, String>> originMap)throws Exception {

//        String filePath = "E:\\testwindows";

            Set<String> AllSet = new HashSet<>();
            Map<String, Map<String, Map<String, Double>>> tfMap = new HashMap<>();

            //   <className,<docName,Set<a,b,c,d,e>>>
            Map<String,Map<String,Set<String>>> classSetMap= new HashMap<>();
            for (Map.Entry<String, Map<String, String>> tempClass : originMap.entrySet()) {


                Set<String> classSet = new HashSet<>();
                Map<String, Map<String, Double>> docWord = new HashMap<>();
                //<docName,<a,b,c,d,e>>
                Map<String,Set<String>> txtSetMap = new HashMap<>();
                for (Map.Entry<String, String> tempdoc : tempClass.getValue().entrySet()) {

                    //txt <word,Freq>
                    String[] txtArray = tempdoc.getValue().split(" ");
                    float txtlength = txtArray.length;
                    Map<String, Double> wordFreq = new HashMap<>();
                    Set<String> txtSet = new HashSet<>();
                    for (String tempstring : txtArray) {
                        txtSet.add(tempstring);
                        if (wordFreq.containsKey(tempstring)) {
                            wordFreq.put(tempstring, wordFreq.get(tempstring) + 1.0d);
                        } else {
                            wordFreq.put(tempstring, 1.0d);
                        }
                    }
                    txtSetMap.put(tempdoc.getKey(),txtSet);

                    Iterator<Map.Entry<String, Double>> iter = wordFreq.entrySet().iterator();
                    while (iter.hasNext()) {
                        Map.Entry<String, Double> entry = (Map.Entry<String, Double>) iter.next();
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
                Map<String,Map<String,Double>> classnum = new HashMap<>();
                //<word,num>
                Map<String,Double> allnum = new HashMap<>();
                int fileCount = 0;
                for (Map.Entry<String,Map<String,Set<String>>> myEntry :classSetMap.entrySet()){

                    Map<String,Double> classWN = new HashMap<>();
                    for (Map.Entry<String,Set<String>> mydocEntry : myEntry.getValue().entrySet()){
                        fileCount++;
                        Iterator<String> W= mydocEntry.getValue().iterator();
                        while (W.hasNext()){
                            String word = W.next();
                            if (classWN.containsKey(word)){
                                classWN.put(word,classWN.get(word)+1.0d);
                            }else {
                                classWN.put(word,1.0d);
                            }

                            if (allnum.containsKey(word)){
                                allnum.put(word,allnum.get(word)+1.0d);
                            }else {
                                allnum.put(word,1.0d);
                            }
                        }
                    }
                    classnum.put(myEntry.getKey(),classWN);
                }



                //log(N/n)
        for (Map.Entry<String,Double> temp:allnum.entrySet()){

            allnum.put(temp.getKey(),Math.log(fileCount/temp.getValue()));

        }


//                return classnum;
                return allnum;
//            return tfMap;
        }



}
