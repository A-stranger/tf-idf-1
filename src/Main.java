import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;
public class Main {

    public static void main(String[] args) throws IOException {

        String filePath = "E:\\testwindows";
//        String filePath = "E:\\1\\SplitData\\1\\train1";
        Map<String, Map<String, String>> fileread2map = operateGetFileMap.readFileByLine(filePath);

        try {
            Map<String, Map<String, Map<String, Float>>> tf = operateGetTF.getTF(fileread2map);
            System.out.println();
        } catch (Exception e) {
            e.printStackTrace();
        }



        for(Entry<String, Map<String, String>> tempClass :fileread2map.entrySet()){

            for(Entry<String, String> tempdoc :tempClass.getValue().entrySet()){

                System.out.println(tempClass.getKey()+"#"+tempdoc.getKey()+":"+tempdoc.getValue());
            }
        }

        try{
            Map<String,Integer> allWordFren =  operateGetIDF.getIDF(fileread2map);
            System.out.println();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}