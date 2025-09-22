import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Map;

public class DupCheckerTest {

    private DupChecker dupChecker;

    @BeforeEach
    void setUp() {
        dupChecker = new DupChecker();
    }

    @Test
    void testGetWordFreq() {
        String text = "Hello world! Hello ChatGPT.";
        Map<String, Integer> freq = dupChecker.getWordFreq(text);

        assertNotNull(freq, "词频 Map 不应为 null");
        assertEquals(2, freq.get("hello"), "hello 应该出现 2 次");
        assertEquals(1, freq.get("world"), "world 应该出现 1 次");
        assertEquals(1, freq.get("chat"), "chat 应该出现 1 次");
    }

    @Test
    void testCalculateSimilaritySameText() {
        String text1 = "Java is great!";
        String text2 = "Java is great!";
        double sim = dupChecker.calculateSimilarity(text1, text2);

        assertEquals(1.0, sim, 1e-6, "相同文本相似度应该为 1.0");
    }

    @Test
    void testCalculateSimilarityDifferentText() {
        String text1 = "Java is great!";
        String text2 = "Python is powerful!";
        double sim = dupChecker.calculateSimilarity(text1, text2);

        assertTrue(sim >= 0.0 && sim <= 1.0, "相似度应该在 0~1 之间");
        assertTrue(sim < 0.5, "不同文本相似度应较低");
    }

    @Test
    void testCalculateSimilarityPartialOverlap() {
        String text1 = "Java and Python are popular.";
        String text2 = "Java is popular.";
        double sim = dupChecker.calculateSimilarity(text1, text2);

        assertTrue(sim > 0.3 && sim < 1.0, "部分重叠的文本相似度应在 0.3 ~ 1.0 之间");
    }

    @Test
    void testCalculateSimilarityEmptyText() {
        String text1 = "";
        String text2 = "Some text here.";
        double sim = dupChecker.calculateSimilarity(text1, text2);

        assertEquals(0.0, sim, "空字符串和非空字符串的相似度应为 0");
    }
}
