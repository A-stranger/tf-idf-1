package com.qingyuan.tfidf;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class file2Map {

    /**
    * @Description: 由文件生成 Map<类，文档名，文档内容>
    * @Param: [filePath, filenums]
     * 每个类中选择file nums篇文档
    * @return: java.util.Map<java.lang.String,java.util.Map<java.lang.String,java.lang.String>>
    * @Author: Qingyuan
    * @Date: 2019/4/16
    */
    public static Map<String,Map<String,String>> readFileByLine(String filePath,Integer filenums) throws IOException{
        File file = new File(filePath);
        Map<String,Map<String,String>> classDocTxt = new HashMap<>();

        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String tempLine;
            int i= 0;
            while((tempLine = bufferedReader.readLine())!=null){
                //教育#287543.txt:英国 少年 16 岁 创业 赚 人生 第一 个 100万 16 岁 时 你 在 干 什么 ？ 忙 着 做 功课 还是 玩 ？ 英国 少年 克里斯琴·欧文斯 16 岁 时 通过 经营 公司 ， 赚 得 他 人生 的 第一 个 100万 英镑 ( 约合 150万 美元 ) 。
                i++;
//				System.out.println(tempLine);
                int classIndex = tempLine.indexOf("#");
                int docIndex = tempLine.indexOf(":");
                String className = tempLine.substring(0, classIndex);
                String docName = tempLine.substring(classIndex+1,docIndex);
                String txtString = tempLine.substring(docIndex+1,tempLine.length());

                Map<String, String> tempDocTxt = new HashMap<>();
                tempDocTxt.put(docName, txtString);

//				System.out.println(className +"==="+docName);

				String txtStringArray [] =  txtString.split(" ");
//                System.out.println(txtStringArray.length);
                if (!classDocTxt.containsKey(className)){

                    classDocTxt.put(className, tempDocTxt);

                }else if(classDocTxt.get(className).size()<filenums){

                    classDocTxt.get(className).putAll(tempDocTxt);
                }
            }
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return classDocTxt;
    }


}

