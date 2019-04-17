import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Test没啥用 {

    @Test
    public  void readByLine(){
        String filePath = "E:\\1\\SplitData\\1\\test";
        File file = new File(filePath);
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String tempLine;
            int i = 0;
            while ((tempLine = bufferedReader.readLine()) != null) {
                i++;
//                getProb(tempLine);
                if (i>=10){
                    break;
                }
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }

//    //从文件读取到测试Map
//    @Test
//    public  void readToMap(){
//        String filePath = "E:\\1\\SplitData\\1\\test";
//        try{
//            Map<String,Map<String,String>> testMap=  com.qingyuan.tfidf.file2Map.readFileByLine(filePath,10);
//            traversalTestMap(testMap);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//    }

//    //Traversal wordMap 分类
//    public static void  traversalTestMap(Map<String,Map<String,String>> testMap){
//
//        //类别： 文档1，文档2，文档3
//        Map<String, ArrayList<String>> origional = new HashMap<>();
//        Map<String, ArrayList<String>> classify = new HashMap<>();
//        for (Map.Entry<String,Map<String,String>> classmap :testMap.entrySet()){
//            String oriKey = classmap.getKey();
//
//            for (Map.Entry<String,String> wordmap :classmap.getValue().entrySet()){
//
//                String docName = wordmap.getKey();
//                String txtString = wordmap.getValue();
//                String returnkey = getFileProb(classmap.getKey(),wordmap.getValue());
//
//                //原来的Map<类，文档名>
//                if (origional.get(oriKey)!=null){
//                    origional.get(oriKey).add(docName);
//                }else {
//                    ArrayList<String> tempdoc = new ArrayList<>();
//                    tempdoc.add(docName);
//                    origional.put(oriKey,tempdoc);
//                }
//                //之后的Map<类，文档名>
//                if (classify.get(returnkey)!=null){
//                    classify.get(returnkey).add(docName);
//                }else {
//                    ArrayList<String> tempdoc = new ArrayList<>();
//                    tempdoc.add(docName);
//                    classify.put(returnkey,tempdoc);
//                }
//            }
//        }
//
//        operateEvaluation.getEvaluation(origional,classify);
//    }


//    //遍历测试Map的文档
//    public  static String getFileProb(String OriginclassName,String txtString){
//        Map<String, Map<String,Double>> keyword = getClassTopK.getClassTopKMap();
//        Map<String, List<Map.Entry<String, Double>>> list_Data = getClassTopK.getClassTopK(keyword);
//
//        String txtStringArray [] =  txtString.split(" ");
//
//        Map<String,Double> ResultclassProb = new HashMap<>();
//        for (Map.Entry<String, List<Map.Entry<String, Double>>> tempclass : list_Data.entrySet()){
//
//            String classname = tempclass.getKey();
//            Map<String,Double> wordtfidf = new HashMap<>();
//            for (Map.Entry<String, Double> tempwords :tempclass.getValue()){
//
//                String word = tempwords.getKey();
//                Double tfidf = tempwords.getValue();
//                wordtfidf.put(word,tfidf);
//            }
//
//            Double totalProb=1.0;
//            for (int i = 0;i<txtStringArray.length;i++){
//                if (wordtfidf.containsKey(txtStringArray[i])){
////                    totalProb += Math.log(wordtfidf.get(txtStringArray[i]));
//                    totalProb +=wordtfidf.get(txtStringArray[i]);
//                }
//            }
//            ResultclassProb.put(classname,totalProb);
//            System.out.println(OriginclassName+"|"+classname+"|"+totalProb+"|"+totalProb/txtStringArray.length);
//        }
//
//        double x = Double.MIN_VALUE;
//        String returnkey = null;
//        for (Map.Entry<String,Double> temp :ResultclassProb.entrySet()){
//            if (temp.getValue()>x){
//                x=temp.getValue();
//                returnkey = temp.getKey();
//            }
//        }
//        System.out.println(returnkey+"=====");
//
//        return returnkey;
//    }
}
