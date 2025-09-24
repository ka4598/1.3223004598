import java.text.DecimalFormat;

public class DupChecker {
    public static double calculateSimilarity(String original, String plagiarized) {
        //确保输入合法
        if (original == null || plagiarized == null) {
            throw new IllegalArgumentException("输入不能为 null");
        }
        if (original.isEmpty() || plagiarized.isEmpty()) {
            return 0.0;
        }
//Lcs是两个字符串按顺序出现但不一定连续的最长子序列，即计算两个字符串的最长公共子序列
        int lcs = lcsLength(original, plagiarized);
        return roundTwoDecimal((lcs * 100.0) / original.length());
    }

    private static int lcsLength(String s1, String s2) {
        int n = s1.length(), m = s2.length();
        if (m > n) { String t = s1; s1 = s2; s2 = t; n = s1.length(); m = s2.length(); }
        //滚动数组，通过填表法逐步计算LCS长度，循环遍历整个数组
        int[] prev = new int[m + 1], curr = new int[m + 1];
        for (int i = 1; i <= n; i++) {
            char c1 = s1.charAt(i - 1);
            for (int j = 1; j <= m; j++) {
                curr[j] = (c1 == s2.charAt(j - 1)) ? prev[j - 1] + 1 : Math.max(prev[j], curr[j - 1]);
            }
            int[] tmp = prev; prev = curr; curr = tmp;
        }
        return prev[m];
    }

    private static double roundTwoDecimal(double v) {
        return Double.parseDouble(new DecimalFormat("#.00").format(v));
    }
}
