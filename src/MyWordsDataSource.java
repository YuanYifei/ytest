import net.sf.classifier4J.bayesian.IWordsDataSource;
import net.sf.classifier4J.bayesian.WordProbability;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fei_yuan on 2015/1/9.
 */
public class MyWordsDataSource implements IWordsDataSource, Serializable {
    private Map words = new HashMap();

    public void setWordProbability(WordProbability wp) {
        words.put(wp.getWord(), wp);
    }

    /**
     * @see net.sf.classifier4J.bayesian.IWordsDataSource#getWordProbability(java.lang.String)
     */
    public WordProbability getWordProbability(String word) {
        if (words.containsKey(word)) {
            WordProbability p = (WordProbability) words.get(word);
            return p;
        } else {
            WordProbability p = new WordProbability();
            p.setProbability(0.4);
            return p;
        }
    }

    public Collection getAll() {
        return words.values();
    }

    /**
     * @see net.sf.classifier4J.bayesian.IWordsDataSource#addMatch(java.lang.String)
     */
    public void addMatch(String word) {
        WordProbability wp = (WordProbability) words.get(word);
        if (wp == null) {
            wp = new WordProbability(word, 1, 0);
        } else {
            wp.setMatchingCount(wp.getMatchingCount() + 1);
        }
        setWordProbability(wp);
    }

    /**
     * @see net.sf.classifier4J.bayesian.IWordsDataSource#addNonMatch(java.lang.String)
     */
    public void addNonMatch(String word) {
        WordProbability wp = (WordProbability) words.get(word);
        if (wp == null) {
            wp = new WordProbability(word, 0, 1);
        } else {
            wp.setNonMatchingCount(wp.getNonMatchingCount() + 1);
        }
        setWordProbability(wp);
    }
}

