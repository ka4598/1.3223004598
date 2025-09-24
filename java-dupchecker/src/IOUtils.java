import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//提供文件读写操作
public class IOUtils {
    //读取文件
    public static String readFile(String path) throws IOException {
        return new String(Files.readAllBytes(Paths.get(path)), "UTF-8");
    }
//写入文件
    public static void writeFile(String path, String content) throws IOException {
        Files.write(Paths.get(path), content.getBytes("UTF-8"));
    }
}
