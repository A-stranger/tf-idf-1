import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class operateEvaluation {
    
    /** 
    * @Description: 目前没啥用。。。 
    * @Param: [origional, classify] 
    * @return: void 
    * @Author: Qingyuan
    * @Date: 2019/4/17 
    */ 
    public static void getEvaluation(Map<String, ArrayList<String>> origional,Map<String, ArrayList<String>> classify) {

//        //类别： 文档1，文档2，文档3
//        Map<String, ArrayList<String>> origional = new HashMap<>();
//        Map<String, ArrayList<String>> classify = new HashMap<>();
//        origional = readFileLine.readOrigionalFile("/home/qingyuan/文档/workspace/Hadoop_NB_File/sourceClassify/part-r-00000");
//        classify = readFileLine.readClassifyFile("/home/qingyuan/文档/workspace/Hadoop_NB_File/arg4/part-r-00000");

        int all = 0;
        for (Map.Entry<String, ArrayList<String>> origionalEntry : origional.entrySet()) {
            all += origionalEntry.getValue().size();
        }

        System.out.println("" + "oriSize:\t" + "classSize:\t" + "TP:\t" + "FP:\t" + "FN:\t");
        double MacroP = 0.0, MacroR = 0.0, MacroF1 = 0.0;
        double totalTP = 0.0, totalClassSize = 0.0, totalOriSize = 0.0;
        for (Map.Entry<String, ArrayList<String>> origionalEntry : origional.entrySet()) {

            int oriSize = origionalEntry.getValue().size();
            String origionalName = origionalEntry.getKey();
            ArrayList<String> origionalList = origionalEntry.getValue();
            ArrayList<String> classifyList = classify.get(origionalName);
            int classSize = classifyList.size();

            origionalList.retainAll(classifyList);
            int TP = origionalList.size();
//			System.out.println(TP);
            int FP = classSize - TP;
            int FN = oriSize - TP;
            int TN = all - TP - FP - FN;
            double P, R, F1;
            P = (double) TP / classSize;
            R = (double) TP / oriSize;
            F1 = 2 * P * R / (P + R);

            totalTP += TP;
            totalClassSize += classSize;
            totalOriSize += oriSize;

            MacroP += P;
            MacroR += R;
            MacroF1 += F1;

            System.out.println(origionalName + oriSize + "\t" + classSize + "\t" + TP + "\t" + FP + "\t" + FN + "\t" + P + "\t" + R + "\t" + F1);

        }
        double MarcoF1_2PR = (2 * MacroR / 14 * MacroP / 14) / (MacroR / 14 + MacroP / 14);

        System.out.println("Macro:\t" + MacroP / 14 + "---" + MacroR / 14 + "---" + MacroF1 / 14 + "---" + MarcoF1_2PR);
        System.out.println(totalTP / totalClassSize);
        System.out.println(totalTP / totalOriSize);
    }
}
