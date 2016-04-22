import net.sf.classifier4J.ClassifierException;
import net.sf.classifier4J.bayesian.BayesianClassifier;
import net.sf.classifier4J.bayesian.IWordsDataSource;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

import java.io.File;

/**
 * Created by fei_yuan on 2015/1/5.
 */
public class MyClassifier {
    public static void main(String[] args) throws Exception {


        int a=1;
        int xxx =1;
        int bb=2;
        String xcxc = "sdsds";
        String xcxcxx = "sdsds";
        String xcxcxxMaster = "sdsds";
        String xcxcxxMaster2 = "sdsds";
        String xcxc2 = "sdsds22";
        String xcxcxxB2 = "sdsds";
        String xcxcxxB21 = "sdsds";

        IWordsDataSource wds = new MyWordsDataSource();
        final BayesianClassifier classifier = new BayesianClassifier(wds, new MyTkoenizer());

        String bad = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\转人工-600天.xml"));
        String[] bads = bad.split("</CONTENT>");
        for (String b : bads) {
            String bText = StringUtils.trim(b);
            classifier.teachMatch(bText);
        }

//        bad = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\其他删除-600天.xml"));
//        bads = bad.split("</CONTENT>");
//        for (String b : bads) {
//            String bText = StringUtils.trim(b);
//            classifier.teachMatch(bText);
//        }

//       bad = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\直接删除-600天.xml"));
//        bads = bad.split("</CONTENT>");
//        for (String b : bads) {
//            String bText = StringUtils.trim(b);
//            classifier.teachMatch(bText);
//        }

        String good = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\2-6k.xml"));
        String[] goods = good.split("</CONTENT>");
        for (String b : goods) {
            String bText = StringUtils.trim(b);
            classifier.teachNonMatch(bText);
        }
//
//        double i = 0;
//        double e = 0;
//        String nav = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\3.xml"));
//        String[] navs = nav.split("</CONTENT>");
//        for (String b : navs) {
//            String bText = StringUtils.trim(b);
//            double d = classifier.classify(bText);
//            if (d >= 0.8) {
//                e++;
//            } else {
//                //System.out.println(bText);
//            }
//            i++;
//        }
//
//        System.out.println((e / i) * 100);
//
//
//        i = 0;
//        e = 0;
//        String navg = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\4.xml"));
//        String[] navgs = navg.split("</CONTENT>");
//        for (String b : navgs) {
//            String bText = StringUtils.trim(b);
//            double d = classifier.classify(bText);
//            if (d >= 0.8) {
//                e++;
//                System.out.println(bText);
//            } else {
//
//            }
//            i++;
//        }
//
//        System.out.println((e / i) * 100);

//        double d = classifier.classify("这个较满意，好看，摆着就不错。还没喝，不过不够密封，不小心就漏出来，还有就是这个包装不知道能不能放久时间，不锈钢的说");
//        if (d >= 0.95) {
//            System.out.println(true);
//        }

        String nav = FileUtils.readFileToString(new File("C:\\Users\\fei_yuan\\Desktop\\6.xml"));
        String[] navs = nav.split("</CONTENT>");
        for (final String b : navs) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String bText = StringUtils.trim(b);
                    double d = 0;
                    try {
                        d = classifier.classify(bText);
                    } catch (ClassifierException e) {
                        e.printStackTrace();
                    }
                    if (d >= 0.99) {
                        //System.out.println(bText);
                        System.out.println(Thread.currentThread().getId());
                    }
                }
            }).start();

        }
    }
}
