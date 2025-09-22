import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class IOUtils {
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
    }

    public static void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes("UTF-8"));
    }
}
