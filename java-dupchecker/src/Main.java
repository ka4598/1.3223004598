public class Main {
    public static void main(String[] args) throws Exception {
        //检查命令行参数数量是否足够，比如只含有一个原文件就不能继续执行下面命令
        if (args.length < 3) {
            System.out.println("Usage: java Main <orig.txt> <orig_0.8_add.txt> <orig_0.8_del.txt> <orig_0.8_dis_1.txt> <orig_0.8_dis_10.txt> <orig_0.8_dis_15.txt>");
            return;
        }
//规定第一个文件是原始文件
        String origPath = args[0];
        String ansPath = args[args.length - 1];
//读取原始文件的字符
        String orig = IOUtils.readFile(origPath);

        StringBuilder results = new StringBuilder();
        for (int i = 1; i < args.length - 1; i++) {
            String plagPath = args[i];
            String plag = IOUtils.readFile(plagPath);
//相似度的计算
            double rate = DupChecker.calculateSimilarity(orig, plag);
            results.append(plagPath).append(": ").append(String.format("%.2f", rate)).append("%\n");
        }

        IOUtils.writeFile(ansPath, results.toString());
        System.out.println("相似度结果已写入 " + ansPath);
    }
}
