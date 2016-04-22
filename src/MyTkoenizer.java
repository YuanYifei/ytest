import net.sf.classifier4J.ITokenizer;
import org.wltea.analyzer.core.IKSegmenter;
import org.wltea.analyzer.core.Lexeme;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fei_yuan on 2015/1/5.
 */
public class MyTkoenizer implements ITokenizer {
    @Override
    public String[] tokenize(String s) {
        try {
            List<String> l = new ArrayList<String>();
            IKSegmenter segmenter = new IKSegmenter(new StringReader(s), true);
            Lexeme lexeme;
            while ((lexeme = segmenter.next()) != null) {
                String temp = lexeme.getLexemeText();
                l.add(temp);
            }
            return l.toArray(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String[0];
    }
}
